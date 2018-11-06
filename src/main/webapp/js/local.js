// onload
$(function() {
	//加载页面顶部工具栏
	load_header();
	//加载左侧导航栏
	load_nav();
	//加载主页
	load_main();
});

// 加载页面顶部工具栏
function load_header() {
	$('#header').load('page/header.html');
}


// 加载左侧导航栏
function load_nav() {
	$('#siderbar').load('page/nav.html', function(){
		register_nav();
	});
}

// 加载主页
function load_main() {
	$('#main').load('page/user.html', function(){
		$.getScript("js/user.js");
	});
}

// 注册导航栏单击事件
function register_nav() {
	$(".nav-sidebar li").click(function(){
		//设置单击的li选中
		$(this).siblings("li").removeClass("active");
		$(this).addClass("active");
		//更换正文
		var target = $(this).children("a").attr("name");
		$('#main').load(target);
		//为正文页面远程加载js
		load_script($(this));
	});
}

// 远程加载功能模块的js
function load_script($li) {
	var target = $li.children("a").attr("name");
	var name = target.substring(target.lastIndexOf("/")+1, target.lastIndexOf(".html"));
	if("user"==name){
		$.getScript("js/common/ajaxfileupload.js");
	}
	$.getScript("js/" + name + ".js");
}