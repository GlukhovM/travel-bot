package com.github.glukhovm.travel;

public class ThirdScenario {
    private static String dAirportCode;
    private static String answer;


    public static String inputHandler(String text) {
        if (text.equals("CEK")) {
            dAirportCode = text;
            answer = "Ок, правильно! Теперь введи аэропорт прилета!";
        } else {
            answer = "Введи CEK дебил!";
        }
        return answer;
    }

}
