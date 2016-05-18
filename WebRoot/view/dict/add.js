var addJs = {
	list_tmp : "",
	save : function() {

		if (!Validator.Validate($("#form1")[0])) {
			return;
		}

		var opt = {
			dictId : $("#dictId").val(),
			name : $("#name").val(),
			seq : $("#seq").val(),
			reserve1 : $("#reserve1").val(),
			reserve2 : $("#reserve2").val()
		};

		$.post(path + "/sso/dict/saveDictItem.do", opt, function(res) {

			if (res.state) {

				if (res.state == "0") {
					sysCommon.updateAlert(res.data);
				} else {

					sysCommon.updateAlertFun("保存字典信息成功", function() {
						window.location.href = path
								+ "/sso/dict/index.do?url=dict/list";
					});

				}

			} else {

				if (res.split("div").length > 1) {
					sysCommon.updateAlertFun("登录超时，请重新登录", function() {
						window.location.href = path;
					});
					return;
				}
			}

		});
	}
};

$(function() {
	// 菜单样式初始化
	sysCommon.setMenuChecked("3");

	$("#addbutton").click(function() {
		addJs.save();
	});

});