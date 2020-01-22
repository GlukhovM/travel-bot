package com.github.glukhovm.travel;

import java.util.Arrays;
import java.util.List;

public class ResponseDto {
    private List<Options> data;

    public ResponseDto() {
    }

    public ResponseDto(List<Options> data) {
        this.data = data;
    }

    public List<Options> getData() {
        return data;
    }

    public void setData(List<Options> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDto= " + data;
    }

    public static class Options {
        private int price;
        private int dTime;
        private String fly_duration;
        private String[] airlines;

        public Options() {
        }

        public Options(int price, int dTime, String fly_duration, String[] airlines) {
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

        public int getdTime() {
            return dTime;
        }

        public void setdTime(int dTime) {
            this.dTime = dTime;
        }

        public String getFly_duration() {
            return fly_duration;
        }

        public void setFly_duration(String fly_duration) {
            this.fly_duration = fly_duration;
        }

        public String[] getAirlines() {
            return airlines;
        }

        public void setAirlines(String[] airlines) {
            this.airlines = airlines;
        }

        @Override
        public String toString() {
            return '{' + "price=" + price +
                    ", dTime=" + dTime +
                    ", fly_duration=" + fly_duration +
                    ", airlines=" + Arrays.toString(airlines) + '}' + "\n";
        }
    }
    }




