package com.stone.ajax.app1.test;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Customer {
	private String name;
	private String id;
	public Customer(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}
	public String getCustomerName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JsonIgnore
	public String getCity() {
		return "BeiJing";
	}
	public String getBirth() {
		return "2019-12-12";
	}
	
	public static void main(String[] args) throws JsonProcessingException {
		//1.导入Jar包
		//2.创建ObjectMapper对象
		ObjectMapper mapper = new ObjectMapper();
		
		//3.调用mapper的writeValueAsString()方法把一个对象或者集合转为一个JSON字符串
		Customer customer = new Customer("stone","1001");
		String jsonStr = mapper.writeValueAsString(customer);
		System.out.println(jsonStr);
		
		//4.注意：Jackson使用getter方法来定位JSON对象的属性，而不是字段
		//5.可以通过在类的getter方法上添加注解：com.fasterxml.jackson.annotation.JsonIgnore忽略某一个getter定义的属性
		
		List<Customer> customers = Arrays.asList(customer, new Customer("tom", "2002"));
		jsonStr = mapper.writeValueAsString(customers);
		System.out.println(jsonStr);		
	}	
}