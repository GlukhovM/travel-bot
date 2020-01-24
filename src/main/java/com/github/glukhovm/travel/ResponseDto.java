package com.github.glukhovm.travel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ResponseDto {
    private List<OptionDto> data;
    private String currency;

    public ResponseDto() {
    }

    public ResponseDto(List<OptionDto> data, String currency) {
        this.data = data;
        this.currency = currency;
    }

    public List<OptionDto> getData() {
        return data;
    }

    public void setData(List<OptionDto> data) {
        this.data = data;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "ResponseDto= " + data;
    }

    public static class OptionDto {
        private int price;
        private long dTime;
        private String fly_duration;
        private List<String> airlines;

        public OptionDto() {
        }

        public OptionDto(int price, long dTime, String fly_duration, List<String> airlines) {
            this.price = price;
            this.dTime = dTime;
            this.fly_duration = fly_duration;
            this.airlines = airlines;
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

        public List<String> getAirlines() {
            return airlines;
        }

        public void setAirlines(List<String> airlines) {
            this.airlines = airlines;
        }

        @Override
        public String toString() {
            return '{' + "price=" + price + " RUB" +
                    ", dTime=" + dTime +
                    ", fly_duration=" + fly_duration +
                    ", airlines=" + airlines + '}' + "\n";
        }

    }
}



