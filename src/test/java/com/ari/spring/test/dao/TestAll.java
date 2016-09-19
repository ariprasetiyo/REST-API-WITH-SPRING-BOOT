/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ari.spring.test.dao;

import com.ari.spring.test.SpringTestApplication;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 *
 * @author ari-prasetiyo
 */
@RunWith(Suite.class)
@SpringApplicationConfiguration(classes = SpringTestApplication.class)
@Suite.SuiteClasses({DeleteAll.class, FlowTest.class, RolesDaoTest.class, EmployeeDaoTest.class, DeleteAll.class})
public class TestAll {


}
