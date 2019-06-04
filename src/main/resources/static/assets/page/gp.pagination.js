/*
 * create by wangjiehan
 * 2019-06-03
 * gp.pagination.js
 * 
 * 全页面刷新分页控件
 */

//------------------page control-------------------//

//----------- html part ---------//
// 初始化 pageBar 上下页 以及 select 选择
function pageControl(){
	var dataCount = $("#dataCount").val();
	var pageNum = $("#pageNum").val();
	var str = "<ul class='page'>";
	var pageBar = $("#pageBar");
	str += "<li>共"+ dataCount +"条</li>";
	str += "<ul class='page'>";
	str += "<li class='page-item page-first' onclick='prePage()'></li>";
	str += "<div id='pageItems'></div>";
	str += "<li class='page-item page-last' onclick='nextPage()'></li>";
	str += "<li><select id='pageSelect' onchange='selectChange()'>";
	str += "<option value='5'>5条/页</option>";
	str += "<option value='10'>10条/页</option>";
	str += "<option value='30'>30条/页</option>";
	str += "</select></li>";
	str += "<li class='page-options-jumper'>前往<input id='goNum' type='input' value='"+pageNum+"'>页</input></li>";
	str += "</ul>";
	pageBar.append(str);
	
	pageItemsInit(pageBar);	//初始化分页列表
	pageCheckColor();		//初始化选中颜色控制
	bindSelectOption();		//绑定选择页面
	enter();				//绑定回车
}
function buildJumpBtn(type){
	return "<li class='page-item' onclick=jumpfunc('"+ type +"')>...</li>";
}
function buildPageClick(pn){
	return "<li class='page-item' onclick='pageClick("+ pn +")'>"+ pn +"</li>";
}
function pageItemsInit(pageBar) {
	var pageItems = $("#pageItems");
	var pageNum = $("#pageNum").val();
	var pageSize = $("#pageSize").val();
	var pageCount = $("#pageCount").val();

	if (pageCount == 0) {
		pageBar.attr("disabled", "disabled");
	} else if (pageCount <= 6) {
		for (i = 1; i < parseInt(pageCount) + 1; i++) {
			pageItems.append(buildPageClick(i));
		}
	} else {
		//pg=7,加载pageNum之前
		if (pageNum > 6) {
			pageItems.append(buildPageClick(1));
			pageItems.append(buildJumpBtn("forward"));
			for (i = pageNum - 2; i <= pageNum; i++) {
				pageItems.append(buildPageClick(i));
			}

			if (parseInt(pageNum) + 5 < pageCount) {
				for (i = parseInt(pageNum) + 1; i < parseInt(pageNum) + 3; i++) {
					pageItems.append(buildPageClick(i));
				}
				pageItems.append(buildJumpBtn("backward"));
				pageItems.append(buildPageClick(pageCount));
			} else {
				for (i = parseInt(pageNum) + 1; i <= pageCount; i++) {
					pageItems.append(buildPageClick(i));
				}
			}
		} else if (pageNum == 6) {
			//加载右侧
			for (i = 1; i < 7; i++) {
				pageItems.append(buildPageClick(i));
			}
			//加载左侧
			if (parseInt(pageNum) + 5 < pageCount) {
				for (i = 7; i < 9; i++) {
					pageItems.append(buildPageClick(i));
				}
				pageItems.append(buildJumpBtn("backward"));
				pageItems.append(buildPageClick(pageCount));
			} else {
				for (i = 7; i < parseInt(pageCount) + 1; i++) {
					pageItems.append(buildPageClick(i));
				}
			}
		} else {
			for (i = 1; i < 7; i++) {
				pageItems.append(buildPageClick(i));
			}
			pageItems.append(buildJumpBtn("backward"));
			pageItems.append(buildPageClick(pageCount));
		}
	}
}

//----------- action part ---------//
// jump btn ...
function buildJumpBtn(type){
	return "<li class='page-item' onclick=jumpfunc('"+ type +"')>...</li>";
}
function jumpfunc(direction) {
	var pageNum = $("#pageNum").val();
	if(direction=="forward")
		pageNum = parseInt(pageNum) - 5;
	else
		pageNum = parseInt(pageNum) + 5;
	buildUrl(pageNum);
}

function getPageSize() {
	return $("#pageSelect option:selected").val();
}

//select
function bindSelectOption() {
	$("#pageSelect").val($("#pageSize").val());
}
function selectChange() {
	buildUrl($("#pageNum").val());
}

//pre or next
function prePage() {
	var pageNum = $("#pageNum").val();
	if (pageNum != 1) {
		pageNum = parseInt(pageNum) - 1;
	}
	buildUrl(pageNum);
}
function nextPage() {
	var pageNum = $("#pageNum").val();
	pageNum = parseInt(pageNum) + 1;
	buildUrl(pageNum);
}

//pageclick
function pageClick(pageNum) {
	buildUrl(pageNum);
}

//enter key
function enter() {
	$(document).keyup(function(e) {//捕获文档对象的按键弹起事件  
		if (e.keyCode == 13) {//按键信息对象以参数的形式传递进来了  
			//此处编写用户敲回车后的代码  
			var pageNum = $("#goNum").val();
			buildUrl(pageNum);
		}
	});
}

//check active pagebtn
function pageCheckColor() {
	var pageNum = $("#pageNum").val();
	$("#pageItems").children("li").each(function() {
		if ($(this).text() == pageNum) {
			$(this).addClass("active");
		}
	});
}