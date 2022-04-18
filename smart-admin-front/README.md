# 项目讲解

## 配置和启动流程
### vscode配置
将 /vscode/settings.json文件配置到vscode中

### 启动命令
1. 安装依赖：
`npm install`

2. 运行本地环境
`npm run local`

### 前端启动流程
前端的入口Vue文件:src/App.vue。打开文件可以看到，它的template模板只有简洁的`router-view`标签。
它的页面展示全部由router来控制。router的入口文件是，`src/router/index.js`，具体的路由配置在文件`src/router/routers.js`
下面看一个例子，页面`业务功能`这个页面选项的路由配置
```
{
    path: '/business',
    name: 'Business',
    component: Main,
    meta: {
      title: '业务功能',
      topMenu:true,
      icon: 'icon iconfont iconyoujianguanli'
    },
    children: [
      ...peony,
      ...emailSetting,
      ...keepAlive,
      ...notice,
      ...threeRouter
    ]
}
```
可以看到这个路由配置的组件是`Main`，对应的vue文件是`src/components/main/main.vue`


### 页面结构
ViewUI提供了Layout组件进行页面的布局(**使用flex实现**)，它可以嵌套如下的组件：
- Header，顶部布局，自带默认样式，可以嵌套任何的元素，只能放在Layout中
- Sider，侧边栏布局，自带默认样式及基本功能，可嵌套任何元素，只能放在Layout中
- Content，内容布局，自带默认样式，可以嵌套任何元素，只能放在Layout中
- Footer，底部布局，自带默认样式，可以嵌套任何元素，只能放在Layout中
- 以及Layou本身

#### 页面主体
本项目使用的布局格式如下：
```
<Layout>
        <Sider hide-trigger>Sider</Sider>
        <Layout>
            <Header>Header</Header>
            <Content>Content</Content>
        </Layout>
</Layout>

// 布局示意图
------------------------------------------------------
|        |                Header                     |
|        |--------------------------------------------
|  Sider |                                           |
|        |                Content                    |         
|        |                                           |
------------------------------------------------------
```


## 前端基础
前端使用框架是ViewUI，https://www.iviewui.com/docs/introduce

### webpack核心概念
1. loader，用于对模块源代码进行转换。loader可以使你在import或load模块时预处理文件。loader类似于其他构建工具中的任务task。loader可以将文件从不同的语言(如typescript)转为javascript
2. plugin，loader用于转换某些类型的模块，而插件则可以用于执行范围更广的任务。包括:打包优化、资源管理、注入环境变量。如果你想使用一个plugin只需要在xxx.config.js中require它，并添加到plugins数组
3. mode，通过选择development、production或none中的一个，你可以启用webpack内置在相应环境下的优化。默认值为production
4. 浏览器兼容性，webpack支持所有符合ES5标准的浏览器（不支持IE8及以下浏览器）。webpack的import()和require.ensure()需要Promise。如果要支持旧版浏览器，在使用这些表达式之前，需要提前加载polyfill
5. 环境，webpack5运行与Node.js v10.13.0+版本
6. webpack的默认配置文件是，webpack.config.js

> webpack的中文文档：https://webpack.docschina.org/guides/
> 文档分为几个部分：API、概念、配置、指南、Loader、迁移和Plugin
> 入门主要看概念和指南模块

### package.json
用于控制webpack的行为
1. browserslist: 浏览器兼容性配置, 打包输出的js代码支持配置的浏览器
2. gitHooks: git钩子,在执行特定git动作时触发，比如在commit前执行`lint-staged`。lint-staged可以格式化代码、语法检查等操作
3. lint-staged: 一个文件过滤器,过滤出暂存区的文件进行语法检查（git add 后的文件）当操作，这个取决于配置

### vue.config.json
vue.config.json是一个可选的配置文件,如果项目的根目录存在这个文件,它会被@vue/cli-service自动加载。你也可以使用package.json中的vue字段进行配置
参考: https://cli.vuejs.org/zh/config/#pages
1. lintOnSave: 是否在开发环境下通过 eslint-loader 在每次保存时 lint 代码。这个值会在 @vue/cli-plugin-eslint 被安装之后生效
2. publicPath: 部署应用包时的基本URL
3. configureWebpack: 调整 webpack 配置最简单的方式就是在 vue.config.js 中的 configureWebpack 选项提供一个对象。这些配置最终合并入webpack配置
4. chainWebpack: 提供的webpack配置的上层抽象，在这里的配置会影响最终webpack的配置

