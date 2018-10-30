/* 将所有接口统一起来便于维护
 * 如果项目很大可以将 url 独立成文件，接口分成不同的模块
 */
var _vue = new Vue();
function requestParam(name) {
    var url = location.href ;
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var list = url.split("?") ;
    if ( list.length <=1 ){
        return null ;
    }
    var r = url.split("?")[1].match(reg);
    if (r != null) return (r[2]); return null;
}
function getTranslationsText(data,key,locale){
    var obj = data[key];
    if ( !obj ){
        return '' ;
    }
    var translations =  data[key].translations ;

    if ( translations && translations.length > 0 ){
        if ( locale ){
            for(var i in translations){
                var translation = translations[i];
                if ( translation.locale.toLowerCase() == locale.toLowerCase() ){
                    return translation.text ;
                }
            }
            return '';
        }
        return translations[0].text ;
    }
    return '';
}

/**
 * 设置翻译公共函数
 * @param data 数据对象
 * @param key 对象key
 * @param locale 语言
 * @param text 内容
 * @returns {string}
 */
function setTranslationsText(data,key,locale,text){
    var obj = data[key];
    if ( obj == null ){
        data[key] = {} ;
        data[key].translations = [];
    }
    var translations =  data[key].translations ;

    if ( !translations ){
        data[key].translations = [];
        translations =  data[key].translations ;
    }
    if ( locale ){
        var flag = false ;
        for(var i in translations){
            var translation = translations[i];
            if ( translation.locale.toLowerCase() == locale.toLowerCase() ){
                translation.text = text ;
                return obj;
            }
        }
        if ( flag == false ){
            var new_translation = {};
            new_translation.locale = locale;
            new_translation.language = locale;
            new_translation.text = text ;
            data[key].translations.push(new_translation);
        }
        return obj;
    }
    return '';
}
function setDataToEditor(el,text){
    if ( !CKEDITOR.instances[el] ){
        return ;
    }
    CKEDITOR.instances[el].setData(text,{callback:function(){
        //增加回调，如果没有设置值，使用原始方案，在body中，填值。
        var content = CKEDITOR.instances[el].getData();
        if(content == "") {
            this.document.$.body.innerHTML=text;
        }
    }});
}
function deleteSuccess(){
    alert('删除成功！');
    if ( app.data_source_config.param ){
        var _param = app.data_source_config.param ;
        var random = Math.random();
        app.$set(_param,'random',random);
    }
}
var page = {
    get : function(){
        return sessionStorage.getItem('page_num') ;
    },
    set : function(num){
        sessionStorage.setItem('page_num',num);
    }
}

//将模块安装到Vue作为组件
var tools = {};
tools.install = function (Vue, options) {
    Vue.prototype.$tools = {
        requestParam : requestParam ,
        getTranslationsText : getTranslationsText ,
        setTranslationsText : setTranslationsText ,
        setDataToEditor : setDataToEditor ,
        deleteSuccess : deleteSuccess ,
        page : page
    };
}
Vue.use(tools);
var file = {
    upload : function(param,callback,exception_callback){
        //创建一个空的FormData对象   
        var data = new FormData();
        //使用FormData.append来添加键/值对到表单里面;
        data.append('file', param.file);
        data.append('id',param.id || 0);
        data.append('type',param.type);

        _vue.$ajax.post('/uploads/upload1',data,{headers: {'Content-Type': 'multipart/form-data'}}).then(function(response){
            if ( callback )
            callback(response.data);
            // return response.data ;
        }).catch(function(exception){
            if (exception_callback){
                exception_callback(exception);
            }
        })
        
    }
}
//base
var base = {
    //添加用户
    add : function(model,param,callback,exception_callback){
        _vue.$ajax.post('app/rest/'+model+'/saveOrUpdate',param).then(function(response){
            if ( callback )
            callback(response.data);
            // return response.data ;
        }).catch(function(exception){
            if (exception_callback){
                exception_callback(exception);
            }
        })
    },
    //查询所有
    all : function(model,param,callback){
        _vue.$ajax.get('app/rest/'+model+'/all',param).then(function(response){
            if ( callback )
            callback(response.data);
            // return response.data ;
        }).catch(function(exception){
            return exception ;
        })
    },
    //查询
    query : function(model,param,callback){
        if ( param == null || typeof param == 'undefined' ){
            param = {} ;
        }
        var pageCount = param.pageCount || 1 ;
        var pageSize = param.pageSize || 10 ;
        _vue.$ajax.post('app/rest/'+model+'/pagination/'+pageCount+'/'+pageSize+'/',param).then(function(response){
            if ( callback )
            callback(response.data);
            // return response.data ;
        }).catch(function(exception){
            return exception ;
        })
    },
    //删除
    'delete' : function(model,id,callback){
        if ( !id ){
            alert('参数错误，无法删除！');
        }
        var url = 'app/rest/'+model+'/delete/'+id ;
         _vue.$ajax.get(url).then(function(response){
            if ( callback )
            callback(response.data);
            // return response.data ;
        }).catch(function(exception){
            console.error( exception );
        })
    }
}


//将模块安装到Vue作为组件
var api = {};
api.install = function (Vue, options) {
    Vue.prototype.$api = {
        base : base ,
        file : file
    };
}
Vue.use(api);