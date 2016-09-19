/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ari.spring.test.dao;

import com.ari.spring.test.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ari-prasetiyo
 */
public interface EmployeeDao extends PagingAndSortingRepository<Employee, String> {

    @Query("select e from Employee e where e.name = :nameEmployee ")
    public Page<Employee> findEmployeeFilterByName(@Param("nameEmployee") String nameRole, Pageable page);

    @Query("from Employee e join e.roles r "
    		+ " where r.nameRole = :nnameRole ")
    public Page<Employee> findEmployeeFilterByRole(@Param("nnameRole") String nameRole, Pageable page);

    @Query("from Employee e join e.roles r where r.canCreate = :ncanCreate and r.canRead =:ncanRead "
    		+ " and r.canUpdate = :ncanUpdate and r.canDelete = :ncanDelete ")
    public Page<Employee> findEmployeeFilterByPermission(@Param("ncanCreate") boolean ncanCreate
    		, @Param("ncanRead") boolean ncanRead,@Param("ncanUpdate") boolean ncanUpdate
    		, @Param("ncanDelete") boolean ncanDelete, Pageable page);
 
    @Query("from Employee e join e.roles r "
    		+ " where r.nameRole = :nnameRole  and e.name = :nameEmployee ")
    public Page<Employee> findEmployeeFilterByNameAndRole(@Param("nnameRole") String nameRole, @Param("nameEmployee") String nameEmployee 
    		, Pageable page);
    
    @Query("from Employee e join e.roles r where e.name = :nnameEmployee and "
    		+ " r.canCreate = :ncanCreate and r.canRead =:ncanRead "
    		+ " and r.canUpdate = :ncanUpdate and r.canDelete = :ncanDelete ")
    public Page<Employee> findEmployeeFilterByRoleAndPermission(@Param("nnameEmployee") String name
    		, @Param("ncanCreate") boolean ncanCreate
    		, @Param("ncanRead") boolean ncanRead,@Param("ncanUpdate") boolean ncanUpdate
    		, @Param("ncanDelete") boolean ncanDelete, Pageable page);
 
//    @Query("select e from Employee r where r.name = :nameEmployee ")
//    public Page<Employee> findEmployeeFilterByRoleAndPermission(@Param("nameEmployee") String nameRole, Pageable page);

}
