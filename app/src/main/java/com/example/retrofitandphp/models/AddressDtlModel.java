package com.example.retrofitandphp.models;

public class AddressDtlModel {


private Double lng;
private Double lat;

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "AddressDtlModel{" +
                "lng=" + lng +
                ", lat=" + lat +
                '}';
    }
}
