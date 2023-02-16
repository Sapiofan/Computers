package com.sapiofan.computers.controllers;

import com.sapiofan.computers.entity.Computer;
import com.sapiofan.computers.entity.Note;
import com.sapiofan.computers.services.NoteService;
import com.sapiofan.computers.services.impl.StockServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private StockServiceImpl stockService;

    @Autowired
    private NoteService noteService;

    @GetMapping("/")
    public List<Computer> getAllComputers() {
        return stockService.getAllComputers();
    }

    @GetMapping("/{id}")
    public Computer getComputerById(@PathVariable("id") Long id) {
        return stockService.findById(id);
    }

    @GetMapping("/computer")
    public List<Computer> getComputerByName(@RequestParam("name") String name) {
        return stockService.findComputersByName(name);
    }

    @PostMapping("/order")
    public Note orderComputer(@RequestParam("phone") String phone, @RequestParam("address") String address,
                              @RequestParam("device") Long deviceId, @RequestParam("card") String card,
                              @RequestParam("date") String date, @RequestParam("cvv") Integer cvv,
                              HttpServletResponse response) {
        if(stockService.payForOrder(card, date, cvv)) {
            Computer device = stockService.findById(deviceId);
            if((phone == null || phone.isEmpty()) || (address == null || address.isEmpty()) || device == null) {
                response.setStatus(422);
                return null;
            }
            Note note = noteService.addNote(new Note(phone, address, device));
            response.setStatus(201);
            return note;
        } else {
            response.setStatus(422);
        }

        return null;
    }
}
