package com.github.glukhovm.travel;

public class SecondScenario {
    private static String dAirportCode;
    private static String aAirportCode;
    private static String answer;


    public static String secondInputHandler(String text) {
        if (text.equals("CEK")) {
            dAirportCode = text;
            answer = "Ок, правильно!";
        } else {
            answer = "Введи CEK дебил!";
        }
        return answer;
    }

}
