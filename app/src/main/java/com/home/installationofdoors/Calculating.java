package com.home.installationofdoors;

/**
 * Created by 4 on 13.03.2016.
 */
public class Calculating {

    public double calcWidthDoor(String widthAperture, String widthProfile, String countOverlaps, String countDoors){
        return Double.parseDouble(widthAperture) + Double.parseDouble(widthProfile)
                * Double.parseDouble(countOverlaps) / Double.parseDouble(countDoors);
    }

    public double calcHeightDoor(String heightAperture, double valueRoller){
        return Double.parseDouble(heightAperture) - valueRoller;
    }

    public double calcInsertWidth(double width, String s, double valueX){
        return width - ((Double.parseDouble(s) - valueX) * 2);
    }

    public double calcInsertHeight(double height, double tolerance){
        return height - tolerance;
    }
}
