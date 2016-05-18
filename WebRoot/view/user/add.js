var userJs = {
	list_tmp : "",
	save : function() {

		if (!Validator.Validate($("#form1")[0])) {
			return;
		}

		var password = $("#password").val();
		var password2 = $("#password2").val();

		if (password != password2) {
			sysCommon.updateAlert("两次输入的密码不一致");
			return;

		}
		var opt = {
			userName : $("#userName").val(),
			userRealName : $("#userRealName").val(),
			password : $("#password").val(),
			userType : $("#userType").val()
		};

		$.post(path + "/sso/ser/save.do", opt, function(res) {

			if (res.state) {

				if (res.state == "0") {
					sysCommon.updateAlert(res.data);
				} else {

					sysCommon.updateAlertFun("保存用户信息成功", function() {
						window.location.href = path
								+ "/sso/user/index.do?url=user/list";
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
	sysCommon.setMenuChecked("5");

	$("#userbutton").click(function() {
		userJs.save();
	});

});