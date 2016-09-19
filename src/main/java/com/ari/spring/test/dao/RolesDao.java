/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ari.spring.test.dao;

import com.ari.spring.test.entity.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ari-prasetiyo
 */
public interface RolesDao extends PagingAndSortingRepository<Roles, String> {

    @Query("select r from Roles r where r.nameRole = :name_role ")
    public Page<Roles> findByRolesName(@Param("name_role") String nameRole, Pageable page);
}
