// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router';
import store from './store';
import ViewUI from 'view-design';
import i18n from '@/locale';
import config from '@/config';
import importDirective from '@/directives';
import JsonViewer from 'vue-json-viewer';
import _ from 'lodash';
// import { directive as clickOutside } from 'v-click-outside-x';
import * as vClickOutside from 'v-click-outside-x';
import installPlugin from '@/plugins';
import './themes/index.less';
import '@/assets/icons/iconfont.css';
import 'slick-carousel/slick/slick.css';
import { Decimal } from 'decimal.js';
// 枚举管理
import Enum from 'vue-enum';
import enumInfo from '@/constants';
// 处理table操作按钮
import tableAction from './lib/table-action';

// 时间
import moment from 'moment';

import 'e-guide-layer/dist/e-guide-layer.css'
import eGuideLayer from 'e-guide-layer'

Vue.use(eGuideLayer);

Vue.prototype.$tableAction = tableAction;
//*********************************************************//
// Vue.use()方法向Vue引入第三方插件，实现上就是在Vue对象添加插件的
// 1. 全局方法和属性, Vue.myGlobalMethod = xxx, 全局的方法和属性无法被new Vue()的实例继承
// 2. 添加全局的指令, Vue.directive()
// 3. 注入组件的选项, Vue.mixin()
// 4. 添加实例方法, Vue.prototype.$myMethod = xxx
//*********************************************************//
Vue.use(Enum, { enumInfo });
Vue.use(ViewUI, {
  i18n: (key, value) => i18n.t(key, value)
});
Vue.use(JsonViewer);
Vue.use(vClickOutside);


Number.prototype.toFixed = function (length) {
  let x = new Decimal(this);
  return x.toFixed(length);
};

//时间处理
//设置语言 或 moment.lang('zh-cn');
moment.locale('zh-cn'); 
//*********************************************************//
// 赋值使用, 向Vue的原型添加属性, 以便Vue在实例化时能够继承这个类
//*********************************************************//
Vue.prototype.$moment = moment;


/**
 * @description 注册admin内置插件
 */
installPlugin(Vue);
/**
 * @description 生产环境关掉提示
 */
Vue.config.productionTip = false;
/**
 * @description 全局注册应用配置
 */
Vue.prototype.$config = config;
/**
 * 注册指令
 */
importDirective(Vue);
// Vue.directive('clickOutside', clickOutside);

window._ = _;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  i18n,
  store,
  render: h => h(App)
});
