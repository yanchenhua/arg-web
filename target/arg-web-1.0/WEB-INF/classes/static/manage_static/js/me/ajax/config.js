var config = {
    baseURL: '/',
    headers: {'content-type': 'application/json'}
}

var instance = axios.create(config);
var vue = new Vue();
//初始化load组件
var _load = vue.$load();

// 添加请求拦截器
instance.interceptors.request.use(function (config) {
    //打开
    //config.headers['Content-Type'] = 'application/x-www-form-urlencoded'
    _load.open();
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});
// 添加响应拦截器
instance.interceptors.response.use(function (response) {
    //请求结束后关闭
    _load.close();
    return response;
}, function (error) {
    // 对响应错误做点什么
    _load.close();
    console.error(error);
    //if ( error.status == 500 ){
    alert(error.message);
    // }
    return Promise.reject(error);
});
var ajax = {};
ajax.install = function (Vue, options) {
    Vue.prototype.$ajax = instance;
}
Vue.use(ajax);