package com.github.glukhovm.travel;

import com.github.glukhovm.travel.enums.OptionNumber;
import com.github.glukhovm.travel.enums.QuestionNumber;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ResponseHandler {

    private boolean correctAnswer = false;
    private boolean isFirstVar = true;
    private int optionNumber;
    private String toSend;

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isFirstVar() {
        return isFirstVar;
    }


    public String askQuestion(int questionNum, String userAnswer, RequestDto requestOptions) {
        switch (QuestionNumber.byCode(questionNum)) {
            case OPTION_SELECTION:
                correctAnswer = false;
                switch (OptionNumber.byCode(userAnswer)) {
                    case SELECTION_ALL_FOUR_PARAMETERS:
                        optionNumber = 1;
                        correctAnswer = true;
                        toSend = "Введи аэропорт вылета в формате \"NNN\" ";
                        break;
                    case SELECTION_AIRPORTS_ONLY:
                        optionNumber = 2;
                        correctAnswer = true;
                        toSend = "Введи аэропорт вылета в формате \"NNN\" ";
                        break;
                    case SELECTION_DEPARTURE_AIRPORT_ONLY:
                        optionNumber = 3;
                        correctAnswer = true;
                        toSend = "Введи аэропорт вылета в формате \"NNN\" ";
                        break;
                    default:
                        toSend = "Введи \"1\" или  \"2\" или  \"3\"";
                }
                return toSend;

            case DEPARTURE_AIRPORT_SELECTION:
                correctAnswer = false;
                if (isValidAirport(userAnswer.toUpperCase())) {
                    if (optionNumber != 3) {
                        requestOptions.setDepartureAirport(userAnswer.toUpperCase());
                        toSend = "Введи аэропорт прилета в формате \"NNN\" ";
                    } else {
                        requestOptions.setDepartureAirport(userAnswer.toUpperCase());
                        toSend = "Отлично, вот рейсы - " + "\n"
                                + new SkypickerRequest(requestOptions).getResponse();
                        isFirstVar = false;
                        correctAnswer = true;
                    }
                    correctAnswer = true;
                } else {
                    toSend = "Пример ввода: CEK";
                }
                return toSend;

            case ARRIVAL_AIRPORT_SELECTION:
                correctAnswer = false;
                if (isValidAirport(userAnswer.toUpperCase())) {
                    if (optionNumber == 1) {
                        requestOptions.setArrivalAirport(userAnswer.toUpperCase());
                        toSend = "Введите дату вылета в формате dd/mm/yyyy";
                    } else {
                        requestOptions.setArrivalAirport(userAnswer.toUpperCase());
                        toSend = "Отлично, вот рейсы - " + "\n"
                                + new SkypickerRequest(requestOptions).getResponse();
                        isFirstVar = false;
                        correctAnswer = true;
                    }
                    correctAnswer = true;
                } else {
                    toSend = "Пример ввода: LED";
                }
                return toSend;

            case DEPARTURE_DATE_SELECTION:
                correctAnswer = false;
                if (isValidDate(userAnswer)) {
                    requestOptions.setDepartureTime(userAnswer);
                    toSend = "Введите дату возвращения в формате dd/mm/yyyy";
                    correctAnswer = true;
                } else {
                    toSend = "Пример ввода: 01/08/2020";
                }
                return toSend;

            case RETURN_TIME_SELECTION:
                correctAnswer = false;
                if (isValidDate(userAnswer)) {
                    requestOptions.setReturnTime(userAnswer);
                    toSend = "Отлично, вот рейсы - " + "\n"
                            + new SkypickerRequest(requestOptions).getResponse();
                    correctAnswer = true;
                } else {
                    toSend = "Пример ввода: 10/08/2020";
                }
                return toSend;
            case RESTART_DIALOGUE:
                toSend = "Чтобы начать заново введи /start";
                isFirstVar = true;
                return toSend;
            default:
                throw new IllegalStateException(); //TODO read about exception!!!
        }
    }

    public boolean isValidAirport(String userAnswer) {
        return userAnswer != null && userAnswer.matches("[A-Z]{3}"); //TODO read about regex!!!
    }

    public boolean isValidDate(String userAnswer) { //TODO read more about LocalDate and add the date ratio validation
        try {
            LocalDate.parse(userAnswer, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}