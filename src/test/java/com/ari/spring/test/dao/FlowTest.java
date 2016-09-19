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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringTestApplication.class)
public class FlowTest {

    @Autowired
    EmployeeDao emp;

    @Autowired
    RolesDao rd;

    @Test
    public void testUpdateEmploye() {
        String userName = "Ari Prasetiyo";
        Employee empResult = emp.findOne(userName);
        Assert.assertEquals(true, empResult.getRoles().isCanUpdate());
        if (empResult.getRoles().isCanUpdate()) {

            Roles roles1 = RolesService.getRolesByName(rd, "Operator");

            Employee emptUpdate1 = emp.findOne(userName);
            emptUpdate1.setRoles(roles1);
            emp.save(emptUpdate1);

            Roles roles2 = RolesService.getRolesByName(rd, "User");

            Employee empOperator = emp.findOne(userName);
            empOperator.setRoles(roles2);
            emp.save(empOperator);

            Employee empSelect = emp.findOne(userName);
            Assert.assertEquals("User", empSelect.getRoles().getNameRole());

        }
    }
}
