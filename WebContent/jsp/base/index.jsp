<%@page pageEncoding="utf-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 <title>NEUSOFT | OA</title>
 <%@include file="/WEB-INF/jspf/head.jsp"%>
 <style type="text/css">
 	.treeview-menu>li.active>a,li.singleView.active>a{
 		background: #fff !important;
 		color:#000 !important;
 		position:relative;
 		right:-1px;
 	}
 	
 </style>
</head>
<body class="skin-purple fixed">
<div class="wrapper">
  <!-- Main Header -->
  <header class="main-header">
    <!-- Logo -->
    <a href="javascript:window.top.dashboard()" target="mainFrame" class="logo" title="点击回到首页">
      <span class="logo-lg"><b>NEUSOFT-</b>OA</span>
    </a>
	<c:set var="u" value="${sessionScope.loginUser}"></c:set>
    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="javascript:" class="sidebar-toggle" data-toggle="push-menu" role="button"></a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <li class="dropdown messages-menu">
            <!-- Menu toggle button -->
            <a href="javascript:" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-envelope-o"></i>
              <span class="label label-success">4</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">你有4条新消息</li>
              <li>
                <ul class="menu">
                  <li>
                      <div class="pull-left">
                        <img src="img/avatar/avatar2.png" width="50" height="50" class="img-circle" >
                      </div>
					  <p>	                      
                      	<h4>Support Team
                      		<small>
                        		<i class="fa fa-clock-o"></i> 5 mins
                        	</small>
                      	</h4>
                      </p>
                      <p>Why not buy a new awesome theme?</p>
                  </li>
                </ul>
              </li>
              <li class="footer"><a href="#">See All Messages</a></li>
            </ul>
          </li>
          <!-- /.messages-menu -->

          <!-- Notifications Menu -->
          <li class="dropdown notifications-menu">
            <!-- Menu toggle button -->
            <a href="javascript:" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <span class="label label-warning">10</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 10 notifications</li>
              <li>
                <!-- Inner Menu: contains the notifications -->
                <ul class="menu">
                  <li><!-- start notification -->
                    <a href="javascript:">
                      <i class="fa fa-users text-aqua"></i> 5 new members joined today
                    </a>
                  </li>
                  <!-- end notification -->
                </ul>
              </li>
              <li class="footer"><a href="javascript:">View all</a></li>
            </ul>
          </li>
          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <a href="javascript:" class="dropdown-toggle" data-toggle="dropdown">
              <img src="${app}/${u.avatar}" width="25" height="25" class="user-image">
              <span class="hidden-xs">${u.name}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
                <img src="${app}/${u.avatar}" class="img-circle" width="80" height="80">
                <p>
                   	${u.name} - 东软集团
                  <small>工龄：18年</small>
                </p>
              </li>
              <li class="user-body">
                <div class="row">
                  <span class="col-xs-4 text-center">
                    <a href="javascript:" onclick="window.top.openMenu(this.textContent)">我的账号</a>
                  </span>
                  <span class="col-xs-4 text-center">
                    <a href="javascript:" onclick="window.top.openMenu(this.textContent)">问题反馈</a>
                  </span>
                  <span class="col-xs-4 text-center">
                    <a href="javascript:" onclick="window.top.openMenu(this.textContent)" >帮助/关于</a>
                  </span>
                </div>
                <!-- /.row -->
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="logout.do" class="btn btn-default btn-flat">修改密码</a>
                </div>
                <div class="pull-right">
                  <a href="javascript:exitSystem()" class="btn btn-default btn-flat">注销退出</a>
                </div>
                <script>
                	var exitSystem=function(){
                		if(window.confirm("确认退出?")){
                			var url="logout.ajax";
                			$.post(url,function(resp){
                				window.opener=null;
                    			window.location.href="";
                			})
                		}
                	}
                </script>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <aside class="main-sidebar">
    <section class="sidebar">
      
      
      <ul id="menuBox" class="sidebar-menu" data-widget="tree">
        <li class="singleView active">
          <a id="dashboardMenuEL" href="dashboard.do" class="module-link" target="mainFrame"><i class="fa fa-dashboard"></i>首页</a>
        </li>
        <!-- 
         <li class="treeview">
          <a href="javascript:">
          	<i class="fa fa-link"></i> <span>系统管理</span>
            <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
          	<li><a href="sysmodule/list.do" target="mainFrame">功能模块管理</a></li>
          	<li><a href="sysuser/list.do" target="mainFrame">用户管理</a></li>
          	<li><a href="sysrole/list.do" target="mainFrame">系统角色管理</a></li>
          </ul>
        </li>
         -->
       </ul>
    </section>
  </aside>
  <main class="content-wrapper">
    <iframe name="mainFrame" src="dashboard.do"></iframe>
  </main>
</div>


<script title="加载用户功能模块">
	$(function(){
		loadUserModule();
		
	});
	var loadUserModule=function(){
		var url="loadmodules.ajax";
		$.getJSON(url,function(resp){
			if(resp.code==="ok"){
				var data=resp.data;
				paintMenu(data);
				addModuleLinkClickListener();
			}else{
				alert("无法加载用户菜单:"+resp.message);
			}	
		})
	};
	
	/**
	绘制菜单
	*/
	var paintMenu=function(menu){
		var menuBox=$("#menuBox");	
		for(var i=0;i<menu.length;i++){
			var menuItem=createMenuItem(menu[i]);
			menuBox.append(menuItem);
		}
	}
	var createMenuItem=function(item){
		var html='<li class="treeview">'
		
        html+='<a><i class="fa fa-link"></i>';
        html+='<span>'+item.name+'</span>';
        html+='<span class="pull-right-container">'
        html+='<i class="fa fa-angle-left pull-right"></i>';
        html+='</span></a>';
        
        var children=item.children;
        if(children){
        	html+='<ul class="treeview-menu">';
	        for(var i=0;i<children.length;i++){
	        	var child=children[i];
	        	html+='<li><a class="module-link" href="';
	        	var url=child.url;
	        	if(url){
	        		if(url.charAt(0)=='/'){
		        		url=url.substr(1);
		        	}	
	        	}
	        	html+=url;
	        	html+='" target="mainFrame">';
	        	html+=child.name;
	        	html+='</a></li>';
	        }
        	html+='</ul>';
        }
      	html+='</li>';
      	return html;
	};
	
	var addModuleLinkClickListener=function(evt){
		$(".module-link").click(function(evt){
			$(".active").removeClass("active");
			$(this).parents("li").addClass("active");
			//evt.preventDefault();
		});
	}
	/**
	*用于打开指定菜单,如openMenu("修改密码")
	*
	*/
	window.top.openMenu=function(menu){
		if(menu==="首页"){
			document.getElementById("dashboardMenuEL").click();
			return;
		}
		$(".module-link",window.top.document).filter(function(){
			if(this.textContent===menu){
				this.click();
			}
		});
	}
	window.top.dashboard=function(){
		window.top.openMenu('首页');
	}
</script>
</body>
</html>