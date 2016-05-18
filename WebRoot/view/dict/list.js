var listJs = {
	list_tmp : "",
	curPage:1,
	searchList:function(){
		listJs.curPage=1;
		listJs.pageInit();
	},
	pageInit : function() {
		var opt = {
			dictId:$("#dictId").val(),
			currPage:listJs.curPage
		};
		$.post(path + "/sso/dict/getList.do", opt, function(res) {
			$("#xiye").html(listJs.curPage);
			listJs.curPage =parseInt(listJs.curPage)+1;
			$("#mo").html(res.totolPage);
			sysCommon.judgeState(res, function() {
				var htmlStr = "";
				
				$.each(res["object"], function(key, value) {

					if(value.dictId == '10001'){
						value.dictName='餐厅地址';
					}
					if(value.dictId == '10002'){
						value.dictName='积分类型';
					}
					// 使用模板组装数据，形成页面HTML代码
					htmlStr += sysCommon.tmpdata(listJs.list_tmp, value);
				});
				// 将HTML代码展示到页面上
				$("#list_tmp").removeClass("none").html(htmlStr);

				listJs.bindClick();
				
			}, null);
			listJs.addClassForTd();
		});
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
	
	bindClick : function() {

		$("a[name=deleteButton]").each(function() {
			var id = $(this).attr("data-id");
			
			$(this).click(function() {
					sysCommon.updateConfirm("确认删除字典项信息", function(){
					
					var opt = {
							itemId : id
						};
					$.post(path + "/sso/dict/deleteDictItem.do", opt, function(res) {
						if(res.state=="0"){
							sysCommon.updateAlert(res.data);
						}else{
							sysCommon.updateAlertFun("操作成功",function(){
								window.location.href=path+"/sso/dict/index.do?url=dict/list";
							});
						}
					});
				});
			});
		});
	}
};

$(function() {
	// 菜单样式初始化
	sysCommon.setMenuChecked("3");
	listJs.list_tmp = $("#list_tmp").html();
	listJs.pageInit();
	listJs.addClassForTh(); 
	sysCommon.queryType = "3";
});