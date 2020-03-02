package com.github.glukhovm.travel;

public class ResponseHandler {
    boolean correctAnswer = false;
    private int optionNumber;
    private String toSend;

    public String handle(int questionNumber, String userAnswer) {

        switch (questionNumber) {
            case 0:
                correctAnswer = false;
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
                break;
            case 1: //write dAir
                correctAnswer = false;
                    Question dAirQuestion = new Question() {
                        String temp = null;
                        @Override
                        public String askQuestion() {
                            if(userAnswer.equals("CEK")){
                                if(optionNumber == 3){
                                    temp = "Отлично, вот рейсы - //тут билеты";
                                }
                                temp = "Введи аэропорт прилета в формате \"NNN\" ";
                                correctAnswer = true;
                            } else {
                                temp = "Пример ввода: CEK";
                            }
                            return temp;
                        }
                    };
                    toSend = dAirQuestion.askQuestion();
                break;
            case 2: //write aAir
                correctAnswer = false;
                if (optionNumber == 1 || optionNumber == 2) {
                    Question aAirQuestion = new Question() {
                        String temp = null;
                        @Override
                        public String askQuestion() {
                            if(userAnswer.equals("LED")){
                                if(optionNumber == 1) {
                                    temp = "Введите дату вылета в формате dd/mm/yy";
                                } else {
                                    temp = "Отлично, вот рейсы - //тут билеты";
                                }
                                correctAnswer = true;
                            } else {
                                temp = "Пример ввода: LED";
                            }
                            return temp;
                        }
                    };
                    toSend = aAirQuestion.askQuestion();
                }
                break;

            case 3: //write dTime //
                correctAnswer = false;
                if (optionNumber == 1) {
                    Question aAirQuestion = new Question() {
                        String temp = null;
                        @Override
                        public String askQuestion() {
                            if(userAnswer.equals("01/08/20")){
                                temp = "Введите дату прилета в формате dd/mm/yy";
                                correctAnswer = true;
                            } else {
                                temp = "Пример ввода: 01/08/20";
                            }
                            return temp;
                        }
                    };
                    toSend = aAirQuestion.askQuestion();
                }
                break;

            case 4: //write aTime
                correctAnswer = false;
                if (optionNumber == 1) {
                    Question aAirQuestion = new Question() {
                        String temp = null;
                        @Override
                        public String askQuestion() {
                            if(userAnswer.equals("10/08/20")){
                                temp = "Отлично, вот рейсы - //тут билеты";
                                correctAnswer = true;
                            } else {
                                temp = "Пример ввода: 10/08/20";
                            }
                            return temp;
                        }
                    };
                    toSend = aAirQuestion.askQuestion();
                }
                break;

            default:
                toSend = "Чтобы начать заново введи /start";
        }
        return toSend;
    }
}