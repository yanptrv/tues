package com.home.Kris;

import java.util.HashSet;
import java.util.Objects;

public class Shoe {
    private final int size;
    private final String color;
    private final String brand;

    public Shoe(int size, String color, String brand) {
        this.size = size;
        this.color = color;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Shoe{" +
                "size=" + size +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shoe shoe = (Shoe) o;
        return size == shoe.size && Objects.equals(color, shoe.color) && Objects.equals(brand, shoe.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, color, brand);
    }
}
