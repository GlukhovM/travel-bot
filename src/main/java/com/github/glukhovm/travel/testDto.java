package com.github.glukhovm.travel;

import java.util.Arrays;
import java.util.List;

public class testDto {
    private List<subData> data;

    public testDto() {
    }

    public testDto(List<subData> data) {
        this.data = data;
    }

    public List<subData> getData() {
        return data;
    }

    public void setData(List<subData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "testDto{" +
                "data=" + data +
                '}';
    }

    public static class subData{
        private int price;
        private int dTime;
        private String fly_duration;
        private String[] airlines;

        public subData() {
        }

        public subData(int price, int dTime, String fly_duration, String[] airlines) {
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
            return "subData{" +
                    "price=" + price +
                    ", dTime=" + dTime +
                    ", fly_duration='" + fly_duration + '\'' +
                    ", airlines=" + Arrays.toString(airlines) +
                    '}';
        }
    }
    }




