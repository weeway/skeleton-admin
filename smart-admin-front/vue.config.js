const path = require('path');
const resolve = dir => {
  return path.join(__dirname, dir);
};
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const CompressionWebpackPlugin = require('compression-webpack-plugin');
const productionGzipExtensions = ['js', 'css'];

const publicPath = process.env.NODE_ENV === 'production' ? '/' : '/';
const lintOnSave = process.env.NODE_ENV === 'production';

module.exports = {
  publicPath,
  // tweak internal webpack configuration.
  // see https://github.com/vuejs/vue-cli/blob/dev/docs/webpack.md
  // 如果你不需要使用eslint，把lintOnSave设为false即可
  lintOnSave,
  chainWebpack: config => {
    config.entry = {
      // https://webpack.docschina.org/concepts/entry-points/
      // 打包起点设置有两个:babel-polyfill(1)和./src/main(2)
      // (1)用于兼容旧浏览器, (2)我们编写程序的入口
      main: ['babel-polyfill', './src/main'],
      // vendor第三方库的入口，比如jQuery等
      vendors: './src/vendors'
    };
    config.module
      .rule('view-design')
      .test(/view-design.src.*?js$/)
      .use('babel')
      // 将ES6语法转为ES5语法
      .loader('babel-loader')
      .end();
    // https://webpack.docschina.org/configuration/resolve/
    // 在import或require时，把别名处理成对应的路径
    config.resolve.alias
      .set('@', resolve('src'))
      .set('_c', resolve('src/components'));
  },
  // 设为false打包时不生成.map文件
  productionSourceMap: false,
  configureWebpack: {
    plugins: [
      // 开启gzip压缩
      new CompressionWebpackPlugin({
        algorithm: 'gzip',
        test: new RegExp('\\.(' + productionGzipExtensions.join('|') + ')$'),
        threshold: 10240,
        minRatio: 0.8
      })
    ],
    optimization: {
      minimizer: [
        new UglifyJsPlugin({
          uglifyOptions: {
            compress: {
              warnings: true,
              drop_console: true, // console
              drop_debugger: true,
              pure_funcs: ['console.log'] // 移除console
            }
          }
        })
      ]
    }
  }
};
