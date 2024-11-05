package com.example.aungsithumoe.calculatorforwood;

/**
 * Created by aungsithumoe on 11/17/17.
 */

public class Cutsize {
    int id;
    double width;
    double thick;
    double length;
    double quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cutsize(int id, double thick, double width, double length, double quantity) {
        this.id=id;

        this.width = width;
        this.thick = thick;
        this.length = length;
        this.quantity = quantity;
    }

    public Cutsize() {
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getThick() {
        return thick;
    }

    public void setThick(double thick) {
        this.thick = thick;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
