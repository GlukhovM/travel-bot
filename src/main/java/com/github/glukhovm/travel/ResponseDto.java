package com.github.glukhovm.travel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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
        return "Options= " + data + ", Currency= " + currency;
    }

    public static class OptionDto {
        private int price;
        private long dTime;
        @JsonProperty(value = "fly_duration")
        private String flyDuration;
        private List<String> airlines;

        public OptionDto() {
        }

        public OptionDto(int price, long dTime, String flyDuration, List<String> airlines) {
            this.price = price;
            this.dTime = dTime;
            this.flyDuration = flyDuration;
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
        } // if you call this getter "getDTime" it return "0"

        public void setDTime(long dTime) {
            this.dTime = dTime;
        }

        public String getFlyDuration() {
            return flyDuration;
        }

        public void setFlyDuration(String flyDuration) {
            this.flyDuration = flyDuration;
        }

        public List<String> getAirlines() {
            return airlines;
        }

        public void setAirlines(List<String> airlines) {
            this.airlines = airlines;
        }

        @Override
        public String toString() {
            return '{' + "price=" + price +
                    ", dTime=" + dTime +
                    ", flyDuration=" + flyDuration +
                    ", airlines=" + airlines + '}' + "\n";
        }
    }
}



