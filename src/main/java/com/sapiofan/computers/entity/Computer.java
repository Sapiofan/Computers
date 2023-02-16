package com.sapiofan.computers.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "computers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Integer price;

    @OneToOne(cascade = CascadeType.ALL)
    private Note note;

    public Computer() {
    }

    public Computer(String name, String brand, Integer price) {
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Computer: " +
                "\nID = " + id +
                "\nName = " + name +
                "\nBrand = " + brand +
                "\nPrice = " + price;
    }
}
