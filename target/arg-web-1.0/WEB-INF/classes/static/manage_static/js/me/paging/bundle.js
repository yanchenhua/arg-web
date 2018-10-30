/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// identity function for calling harmony imports with the correct context
/******/ 	__webpack_require__.i = function(value) { return value; };
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 70);
/******/ })
/************************************************************************/
/******/ ({

/***/ 0:
/***/ (function(module, exports) {

// this module is a runtime utility for cleaner component module output and will
// be included in the final webpack user bundle

module.exports = function normalizeComponent (
  rawScriptExports,
  compiledTemplate,
  scopeId,
  cssModules
) {
  var esModule
  var scriptExports = rawScriptExports = rawScriptExports || {}

  // ES6 modules interop
  var type = typeof rawScriptExports.default
  if (type === 'object' || type === 'function') {
    esModule = rawScriptExports
    scriptExports = rawScriptExports.default
  }

  // Vue.extend constructor export interop
  var options = typeof scriptExports === 'function'
    ? scriptExports.options
    : scriptExports

  // render functions
  if (compiledTemplate) {
    options.render = compiledTemplate.render
    options.staticRenderFns = compiledTemplate.staticRenderFns
  }

  // scopedId
  if (scopeId) {
    options._scopeId = scopeId
  }

  // inject cssModules
  if (cssModules) {
    var computed = Object.create(options.computed || null)
    Object.keys(cssModules).forEach(function (key) {
      var module = cssModules[key]
      computed[key] = function () { return module }
    })
    options.computed = computed
  }

  return {
    esModule: esModule,
    exports: scriptExports,
    options: options
  }
}


/***/ }),

/***/ 16:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 38:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

/* harmony default export */ __webpack_exports__["default"] = ({
    name : 'w-paging' ,
    props : {
        //总条数
        total : {
            type : String | Number , 
            default : 0 
        },
        //当前第几页
        current : {
            type : String | Number , 
            default : 1 
        },
        //每页数量
        page_size : {
            type : Number , 
            default : 10 
        }
    },
    data : function(){
        return {
            _total : 0 ,
            _current : 0 ,
            _page_size : 0 ,
            total_page_num : 0 ,
            show_paging_num : 6 ,
            start_num : 0 ,
            end_num : 0 ,
            show_paging_list : []
        }
    },
    created : function(){
        this._total = parseInt(this.total) ;
        this._current = parseInt(this.current) ;
        this._page_size = this.page_size ;
    },
    mounted : function(){
      
        this.init();
        // this.end_num = this.start_num + this. show_paging_num ;
        //show_paging_list
    },
    methods : {
        switch_page : function(rst){
            //num.target.value
            var _page_num = 0 ;
            if ( typeof rst == 'object'){
                _page_num = parseInt(rst.target.value) ;
            }else{
                _page_num = rst ;
            }
           this._current = _page_num ;
           this.init();
           this.$emit('switch_page',this._current);
        },
        init : function(){
            //总页数
            this.total_page_num = Math.ceil(this._total / this._page_size) ;
            //总页数 小于 总显示页数
            if ( this.total_page_num < this.show_paging_num  ){
                this.start_num = 1 ;
                this.end_num = this.total_page_num ;
            }
            //当前页 <= 总页
            else if ( this._current <=3 ){
                this.start_num = 1  ;
                this.end_num = this.show_paging_num ;
            }
            //当前页 + 3 >= 总页
            else if (this._current + 3 >= this.total_page_num ){
                this.start_num = this._current - 3  ;
                this.end_num = this.total_page_num ;
            }
            else{
                this.start_num = this._current - 3  ;
                this.end_num = this._current + 3 ;
            }
            this.show_paging_list = [] ;
            for(var i = this.start_num ; i <= this.end_num ; i ++ ){
                this.show_paging_list.push(i);
            }
        }
    },
    watch : {
       total : function(n,o){
           this._total = parseInt(this.total) ;
           this.init();
       },
       current :  function(n,o){
            this.switch_page(n);
       }
    }
});   


/***/ }),

/***/ 52:
/***/ (function(module, exports, __webpack_require__) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "pagination"
  }, [(_vm._current > 1) ? _c('li', [_c('a', {
    attrs: {
      "href": "javascript:void(0);"
    },
    on: {
      "click": function($event) {
        _vm.switch_page(_vm._current - 1)
      }
    }
  }, [_vm._v("上一页")])]) : _vm._e(), _vm._v(" "), _vm._l((_vm.show_paging_list), function(num) {
    return _c('li', {
      class: {
        active: num == _vm._current
      }
    }, [_c('a', {
      attrs: {
        "href": "javascript:void(0);"
      },
      on: {
        "click": function($event) {
          _vm.switch_page(num)
        }
      }
    }, [_vm._v(_vm._s(num))])])
  }), _vm._v(" "), (_vm._current != _vm.total_page_num) ? _c('li', [_c('a', {
    attrs: {
      "href": "javascript:void(0);"
    },
    on: {
      "click": function($event) {
        _vm.switch_page(_vm._current + 1)
      }
    }
  }, [_vm._v("下一页")])]) : _vm._e(), _vm._v(" \n    跳转至\n    "), _c('select', {
    staticClass: "sel-page",
    on: {
      "change": _vm.switch_page
    }
  }, _vm._l((_vm.total_page_num), function(num) {
    return _c('option', {
      domProps: {
        "value": num,
        "selected": num == _vm._current
      }
    }, [_vm._v(_vm._s(num))])
  })), _vm._v("页 共" + _vm._s(_vm.total_page_num) + "页，" + _vm._s(_vm.total) + "条")], 2)
},staticRenderFns: []}
module.exports.render._withStripped = true
if (false) {
  module.hot.accept()
  if (module.hot.data) {
     require("vue-hot-reload-api").rerender("data-v-2d6bad3c", module.exports)
  }
}

/***/ }),

/***/ 70:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__w_paging_vue__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__w_paging_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0__w_paging_vue__);

Vue.component('w-paging', __WEBPACK_IMPORTED_MODULE_0__w_paging_vue___default.a);

/***/ }),

/***/ 8:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(16)

var Component = __webpack_require__(0)(
  /* script */
  __webpack_require__(38),
  /* template */
  __webpack_require__(52),
  /* scopeId */
  "data-v-2d6bad3c",
  /* cssModules */
  null
)
Component.options.__file = "/Users/ruanjingwang/Documents/work/wee0/project/component/admin/src/paging/w-paging.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key !== "__esModule"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] w-paging.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-2d6bad3c", Component.options)
  } else {
    hotAPI.reload("data-v-2d6bad3c", Component.options)
  }
})()}

module.exports = Component.exports


/***/ })

/******/ });