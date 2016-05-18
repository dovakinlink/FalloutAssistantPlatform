var updateJs = {
	lognr : "",
	
	getLogFileList : function() {
		
		$.post(path + "/sso/log/getFileList.do", null, function(res) {
			updateJs.initSelect("logFile",res);
		});
	},
	// 初始化Select菜单
	initSelect : function(name, values) {

		var html = "";
		for ( var i = 0; i < values.length; i++) {
			html += "<option value=\"" + values[i] + "\">"
					+ values[i] + "</option>";
		}
		$("#" + name).html(html);

	},
	getLognr : function() {
		var opt = {
			logFile : $("#logFile").val(),
			num : $("#num").val(),
			charset : $("#charset").val(),
		};
		$.post(path + "/sso/log/getFileInfo.do", opt, function(res) {
			$("#lognr").val(res);
		},'text');
	}
};
$(function() {
	//菜单初始化
	sysCommon.setMenuChecked("7");
	updateJs.lognr = $("#lognr").html();
	$("#lognr").html("");
	$("#search").click(function(){
		updateJs.getLognr();
	});
	updateJs.getLogFileList();
});