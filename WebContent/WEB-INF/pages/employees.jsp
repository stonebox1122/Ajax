<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
<!-- 
	1.获取#city，添加change响应函数
	2.使#department只保留第一个option子节点
	3.获取#city选择的值，若该值为"",即选择的是"请选择...",此时不需要发送Ajax请求
	4.若值不为""，说明的确是city发生了改变，准备Ajax请求
		4.1 url:EmployeeServlet?method=listDepartments
		4.2 args:locationId,time
	5.返回的是JSON数组
		5.1 若返回数组中的元素为0，提示："当前城市没有部门"
		5.2 若返回数组中元素不为0，遍历，创建<option value='departmentId'>departmentName</option>
			并把新创建的option节点加为#department的子节点
 -->
<script type="text/javascript">
	$(function() {
		$("#city").change(function() {
			$("#department option:not(:first)").remove();
			var city = $(this).val();
			if (city != "") {
				var url = "EmployeeServlet?method=listDepartments";
				var args = {"locationId":city,"time":new Date()};
				$.getJSON(url, args, function(data) {
					if (data.length == 0) {
						alert("当前城市没有部门");
					} else {
						for (var i = 0; i < data.length; i++) {
							var deptId = data[i].departmentId;
							var deptName = data[i].departmentName;
							$("#department").append("<option value='" + deptid + "'>" + deptName + "</option>");
						}
					}
				});
			}
		});
	})
</script>
</head>
<body>
	<center>
		<br><br>
		City:
		<select id="city">
			<option value="">请选择...</option>
			<c:forEach items="${locations }" var="location">
				<option value="${location.locationId }">${location.city }</option>
			</c:forEach>
		</select>
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		Department:
		<select id="department">
			<option value="">请选择...</option>
			
		</select>
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		Employee:
		<select id="employee">
			<option value="">请选择...</option>
			
		</select>
		
		<br><br>
		<table id="empdetails" border="1" cellspacing="0" cellpadding="5" style="display: none">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Email</th>
				<th>Salary</th>
			</tr>
			<tr>
				<td id="id"></td>
				<td id="name"></td>
				<td id="email"></td>
				<td id="salary"></td>
			</tr>
		</table>
	</center>
</body>
</html>