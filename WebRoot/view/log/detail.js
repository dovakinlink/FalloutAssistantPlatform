var updateJs = {
	getDetail : function() {

		var opt = {
			logId : $("#logId").val()
		};

		$.post(path + "/sso/log/detail.do", opt, function(res) {

			if (res.result == "false") {

				sysCommon.updateAlertFun(res.data, function() {
					window.history.back();
				});
			}
			var bean = res.bean;
			$("#type").html(bean.type);
			$("#clientId").html(bean.clientId);
			$("#createTime").html(bean.createTime);
			$("#request").val(bean.request);
			$("#result").val(bean.result);

		});

	}

};

$(function() {
	// 菜单样式初始化
	sysCommon.setMenuChecked("7");
	updateJs.getDetail();
});