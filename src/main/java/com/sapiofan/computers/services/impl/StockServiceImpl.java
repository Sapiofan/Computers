package com.sapiofan.computers.services.impl;

import com.sapiofan.computers.dao.ComputerDao;
import com.sapiofan.computers.entity.Computer;
import com.sapiofan.computers.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private ComputerDao computerDao;

    @Override
    public List<Computer> getAllComputers() {
        return computerDao.findAll();
    }

    @Override
    public List<Computer> findComputersByName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        return computerDao.findComputersByName(name);
    }

    @Override
    public Computer findById(Long id) {
        if(id == null || id < 0) {
            return null;
        }

        Optional<Computer> computer = computerDao.findById(id);
        return computer.isEmpty() ? null : computer.get();
    }

    @Override
    public boolean payForOrder(String card, String date, Integer cvv) {
        if ((card == null || card.isEmpty()) || (date == null || date.isEmpty())
                || (cvv == null || (cvv < 100 || cvv > 1000))) {
            return false;
        }

        // handle card data or use another service to pay for order
        // if transaction was successful, return true, else - false

        return true;
    }
}
