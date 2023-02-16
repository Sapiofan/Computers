package com.sapiofan.computers.tests.services;

import com.sapiofan.computers.dao.ComputerDao;
import com.sapiofan.computers.entity.Computer;
import com.sapiofan.computers.entity.Laptop;
import com.sapiofan.computers.entity.Tablet;
import com.sapiofan.computers.services.impl.StockServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockServiceTests {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private ComputerDao computerDao;

    @Test
    public void getAllComputersTest() {
        List<Computer> list = new ArrayList<>();
        Laptop laptop = new Laptop();
        laptop.setName("laptop");
        laptop.setCores(4);

        Laptop laptop2 = new Laptop();
        laptop2.setName("laptop2");
        laptop2.setCores(8);

        Tablet tablet = new Tablet();
        tablet.setName("tablet");
        tablet.setBluetooth(true);

        list.add(laptop);
        list.add(laptop2);
        list.add(tablet);

        when(computerDao.findAll()).thenReturn(list);

        List<Computer> list2 = stockService.getAllComputers();

        Tablet tablet1 = (Tablet) list2.get(2);

        Laptop laptop1 = (Laptop) list2.get(0);

        Assertions.assertEquals(3, list2.size());
        Assertions.assertEquals("tablet", list2.get(2).getName());
        Assertions.assertEquals(true, tablet1.getBluetooth());
        Assertions.assertEquals("laptop", laptop1.getName());
    }

    @Test
    public void findComputerByName() {
        Laptop laptop = new Laptop();
        laptop.setName("laptop");
        laptop.setOs("os");
        laptop.setRam("ram");

        List<Computer> list = new ArrayList<>();
        list.add(laptop);

        when(computerDao.findComputersByName("laptop")).thenReturn(list);

        Laptop laptop1 = (Laptop) stockService.findComputersByName("laptop").get(0);

        Assertions.assertNotNull(laptop1);
        Assertions.assertEquals("os", laptop1.getOs());
        Assertions.assertEquals("ram", laptop1.getRam());
    }

    @Test
    public void findComputerByNameFailure() {
        Laptop laptop1 = (Laptop) stockService.findComputersByName(null);
        Laptop laptop2 = (Laptop) stockService.findComputersByName("");

        Assertions.assertNull(laptop1);
        Assertions.assertNull(laptop2);
    }

    @Test
    public void findById() {
        Laptop laptop = new Laptop();
        laptop.setId(1l);
        laptop.setScreenDiagonal("16.7");
        laptop.setBrand("brand");

        when(computerDao.findById(1l)).thenReturn(Optional.of(laptop));

        Laptop laptop1 = (Laptop) stockService.findById(1l);

        Assertions.assertNotNull(laptop1);
        Assertions.assertEquals("16.7", laptop1.getScreenDiagonal());
        Assertions.assertEquals("brand", laptop1.getBrand());
    }

    @Test
    public void findByIdFailure() {
        when(computerDao.findById(2l)).thenReturn(Optional.empty());

        Assertions.assertNull(stockService.findById(-1l));
        Assertions.assertNull(stockService.findById(2l));
    }

    @Test
    public void payForOrderFailure() {
        Assertions.assertFalse(stockService.payForOrder("", "date", 238));
        Assertions.assertFalse(stockService.payForOrder("xxxx-xxxx-xxxx-xxxx", null, 238));
    }
}
