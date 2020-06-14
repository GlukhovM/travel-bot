package com.github.glukhovm.travel.enums;

public enum  QuestionNumber {
    OPTION_SELECTION(0),
    DEPARTURE_AIRPORT_SELECTION(1),
    ARRIVAL_AIRPORT_SELECTION(2),
    DEPARTURE_DATE_SELECTION(3),
    RETURN_TIME_SELECTION(4),
    RESTART_DIALOGUE(5);

    private int questionNumber;

    QuestionNumber(int optionNumber) {
        this.questionNumber = optionNumber;
    }

    public static QuestionNumber byCode(int questionNumber){
        for(QuestionNumber value : values()){
            if(value.questionNumber == questionNumber){
                return value;
            }
        }
        return null;
    }
}
