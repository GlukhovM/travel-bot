package com.github.glukhovm.travel;

public class ResponseHandler {
    boolean correctAnswer = false;
    boolean isFirstVar = true;
    public int optionNumber;
    private String toSend;

    private String dAirport;
    private String aAirport;
    private String dTime;
    private String rTime;

    public String getDAirport() {
        return dAirport;
    }

    public String getAAirport() {
        return aAirport;
    }

    public String getDTime() {
        return dTime;
    }

    public String getRTime() {
        return rTime;
    }


    public String askQuestion(int questionNum, String userAnswer) {
        switch (questionNum) {
            case 0:
                correctAnswer = false;
                dAirport = null;
                aAirport = null;
                dTime = null;
                rTime = null;
                switch (userAnswer) {
                    case "1":
                        optionNumber = 1;
                        correctAnswer = true;
                        toSend = "Введи аэропорт вылета в формате \"NNN\" ";
                        break;
                    case "2":
                        optionNumber = 2;
                        correctAnswer = true;
                        toSend = "Введи аэропорт вылета в формате \"NNN\" ";
                        break;
                    case "3":
                        optionNumber = 3;
                        correctAnswer = true;
                        toSend = "Введи аэропорт вылета в формате \"NNN\" ";
                        break;
                    default:
                        toSend = "Введи \"1\" или  \"2\" или  \"3\"";
                }
                return toSend;

            case 1:
                correctAnswer = false;
                if (userAnswer.equals("CEK")) { //TODO correct verification
                    if (optionNumber != 3) {
                        dAirport = userAnswer;
                        toSend = "Введи аэропорт прилета в формате \"NNN\" ";
                    } else {
                        dAirport = userAnswer;
                        toSend = "Отлично, вот рейсы - *TODO*";
                        //здесь отправляется URL запрос, возращается ответ и в тусенд отправляем инфу по билетам
                        //TODO URL request code
                        isFirstVar = false;
                        correctAnswer = true;
                    }
                    correctAnswer = true;
                } else {
                    toSend = "Пример ввода: CEK";
                }
                return toSend;

            case 2:
                correctAnswer = false;
                if (userAnswer.equals("LED")) { //TODO correct verification
                    if (optionNumber == 1) {
                        aAirport = userAnswer;
                        toSend = "Введите дату вылета в формате dd/mm/yy";
                    } else {
                        aAirport = userAnswer;
                        toSend = "Отлично, вот рейсы - *TODO*";
                        //здесь отправляется URL запрос, возращается ответ и в тусенд отправляем инфу по билетам
                        //TODO URL request code
                        isFirstVar = false;
                        correctAnswer = true;
                    }
                    correctAnswer = true;
                } else {
                    toSend = "Пример ввода: LED";
                }
                return toSend;

            case 3:
                correctAnswer = false;
                if (userAnswer.equals("01/08/20")) { //TODO correct verification
                    dTime = userAnswer;
                    toSend = "Введите дату прилета в формате dd/mm/yy";
                    correctAnswer = true;
                } else {
                    toSend = "Пример ввода: 01/08/20";
                }
                return toSend;

            case 4:
                correctAnswer = false;
                if (userAnswer.equals("10/08/20")) { //TODO correct verification
                    rTime = userAnswer;
                    toSend = "Отлично, вот рейсы - *TODO*";
                    //здесь отправляется URL запрос, возращается ответ и в тусенд отправляем инфу по билетам
                    //TODO URL request code
                    correctAnswer = true;
                } else {
                    toSend = "Пример ввода: 10/08/20";
                }
                return toSend;
            default:
                toSend = "Чтобы начать заново введи /start";
                isFirstVar = true;
        }
        return toSend;
    }
}