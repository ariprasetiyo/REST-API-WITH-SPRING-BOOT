/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ari.spring.test.dao;

import com.ari.spring.test.SpringTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author ari-prasetiyo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringTestApplication.class)
public class DeleteAll {
    
    @Autowired
    private RolesDao ds;
    
    @Autowired
    private EmployeeDao ed;

    @Test
    public void deleteAllTest() {
        ed.deleteAll();
        ds.deleteAll();
    }
}
