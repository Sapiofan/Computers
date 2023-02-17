package com.sapiofan.computers.dao;

import com.sapiofan.computers.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComputerDao extends JpaRepository<Computer, Long> {

    @Query("select c from Computer c where lower(c.name) like lower(:name) or lower(c.brand) like lower(:name)")
    List<Computer> findComputersByName(String name);
}
