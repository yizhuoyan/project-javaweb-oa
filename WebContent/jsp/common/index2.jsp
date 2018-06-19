<%@page pageEncoding="utf-8"%>
<%@taglib
    prefix="fmt"
    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>NEUSOFT | OA</title>
<%
    String app = request.getContextPath();
    pageContext.setAttribute("app", app);
%>
<meta charset="utf-8">
<meta
    http-equiv="X-UA-Compatible"
    content="IE=edge">
<meta
    content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
    name="viewport">
<base href="<%=app%>/">
<link
    rel="shortcut icon"
    href="favicon.ico" />
<link
    rel="stylesheet"
    href="css/font-awesome.min.css">
<link
    rel="stylesheet"
    href="common.css">
<script src="js/plugins/jquery.min.js"></script>
<script src="common.js"></script>
<style>
    body{
        height: 100vh;
    }
    body>header{
        background: #605ca8;
        height: 50px;
    }
    body>header>h1{
        width: 200px;
        background-color: #555299;
        color: #fff;
        font-size: 150%;
        line-height: 50px;
        text-align: center;
    }
    body>article>nav{
        width: 200px;
        box-sizing: border-box;
        background: #222d32;
    }
    body>article>main{
        background: yellow;
    }
</style>
</head>
<body class="layout-vbox">
    <header class="layout-hbox">
        <h1><b>NEUSOFT-</b>OA</h1>
        <div class="layout-hbox">
            <a  href="javascript:toggleNav()" class="fa fa-bars" ></a>
            <script>
            var toggleNav=function(){
                $("nav").toggle();
            }
            </script>
            
        </div>
    </header>
    <article class="layout-box-grow layout-hbox">
        <nav>
            <a id="dashboardMenuEL" href="dashboard.do" class="module-link" target="mainFrame"><i class="fa fa-dashboard"></i>首页</a>
            
        </nav>
        <main class="layout-box-grow">
            <iframe name="mainFrame"></iframe>
        </main>
    </article>
    
</body>
</html>