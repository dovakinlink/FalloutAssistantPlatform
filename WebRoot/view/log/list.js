var updateJs = {
	list_tmp : "",
	curPage:1,
	getList:function(){
		updateJs.curPage =1;
		updateJs.getListPage();
	},
	
	addClassForTh : function(){
		$(".tablesorter th").each(function(){
			$(this).mouseover(function(){
				$(this).addClass("datagrid-row-over");
			});
			$(this).mouseout(function(){
				$(this).removeClass("datagrid-row-over");
			});
		});
	},
	addClassForTd : function(){
		$(".tablesorter tr").each(function(){
			$(this).mouseover(function(){
				$(this).addClass("datagrid-row-over");
			});
			$(this).mouseout(function(){
				$(this).removeClass("datagrid-row-over");
			});
		});
	},
	getListPage : function() {

		var opt ={
				type:$("#type").val(),
				clientId:$("#clientId").val(),
				timeStart:$("#timeStart").val(),
				timeEnd:$("#timeEnd").val(),
				currPage:updateJs.curPage
		};
		
		$.post(path + "/sso/log/getList.do", opt, function(res) {
			sysCommon.judgeState(res, function() {
				$("#xiye").html(updateJs.curPage);
				updateJs.curPage =parseInt(updateJs.curPage)+1;
				$("#mo").html(res.totolPage);
				var htmlStr = "";
				$.each(res["object"], function(key, value) {

					if(value.state=='1'){
						value.state="成功";
					}else{
						value.state="失败";
					}
					
					// 使用模板组装数据，形成页面HTML代码
					htmlStr += sysCommon.tmpdata(updateJs.list_tmp, value);
				});
				// 将HTML代码展示到页面上
				$("#list_tmp").removeClass("none").html(htmlStr);
				updateJs.bindAllButton();
			}, null);
			updateJs.addClassForTd();
		});

	},
	bindAllButton:function(){
		$("a[name=detailButton]").each(function() {

			$(this).click(function() {
				
				var id = $(this).attr("data-id");
				
				window.location.href=path+"/sso/log/index.do?url=log/detail&logId="+id;
				
			});
		});
	},
	toCheckLogFile:function(){
		window.location.href=path+"/sso/log/index.do?url=log/checkLogFile";
	}

};

$(function() {
	// 菜单样式初始化
	sysCommon.setMenuChecked("7");
	updateJs.list_tmp = $("#list_tmp").html();
	$("#list_tmp").html("");
	$("#search").click(function(){
		updateJs.getList();
	});
	//日志文件查看
	$("#checkLogFile").click(function(){
		updateJs.toCheckLogFile();
	});
	updateJs.getList();
	
	updateJs.addClassForTh();
	
	sysCommon.queryType = "1";
});