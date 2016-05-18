var userJs = {
	list_tmp : "",
	curPage:1,
	pageInit : function() {
		var opt = {
			deleteFlag : '1',
			currPage:userJs.curPage
		};
		$.post(path + "/sso/user/getListUser.do", opt, function(res) {
			$("#xiye").html(userJs.curPage);
			userJs.curPage =parseInt(userJs.curPage)+1;
			$("#mo").html(res.totolPage);
			sysCommon.judgeState(res, function() {
				var htmlStr = "";
				
				$.each(res["object"], function(key, value) {

					if (value.userType == "0") {
						value.type = "管理员";
					} else {
						value.type = "第三方用户";
					}
					
					value.buttonName="删除";
					// 使用模板组装数据，形成页面HTML代码
					htmlStr += sysCommon.tmpdata(userJs.list_tmp, value);
				});
				// 将HTML代码展示到页面上
				$("#list_tmp").removeClass("none").html(htmlStr);

				userJs.bindClick();
				
			}, null);
			userJs.addClassForTd();
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

		$("a[name=resetButton]").each(function() {

			$(this).click(function() {
				
				var id = $(this).attr("data-id");
				
				sysCommon.updateConfirm("确认将密码重置为初始值", function(){
					
					var opt = {
							userId : id
						};
					$.post(path + "/sso/user/reset.do", opt, function(res) {
						if(res.state=="0"){
							sysCommon.updateAlert(res.data);
						}else{
							sysCommon.updateAlertFun("操作成功",function(){
								window.location.href=path+"/sso/user/index.do?url=user/list";
							});
							
						}
					});
				});
				
			});
		});

		$("a[name=deleteButton]").each(function() {
			var id = $(this).attr("data-id");
			if(id== $("#userId").val()){
				$("a[id="+id+"]").remove();
			}
			$(this).click(function() {
					
					
					sysCommon.updateConfirm("确认删除用户信息", function(){
					
					var opt = {
							userId : id
						};
					$.post(path + "/sso/user/delete.do", opt, function(res) {
						if(res.state=="0"){
							sysCommon.updateAlert(res.data);
						}else{
							sysCommon.updateAlertFun("操作成功",function(){
								window.location.href=path+"/sso/user/index.do?url=user/list";
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
	sysCommon.setMenuChecked("5");
	userJs.list_tmp = $("#list_tmp").html();
	userJs.pageInit();
	userJs.addClassForTh(); 
	sysCommon.queryType = "3";
});