var sysConfig = {
	saveXml : function() {

		sysCommon.updateConfirm('确定对配置信息进行修改?', function() {

			if (!Validator.Validate($("#form1")[0])) {
				return;
			}
			var parm = "";
			$("#contentInfo input").each(
					function() {
						parm += "\"" + ($(this).attr("id")) + "\":\""
								+ $(this).val() + "\",";
					});
			parm = "{" + parm.substring(0, parm.length - 1) + "}";

			$.post(path + "/sso/sysConfig/save.do", jQuery.parseJSON(parm),
					function(res) {

						if (res.state) {

							sysCommon.judgeState(res, function() {

								sysCommon.updateAlert(res["data"]);

							}, null);
						} else {

							if (res.split("div").length > 1) {
								sysCommon.updateAlertFun("登录超时，请重新登录",
										function() {
											window.location.href = path
													+ "/Login/index.do";
										});
								return;
							}
							sysCommon.updateAlert("操作成功");
						}

					});
		});
	}

};

$(function() {
	// 菜单样式初始化
	sysCommon.setMenuChecked("6");
	

	$
			.post(
					path + "/sso/sysConfig/getAllConfigInfo.do",
					null,
					function(res) {
						if (res["state"]) {
							var message = res["data"];
							sysCommon.updateAlert(message);
							return;
						}
						var content = $("#contentInfo");
						var html = "<div style=\"height:395px;overflow-y:auto;width: 94%;position: absolute;" +
								"left: 3%;margin-top: 30px;\"><table class = \"systemConfigTb\" style=\"width: 100%;border: 1px solid #DDD;\">";

						var json = eval(res);
						for ( var i = 0; i < json.length; i++) {
							if (json[i].showEnable == 'false') {
								continue;
							}
							html += "<tr><td style = \"height:30px;text-indent:30px; width:50%;\">"
									+ json[i].nodeDesc
									+ "：</td><td><input type=\"text\"  maxlength=\"200\"  style = \"width:61%;\" id=\""
									+ json[i].path
									+ "\" name=\""
									+ json[i].path
									+ "\" value=\""
									+ json[i].nodeValue
									+ "\"  dataType=\"Require\" msg=\"值不能为空\"  /></td>";
						}
						html += "</table></div>";
						content.html(html);
						$(".systemConfigTb tr").each(function(){
							$(this).mouseover(function(){
								$(this).addClass("datagrid-row-over");
							});
							$(this).mouseout(function(){
								$(this).removeClass("datagrid-row-over");
							});
						});

					});

	$("#saveButton").click(function() {
		sysConfig.saveXml();
	});
});