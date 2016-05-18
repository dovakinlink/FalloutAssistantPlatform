<#macro index_top>
<div class="container">
		<div class="page-header">
            <div class="page-header-top"></div>
			<div class="page-header-top2">
				<span class="page-header-top-left"><span class="nihao">你好，${Session.sysUserSessionInfo.userRealName!}</span></span>
				<span class="page-header-top-right"> 
				<a class="tc"
					href="${path}">退出</a> 
				<a class="xgmm"
								 href="${path!}/sso/user/index.do?url=user/editPwd&userId=${Session.sysUserSessionInfo.userId!}" >修改密码</a>
				
				</span>
			</div>
			<div class="page-header-content report_header">
				<div class="logo2">
					<a href="javascript:void(0)"><span class="">欢迎使用管理系统</span></a>
				</div>
				<div class="menu2  fr"></div>
			</div>
		</div>
		</div>
		
		
		<div id="mainPage" class="page-main">

			<div id="mainLayout">

				<div data-options="region:'west',split:true" id="subMenu" title="二级菜单" style="width: 160px;">
					<div class="erji-menu">
						<ul id="smallmenu" style="height:100%;overflow-y:auto;overflow-x: hidden;"></ul>
					</div>
				</div>
				<div id="content-center" data-options="region:'center'" style="width: 100%;"></div>
			</div>
		</div>

		
		<script type="text/javascript">
		var topMenu="<ul id='bigmenu' class='menu_con_detail'><li class=''><a href='javascript:void(0)' id='topmenuid1' data-url='/sso/topage.do?url=main/main'><div class = 'bgdiv'>首页<div></a></li>";
		  topMenu+="<li class=''><a href='javascript:void(0)' id='topmenuid2' data-url='/Update/index.do?url=update/list' >升级配置</a></li>";
		  topMenu+="<li class=''><a href='javascript:void(0)' id='topmenuid3' data-url='/sso/dict/index.do?url=dict/list' >字典管理</a></li>";
		  topMenu+="<li class=''><a href='javascript:void(0)' id='topmenuid4' data-url='/Plugin/index.do?url=plugin/list' >插件管理</a></li>";
		  topMenu+="<li class=''><a href='javascript:void(0)' id='topmenuid5' data-url='/sso/user/index.do?url=user/list'>用户管理</a></li>";
		  topMenu+="<li class=''><a href='javascript:void(0)' id='topmenuid6' data-url='/sso/topage.do?url=sysconfig/sysConfig'>系统配置</a></li>";
		  topMenu+="<li class=''><a href='javascript:void(0)' id='topmenuid7' data-url='/sso/log/index.do?url=log/list'>日志查看</a></li>";
	      topMenu+="</ul>";
	      $('.menu2').html(topMenu);
	      
	      $(".menu2 a").each(function() {
				$(this).click(function() {
					window.location.href = path + $(this).attr("data-url");
				});
			});
	
	

</script>
</#macro>