### 语法记录
1. Vue的实例属性mixins: 组件的一个属性，意思是混入，顾名思义就是把其他组件混入的到当前组件实现复用。这个混入具体就是合并当前组件和被混入组件的data、methods、components等属性，如果有重名冲突则优先选择当前组件，以及它们的生命周期函数，混入的生命周期函数会先于当前组件的生命周期函数先执行
2. 在使用Router情况下，在Vue组件内使用$router.name可以获取当前路由的名称
3. Vue的实例属性refs，可以用来访问在标签中使用了ref命名的dom元素或子组件。例如，
```
// template
<SideMenu
        :active-name="$route.name"
        :collapsed="collapsed"
        :menu-list="menuList"
        :menuNameMatchedMap="menuNameMatchedMap"
        @on-select="turnToPage"
        accordion
        ref="sideMenu"
>

// js
this.$refs.sideMenu.updateActiveName(this.$route.name);
```
4. 计算属性computed，当依赖原始值发生变化时，计算属性也会发生变化，通常当成一个衍生属性来使用
```
// 数据
data: {
    firstName: 'Foo',
    lastName: 'Bar'
}

// 计算属性
computed: {
    fullName: function () {
      return this.firstName + ' ' + this.lastName
    }
}
```
5. 侦听属性watch，侦听特定的属性，当属性发生变化时，触发侦听属性对应的方法，通常当成一种回调来使用
```
// 属性
data: {
    firstName: 'Foo',
    lastName: 'Bar',
    fullName: 'Foo Bar'
}

// 侦听
watch: {
    firstName: function (val) {
      this.fullName = val + ' ' + this.lastName
    },
    lastName: function (val) {
      this.fullName = this.firstName + ' ' + val
    }
}
```


## 第三方库说明
1. lodash，前端工具类。提供了对array，object和string遍历操作的常用方法，简化函数创建，对值进行操作和检测
2. vue-json-viewer，json查看器支持层级折叠展开
3. v-click-outside-x，一个vue指令`v-click-outside:`的实现，当点击目标元素之外的元素时触发
4. decimal.js，一个的任意精度十进制类型JavaScript库
5. e-guide-layer，一个新手引导的前端实现
6. vuex，用于集中管理前端组件的共享变量
- mapMutation, 把this.myMethod()调用映射为vuex的状态修改(mutation)操作myMethod
- mapAction, 把this.myMethod()调用映射为vuex的异步action操作myMethod

## 杂项问题
1. webpack中的module, chunk和bundle的区别？
module，chunk 和 bundle 其实就是同一份逻辑代码在不同转换场景下的取了三个名字：我们直接写出来的是 module，webpack 处理时是 chunk，最后生成浏览器可以直接运行的 bundle
https://www.cnblogs.com/skychx/p/webpack-module-chunk-bundle.html

2. 文件*.d.ts的作用是啥？
Typescript是javascript的超集，它拥有强类型约束，能够实现静态的代码分析，推断代码中存在的类型错误或进行类型提示。如果我们写的是ts代码，那么就能很好地利用它的这个特性。但是在前端的历史演进中有很多的主流库都是使用javascript编写，而且因为是js所以没有类型信息。用ts来重写这些主流库显然是不现实的，这时候就需要*.d.ts文件来声明javascript中类、方法、属性的类型，这就有点像c语句的头文件。ts只要查看这个声明文件就知道原始js对应的类型，也就能在ts中引入js的实现，并能够进行静态的类型推断了。如果是通过npm安装的依赖支持ts，对应这些声明文件一般在types目录

3. ViewUI的定义文件在哪里？它如何引入组件？
它的js定义路径是，node_modules/view-design/src/index.js
打开文件可以看到，components属性定义了ViewUI所有支持的组件，并通过实现Vue约定的install函数，用Vue.componen()的方式一一把组件注册到Vue上

4. Vue中关于代码复用的几个概念：插件、组件、混入？
插件，是对Vue对应的功能增强，对所有的Vue实例都能产生影响，需要实现约定的install方法
组件，是Vue实例，是对html、css和js的组合
混入，是Vue实例的一个属性，能够实现在多个组件间的代码复用，需要按约定来编写mixin对象

5. 什么是less?
less和css非常像，less仅对css语言增加了少许方便的扩展。具体增加的功能如下，
- 变量
- 混合(Mixins)，把一个规则的属性合并到另一个规则集的方法
- 嵌套(Nesting)，使用嵌套来替代css的层叠的语法
- @规则和冒泡，可以和选择器以相同的方式进行嵌套，@规则会被放到前面，这叫做冒泡
- 运算等

6. 菜单权限如何实现？功能点权限如何实现？
功能点权限在前端的实现，实际上就是对页面元素可见性的控制，通过后端返回功能点列表的结合本项目自定义的`v-privilege`来实现。
自定义指令有多个钩子函数，钩子函数也就是在元素的特定的生命周期点会执行的函数。这个自定义指令在`inserted`的时候被调用
具体的原理如下，如果元素使用`v-privilege`指令，则在元素插入的时候进行可见性判定，指令携带的值在后端返回的数据内则可见，
否则把元素移除。

菜单的权限和功能点的实现类似，也是后端返回权限数据，由前端进行显示控制

7. 不同的路由配置了相同的组件会怎么样？
当使用vue-router时，如果不同的路由使用了相同组件实例，router会自动复用实例。因为组件复用的关系，created函数在路由切换的时候不会重复执行。
如果需要在路由切换的时候更新数据，需要watch路由变化，并做响应的数据更新。
本项目的二级菜单都复用了Main组件