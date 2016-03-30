package com.home.installationofdoors;

/**
 * Created by 4 on 13.03.2016.
 */
public class Calculating {

    private double width, height, insertWidth, insertHeight;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = Math.rint(100.0 * width) / 100.0;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = Math.rint(100.0 * height) / 100.0;
    }

    public double getInsertWidth() {
        return insertWidth;
    }

    public void setInsertWidth(double insertWidth) {
        this.insertWidth = Math.rint(100.0 * insertWidth) / 100.0;
    }

    public double getInsertHeight() {
        return insertHeight;
    }

    public void setInsertHeight(double insertHeight) {
        this.insertHeight = Math.rint(100.0 * insertHeight) / 100.0;
    }

    public double calcWidthDoor(String widthAperture, String widthProfile, String countOverlaps, String countDoors){
        return (Double.parseDouble(widthAperture) + (( Double.parseDouble(widthProfile)
                * Double.parseDouble(countOverlaps)) / Double.parseDouble(countDoors) ))/ Double.parseDouble(countDoors);
    }

    public double calcHeightDoor(String heightAperture, double valueRoller){
        return Double.parseDouble(heightAperture) - valueRoller;
    }

    public double calcInsertWidth(double width, String s, double valueX, String countDoors){
        return (width - ((Double.parseDouble(s) - valueX) * 2 )/ Double.parseDouble(countDoors));
    }

    public double calcInsertHeight(double height, double tolerance){
        return height - tolerance;
    }
}
