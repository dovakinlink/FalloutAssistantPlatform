var loginJs = {
	login : function() {
		if (!Validator.Validate($("#form1")[0])) {
			return;
		}
		var opt = {
			userName : $("#username").val(),
			password : $("#password").val()
		};
		var d = sysCommon.updateLoading("正在验证用户信息");
		$.post(path + "/login/checkUserInfo.do", opt, function(res) {
			
			d.close();
			
			sysCommon.judgeState(res, function() {
				window.location.href = path + "/sso/topage.do?url=main/main";
			}, null);
		});

	}
};

$(function() {
	$("#username").val("");
	$("#password").val("");
	$("#username").focus();
	$("#loginImg").click(function() {
		loginJs.login();
	});
	// 需增加回车事件
	// $("#username").keypress(loginJs.inputKey);
	// $("#password").keypress(loginJs.inputKey);
	$("#password").keypress(function(event) {
		if (event.keyCode == 13) {
			loginJs.login();
		}

	});
});