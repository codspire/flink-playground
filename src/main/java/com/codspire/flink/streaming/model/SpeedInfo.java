package com.codspire.flink.streaming.model;

public class SpeedInfo {
    private String carModel;
    private int speed;

    public SpeedInfo(String carModel, int speed) {
        this.carModel = carModel;
        this.speed = speed;
    }

    public String getCarModel() {
        return carModel;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "SpeedInfo{" +
                "carModel='" + carModel + '\'' +
                ", speed=" + speed +
                '}';
    }
}
