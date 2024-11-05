package com.example.aungsithumoe.calculatorforwood;

/**
 * Created by aungsithumoe on 11/17/17.
 */

public class Round {
    int id;
    double length;
    double perimeter;
    double quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Round(int id, double length, double perimeter, double quantity) {
        this.id=id;

        this.length = length;
        this.perimeter = perimeter;
        this.quantity = quantity;
    }

    public Round() {
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
