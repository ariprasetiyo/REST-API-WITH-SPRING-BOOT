/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ari.spring.test.controller;

import com.ari.spring.test.dao.EmployeeDao;
import com.ari.spring.test.dao.RolesDao;
import com.ari.spring.test.entity.Employee;
import com.ari.spring.test.entity.Roles;
import com.ari.spring.test.service.RolesService;
import com.fasterxml.jackson.databind.node.TextNode;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ari-prasetiyo
 */
// curl -H "Content-Type: application/json" -X POST -d
// '{"username":"xyz","password":"xyz"}' http://localhost:3000/api/login
// curl -H "Authorization: bearer 1d2aa734-5b9c-4c4b-a4bc-16b7dd28345a" -H
// "Content-Type: application/json" -X POST -d
// '{"username":"xyz","password":"xyz"}' localhost:8080/api/insertEmployee
@RestController
@RequestMapping("/api")
public class restApiEmployee {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeDao ed;

	@Autowired
	private RolesDao rd;

	@RequestMapping(value = "/read/testConnection", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	private Map<Object, String> testConnection() {
		Map<Object, String> map = new HashMap<Object, String>();
		map.put("status_connection", "ok");
		return map;
	}

	@RequestMapping(value = { "/insert/Employee",
			"/update/Employee" }, method = RequestMethod.POST, headers = {
					"content-type=application/json" })
	@ResponseStatus(HttpStatus.CREATED)
	private void insertEmployee(@RequestBody Employee employee) {
		String nameString = employee.getName();
		String roleString = employee.getRoleName();
		logger.debug("-- name : " + nameString + ", role : " + roleString);
		Roles roleEntity = RolesService.getRolesByName(rd, roleString);
		Employee insertEmployee = new Employee();
		insertEmployee.setName(nameString);
		insertEmployee.setRoles(roleEntity);
		ed.save(insertEmployee);
	}

	@RequestMapping(value = "/delete/Employee/{name}", method = RequestMethod.DELETE, headers = {
			"content-type=application/json" })
	@ResponseStatus(HttpStatus.OK)
	private void deleteEmployee(@PathVariable("name") String name) {
		ed.delete(name);
	}

	@RequestMapping(value = "/read/Employee/{name}", method = RequestMethod.GET, headers = {
			"content-type=application/json" })
	private ResponseEntity<Employee> readEmployeeByName(
			@PathVariable("name") String name) {
		Employee emp = ed.findOne(name);
		if (emp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(emp, HttpStatus.FOUND);
	}

	@RequestMapping(value = "/read/Employee/All", method = RequestMethod.GET, headers = {
			"content-type=application/json" })
	private Page<Employee> readEmployeeAll(Pageable page) {
		return ed.findAll(page);
	}

	@RequestMapping(value = "/read/Employee/{filterByRole}", method = RequestMethod.GET, headers = {
			"content-type=application/json" })
	private Page<Employee> readEmployeeFilterByRole(@PathVariable("filterByRole") String filterByRole,Pageable page) {
		
		
		return ed.findEmployeeFilterByName(filterByRole, page);
	}

	@RequestMapping(value = "/read/Employee/{ncanCreate}/{ncanRead}/{ncanUpdate}/{ncanDelete}", method = RequestMethod.GET, headers = {
			"content-type=application/json" })
	private Page<Employee> readEmployeeFilterByPermission( 
			 @PathVariable("ncanCreate") boolean ncanCreate
			,@PathVariable("ncanRead") boolean ncanRead
			,@PathVariable("ncanUpdate") boolean ncanUpdate
			,@PathVariable("ncanDelete") boolean ncanDelete
			, Pageable page) {
		return ed.findEmployeeFilterByPermission(ncanCreate, ncanRead, ncanUpdate, ncanDelete, page);
	}
	
	@RequestMapping(value = "/read/Employee/{nameEmployee}/{nameRole}", method = RequestMethod.GET, headers = {
	"content-type=application/json" })
	private Page<Employee> readEmployeeFilterByNameAndRole(
			@PathVariable("nameEmployee") String nameEmployee,
			@PathVariable("nameRole") String nameRole, Pageable page) {
		return ed.findEmployeeFilterByNameAndRole(nameRole, nameEmployee, page);
	}
}
