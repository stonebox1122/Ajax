<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(function() {
		$("a").click(function() {
			//使用load方法处理Ajax
			var url = this.href;
			var args = {"time":new Date()};
			$("#content").load(url, args);
			return false;
		})
	})
</script>
</head>
<body>
	<a href="helloAjax.txt">Hello Ajax</a>
	<div id="content"></div>
</body>
</html>