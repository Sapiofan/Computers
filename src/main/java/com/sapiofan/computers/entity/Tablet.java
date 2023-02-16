package com.sapiofan.computers.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tablets")
public class Tablet extends Computer {

    @Column(nullable = false, name = "main_camera")
    private String mainCamera;

    @Column(nullable = false, name = "front_camera")
    private String frontCamera;

    @Column(nullable = false)
    private Boolean bluetooth;

    @Column(name = "housing_material")
    private String housingMaterial;

    public Tablet() {
    }

    public Tablet(String name, String brand, Integer price, String mainCamera, String frontCamera, Boolean bluetooth) {
        super(name, brand, price);
        this.mainCamera = mainCamera;
        this.frontCamera = frontCamera;
        this.bluetooth = bluetooth;
    }

    public String getMainCamera() {
        return mainCamera;
    }

    public void setMainCamera(String mainCamera) {
        this.mainCamera = mainCamera;
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public void setFrontCamera(String frontCamera) {
        this.frontCamera = frontCamera;
    }

    public Boolean getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(Boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getHousingMaterial() {
        return housingMaterial;
    }

    public void setHousingMaterial(String housingMaterial) {
        this.housingMaterial = housingMaterial;
    }

    @Override
    public String toString() {
        String res = super.toString();
        res = res.substring(0, 8) + ": Tablet :" + res.substring(8) +
                "\nMain camera = " + mainCamera + " MP" +
                "\nFront camera = " + frontCamera + " MP" +
                "\nBluetooth = " + (bluetooth ? "Yes" : "No") +
                "\nHousing material = " + housingMaterial;
        return res;
    }
}
