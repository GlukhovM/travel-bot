package com.github.glukhovm.travel;

import com.github.glukhovm.travel.enums.OptionNumber;
import com.github.glukhovm.travel.enums.QuestionNumber;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UserResponseHandler {

    private boolean correctAnswer = false;
    private boolean isFirstVar = true;
    private int optionNumber;
    private String toSend;
    private SkypickerClient skypickerClient = new SkypickerClient();

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isFirstVar() {
        return isFirstVar;
    }


    public String askQuestion(int questionNum, String userAnswer, RequestDto requestDto) {
        switch (QuestionNumber.byCode(questionNum)) {
            case OPTION_SELECTION:
                correctAnswer = false;
                isFirstVar = true;
                switch (OptionNumber.byCode(userAnswer)) {
                    case SELECTION_ALL_FOUR_PARAMETERS:
                        optionNumber = 1;
                        correctAnswer = true;
                        toSend = "Введи IATA код аэропорта вылета в формате \"NNN\" ";
                        break;
                    case SELECTION_AIRPORTS_ONLY:
                        optionNumber = 2;
                        correctAnswer = true;
                        toSend = "Введи IATA код аэропорта вылета в формате \"NNN\" ";
                        break;
                    case SELECTION_DEPARTURE_AIRPORT_ONLY:
                        optionNumber = 3;
                        correctAnswer = true;
                        toSend = "Введи IATA код аэропорта вылета в формате \"NNN\" ";
                        break;
                    default:
                        toSend = "Введи \"1\" или  \"2\" или  \"3\"";
                }
                return toSend;

            case DEPARTURE_AIRPORT_SELECTION:
                correctAnswer = false;
                if (isValidAirport(userAnswer.toUpperCase())) {
                    if (optionNumber != 3) {
                        requestDto.setDepartureAirport(userAnswer.toUpperCase());
                        toSend = "Введи IATA код аэропорта прилета в формате \"NNN\" ";
                        correctAnswer = true;
                    } else {
                        requestDto.setDepartureAirport(userAnswer.toUpperCase());
                        toSend = skypickerClient.getResponse(requestDto); //ticket request
                        isFirstVar = false;
                        correctAnswer = true;
                    }
                } else {
                    toSend = "Пример ввода: CEK";
                }
                return toSend;

            case ARRIVAL_AIRPORT_SELECTION:
                correctAnswer = false;
                if (isValidAirport(userAnswer.toUpperCase())) {
                    if (optionNumber == 1) {
                        requestDto.setArrivalAirport(userAnswer.toUpperCase());
                        toSend = "Введите дату вылета в формате dd/mm/yyyy";
                        correctAnswer = true;
                    } else {
                        requestDto.setArrivalAirport(userAnswer.toUpperCase());
                        toSend = skypickerClient.getResponse(requestDto); //ticket request
                        isFirstVar = false;
                        correctAnswer = true;
                    }
                } else {
                    toSend = "Пример ввода: LED";
                }
                return toSend;

            case DEPARTURE_DATE_SELECTION:
                correctAnswer = false;
                if (isValidDate(userAnswer)) {
                    requestDto.setDepartureTime(userAnswer);
                    toSend = "Введите дату возвращения в формате dd/mm/yyyy";
                    correctAnswer = true;
                } else {
                    toSend = "Пример ввода: 01/08/2020";
                }
                return toSend;

            case RETURN_TIME_SELECTION:
                correctAnswer = false;
                if (isValidDate(userAnswer)) {
                    requestDto.setReturnTime(userAnswer);
                    toSend = skypickerClient.getResponse(requestDto); //ticket request
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
                throw new IllegalStateException();
        }
    }

    public boolean isValidAirport(String userAnswer) {
        return userAnswer != null && userAnswer.matches("[A-Z]{3}");
    }

    public boolean isValidDate(String userAnswer) {
        try {
            LocalDate.parse(userAnswer, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}