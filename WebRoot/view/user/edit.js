var userJs = {
	list_tmp : "",
	getInfo : function() {
		var userId = $("#userId").val();
		var opt = {
			userId : userId
		};
		var sessionId = $("#sessionId").val();

		$.post(path + "/sso/user/getUserInfoById.do", opt, function(res) {
			if (res.state == "0") {
				sysCommon.updateAlert(res.data);
			} else {
				$("#userName").html(res.object.userName);
				$("#userRealName").val(res.object.userRealName);
				$("#userType")[0].selectedIndex = res.object.userType;
				if (sessionId == userId) {
					$('#userType').attr("disabled", "disabled");
				}
			}
		});

	},
	save : function() {

		if (!Validator.Validate($("#form1")[0])) {
			return;
		}

		var opt = {
			userId : $("#userId").val(),
			userRealName : $("#userRealName").val(),
			userType : $("#userType").val()
		};

		$.post(path + "/sso/user/modify.do", opt, function(res) {

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
	userJs.getInfo();
	$("#userbutton").click(function() {
		userJs.save();
	});

});