var editJs = {
	list_tmp : "",
	getInfo : function() {
		var itemId = $("#itemId").val();
		var opt = {
			itemId : itemId
		};

		$.post(path + "/sso/dict/getDictItemById.do", opt, function(res) {
			if (res.state == "0") {
				sysCommon.updateAlert(res.data);
			} else {
				if(res.object.dictId=="10001"){
					$("#dictName").html("餐厅名称");
				}else if(res.object.dictId=="10002"){
					$("#dictName").html("积分类型");
				}
				$("#dictId").val(res.object.dictId);
				$("#name").val(res.object.name);
				$("#seq").val(res.object.seq);
				$("#reserve1").val(res.object.reserve1);
				$("#reserve2").val(res.object.reserve2);
			}
		});

	},
	save : function() {

		if (!Validator.Validate($("#form1")[0])) {
			return;
		}

		var opt = {
				itemId : $("#itemId").val(),
				dictId : $("#dictId").val(),
				name : $("#name").val(),
				seq : $("#seq").val(),
				reserve1 : $("#reserve1").val(),
				reserve2 : $("#reserve2").val()
			};


		$.post(path + "/sso/dict/modifyDictItem.do", opt, function(res) {

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
	editJs.getInfo();
	$("#userbutton").click(function() {
		editJs.save();
	});

});