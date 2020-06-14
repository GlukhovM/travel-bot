package com.github.glukhovm.travel.enums;

public enum  OptionNumber {
    SELECTION_ALL_FOUR_PARAMETERS("1"),
    SELECTION_AIRPORTS_ONLY("2"),
    SELECTION_DEPARTURE_AIRPORT_ONLY("3"),
    UNKNOWN_OPTION(null);


    private String optionNumber;

    OptionNumber(String optionNumber) {
        this.optionNumber = optionNumber;
    }

    public static OptionNumber byCode(String optionNumber){
        for(OptionNumber value : values()){
        if(value.optionNumber.equals(optionNumber)) {
            return value;
            }
        }
        return UNKNOWN_OPTION;
    }
}
