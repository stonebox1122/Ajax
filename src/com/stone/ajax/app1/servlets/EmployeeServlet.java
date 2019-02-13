package com.stone.ajax.app1.servlets;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stone.ajax.app1.beans.Department;
import com.stone.ajax.app1.beans.Location;
import com.stone.ajax.app1.dao.BaseDao;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private BaseDao baseDao = new BaseDao();
	
	protected void listLocations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "select location_id locationId,city from locations";
		List<Location> locations = baseDao.getForList(sql, Location.class);
		request.setAttribute("locations", locations);
		request.getRequestDispatcher("/WEB-INF/pages/employees.jsp").forward(request, response);
	}
	
	protected void listDepartments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locationId = request.getParameter("locationId");
		String sql = "select department_id departmentId,department_name departmentName from departments where location_id=?";
		List<Department> departments = baseDao.getForList(sql, Department.class, Integer.parseInt(locationId));
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(departments);
		System.out.println(result);
		response.setContentType("text/javascript");
		response.getWriter().print(result);
	}

}
