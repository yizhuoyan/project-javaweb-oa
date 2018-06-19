<%@page pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
	 <%@include file="/WEB-INF/jspf/head.jsp"%>
	</head>
<body class="layout-vbox">
    <nav class="nav-bar">
      <a
            href="javascript:history.back()"
            class="fa fa-back"></a>
        <ol class="breadcrumb">
            <li>
                <a
                    href="javascript:window.top.dashboard()"
                    class="fa fa-dashboard">首页 </a>
            </li>
            <li>个人中心</li>
            <li>
                <a href="jsp/userhome/my-account.jsp">我的账号</a>
            </li>
            <li>
                <b>修改我的头像</b>
            </li>
        </ol>
    </nav>
		<main class="pad">
            <p>
                *请选择头像文件(仅支持gif、png格式，不得超过2m):
            </p>
			<form  action="user/modifyAvatar.do"  method="post" enctype="multipart/form-data" >
					<p>
						<input type="file" name="avatar"  accept=".png,.gif" class="" id="avatarEL" >
                        
                        <button type="submit" class="btn">提交</button>
					</p>

			</form>

		</main>
		
	</body>

</html>