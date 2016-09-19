/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ari.spring.test.service;

import com.ari.spring.test.dao.RolesDao;
import com.ari.spring.test.entity.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author ari-prasetiyo
 */
public class RolesService {
    
    public static Roles getRolesByName(RolesDao rd , String findByRoleName) {
        
        Pageable page = new PageRequest(0, 1);
        Page<Roles> resultRoleName = rd.findByRolesName(findByRoleName, page);
        
        Roles resultAfter = new Roles();
        for (Roles o : resultRoleName) {
            resultAfter = o;
        }
        return resultAfter;
    }
}
