package com.wyb.test.spring;

public class Wheel {

    private String brand;

    private String specification ;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @Override
    public String toString() {
        return "com.wyb.test.spring.Wheel{" +
                "brand='" + brand + '\'' +
                ", specification='" + specification + '\'' +
                '}';
    }
}
