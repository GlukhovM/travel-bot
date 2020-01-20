package com.github.glukhovm.travel;

import java.util.Arrays;

public class ResponseDto {
    private int price;
    private long dTime;
    //дата вылета
    //время вылета
    private String fly_duration;
    private String airline;

    public ResponseDto() {
    }

    public ResponseDto(int price, long dTime, String fly_duration, String airline) {
        this.price = price;
        this.dTime = dTime;
        this.fly_duration = fly_duration;
        this.airline = airline;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getdTime() {
        return dTime;
    }

    public void setdTime(long dTime) {
        this.dTime = dTime;
    }

    public String getFly_duration() {
        return fly_duration;
    }

    public void setFly_duration(String fly_duration) {
        this.fly_duration = fly_duration;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "price=" + price +
                ", dTime=" + dTime +
                ", fly_duration='" + fly_duration + '\'' +
                ", airline='" + airline + '\'' +
                '}';
    }
}
