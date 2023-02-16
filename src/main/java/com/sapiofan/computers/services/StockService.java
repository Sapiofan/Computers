package com.sapiofan.computers.services;

import com.sapiofan.computers.entity.Computer;

import java.util.List;

public interface StockService {
    List<? extends Computer> getAllComputers();

    List<Computer> findComputersByName(String name);

    Computer findById(Long id);

    boolean payForOrder(String card, String date, Integer cvv);
}
