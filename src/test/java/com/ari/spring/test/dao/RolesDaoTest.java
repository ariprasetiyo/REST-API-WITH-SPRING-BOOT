/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ari.spring.test.dao;

import com.ari.spring.test.SpringTestApplication;
import com.ari.spring.test.entity.Roles;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 *
 * @author ari-prasetiyo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringTestApplication.class)
@SuiteClasses({RolesDaoTest.class, EmployeeDaoTest.class})
public class RolesDaoTest {
    
    @Autowired
    private RolesDao ds;
    
    @Test
    public void testInsert() {
        Roles insert = new Roles();
        insert.setNameRole("Admin");
        insert.setCanCreate(true);
        insert.setCanDelete(true);
        insert.setCanRead(true);
        insert.setCanUpdate(true);
        ds.save(insert);
      
        insert = new Roles();
        insert.setNameRole("Operator");
        insert.setCanCreate(true);
        insert.setCanDelete(false);
        insert.setCanRead(true);
        insert.setCanUpdate(false);
        ds.save(insert);
        
        
        insert = new Roles();
        insert.setNameRole("User");
        insert.setCanCreate(false);
        insert.setCanDelete(false);
        insert.setCanRead(true);
        insert.setCanUpdate(false);
        ds.save(insert);
        
        String uuid = insert.getId();
        //Test save data
        Assert.assertNotNull(uuid);
    }
    
}
