package com.example.itmba_transportcostcomparator;

public class Transport {

    private String modeOfTransport;
    private String transportType;
    private double distancePerDay;
    private double costPerKm;
    private int travelDays;

    public Transport() {

    }

    public Transport(String modeOfTransport, String transportType, double distancePerDay, double costPerKm, int travelDays) {
        this.modeOfTransport = modeOfTransport;
        this.transportType = transportType;
        this.distancePerDay = distancePerDay;
        this.costPerKm = costPerKm;
        this.travelDays = travelDays;
    }

    public String getModeOfTransport() {
        return modeOfTransport;
    }

    public String getTransportType() {
        return transportType;
    }

    public double getDistancePerDay() {
        return distancePerDay;
    }

    public double getCostPerKm() {
        return costPerKm;
    }

    public int getTravelDays() {
        return travelDays;
    }

    public void setModeOfTransport(String modeOfTransport) {
        this.modeOfTransport = modeOfTransport;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public void setDistancePerDay(double distancePerDay) {
        this.distancePerDay = distancePerDay;
    }

    public void setCostPerKm(double costPerKm) {
        this.costPerKm = costPerKm;
    }

    public void setTravelDays(int travelDays) {
        this.travelDays = travelDays;
    }
}
