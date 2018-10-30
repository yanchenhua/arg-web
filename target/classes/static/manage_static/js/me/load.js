var load = {};
//避免重复
var load_obj = null ;
load.install = function (Vue, options) {
    Vue.prototype.$load = function (tips) {
        if ( load_obj ){
            return load_obj ;
        }
        var loadTpl = Vue.extend({     // 1、创建构造器，定义好提示信息的模板
            template: '<w-load></w-load>'
        });
        var tpl = new loadTpl().$mount();  // 2、创建实例，挂载到文档以后的地方
        document.body.appendChild(tpl.$el);     // 3、把创建的实例添加到body中
        load_obj = tpl ;
        return tpl;
    }
}
Vue.use(load);
