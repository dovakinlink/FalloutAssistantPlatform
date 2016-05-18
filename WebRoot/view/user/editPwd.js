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
			userId : $("#userId").val(),
			passwordOld : $("#passwordOld").val(),
			password : password
		};

		$.post(path + "/sso/user/modifyPwd.do", opt, function(res) {

			if (res.state) {

				if (res.state == "0") {
					sysCommon.updateAlert(res.data);
				}else{
					sysCommon.updateAlertFun("修改成功，请重新登录",function(){
						window.location.href = path;
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