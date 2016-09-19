/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ari.spring.test.dao;

import com.ari.spring.test.SpringTestApplication;
import com.ari.spring.test.entity.Employee;
import com.ari.spring.test.entity.Roles;
import com.ari.spring.test.service.RolesService;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringTestApplication.class)
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao ed;

    @Autowired
    private RolesDao rd;

    RolesService roleService;

    @Test
    public void testReada() {
        Roles role = roleService.getRolesByName(rd, "User");
        Assert.assertEquals(role.getNameRole(), "User");
    }

    @Test
    @Ignore
    public void testInsert() {

        Employee emp = new Employee();
        emp.setName("Ari Prasetiyo");
        emp.setRoles(roleService.getRolesByName(rd, "Admin"));
        ed.save(emp);
        //Test save data
        Assert.assertNotNull(emp.getName());

        emp = new Employee();
        emp.setName("Ari");
        emp.setRoles(roleService.getRolesByName(rd, "Admin"));
        ed.save(emp);
        //Test save data
        Assert.assertNotNull(emp.getName());

        emp = new Employee();
        emp.setName("Ari P");
        emp.setRoles(roleService.getRolesByName(rd, "Operator"));
        ed.save(emp);
        //Test save data
        Assert.assertNotNull(emp.getName());

        emp = new Employee();
        emp.setName("Prasetiyo");
        emp.setRoles(roleService.getRolesByName(rd, "Operator"));
        ed.save(emp);
        //Test save data
        Assert.assertNotNull(emp.getName());

        emp = new Employee();
        emp.setName("Ari Pras");
        emp.setRoles(roleService.getRolesByName(rd,"User"));
        ed.save(emp);
        //Test save data
        Assert.assertNotNull(emp.getName());
    }
}
