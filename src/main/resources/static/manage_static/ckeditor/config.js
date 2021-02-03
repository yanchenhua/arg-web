/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here.
	// For complete reference see:
	// http://docs.ckeditor.com/#!/api/CKEDITOR.config

	// The toolbar groups arrangement, optimized for two toolbar rows.
	// config.toolbar = [
	// 	{ name: 'document',	   items: [ 'Source' ] },
	// 	{ name: 'clipboard',   items: [ 'Cut','Copy', 'Paste','PasteText','Undo','Redo'] },
	// 	{ name: 'links' },
	// 	{ name: 'insert' ,items: ['Image','Table','Link','HorizontalRule','SpecialChar','html5video']},
     //    //{ name: 'insert', groups: [ 'insert' ] },
	// 	{ name: 'others' ,items:['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock']},
	// 	{ name: 'basicstyles' ,items: ['Bold','Italic','Underline','Strike']},
	// 	{ name: 'styles' ,items: ['Format']},
	// 	{ name: 'colors',items: ['TextColor','BGColor'] }
    //
    //
    //
	// ];

	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';

	// Set the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';

	// Simplify the dialog windows.
	//config.removeDialogTabs = 'image:advanced;link:advanced';

    config.filebrowserBrowseUrl = '';
    config.filebrowserImageBrowseUrl = '';
//    config.filebrowserHtml5videoUploadUrl ='';
//     config.extraPlugins = 'html5video';
    config.removeDialogTabs = 'image:advanced;image:Link';//隐藏超链接与高级选项
    config.filebrowserImageUploadUrl = "";//上传图片的地址
    config.filebrowserHtml5videoUploadUrl = "";//上传视频的地址
    config.extraPlugins = 'html5video,html5audio';
    //config.extraPlugins = 'html5video';
    //config.extraPlugins = '';
    config.image_previewText = ' '//设置图片预览默认值为' '

};
