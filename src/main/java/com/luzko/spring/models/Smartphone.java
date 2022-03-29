package com.luzko.spring.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Smartphone {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Min(value = 1973, message = "Year of release should not be earlier than 1973")
    private int yearOfRelease;

    @Min(value = 0, message = "Display width should be greater than 0")
    private double displayWidthInches;

    public Smartphone() {}

    public Smartphone(int id, String name, int yearOfRelease, double displayWidthInches) {
        this.id = id;
        this.name = name;
        this.yearOfRelease = yearOfRelease;
        this.displayWidthInches = displayWidthInches;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public double getDisplayWidthInches() {
        return displayWidthInches;
    }

    public void setDisplayWidthInches(double displayWidthInches) {
        this.displayWidthInches = displayWidthInches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Smartphone that = (Smartphone) o;
        return id == that.id && yearOfRelease == that.yearOfRelease && Double.compare(that.displayWidthInches, displayWidthInches) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, yearOfRelease, displayWidthInches);
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", displayWidthInches=" + displayWidthInches +
                '}';
    }
}