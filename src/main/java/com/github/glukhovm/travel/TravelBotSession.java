package com.github.glukhovm.travel;

import com.github.glukhovm.travel.configuration.BotProperties;
import org.apache.shiro.session.Session;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.session.TelegramLongPollingSessionBot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TravelBotSession extends TelegramLongPollingSessionBot {
    private final BotProperties properties;

    public TravelBotSession(BotProperties properties) {
        this.properties = properties;
    }

    public static void main(String[] args) throws IOException {
        final String file = TravelBotSession.class.getClassLoader().getResource("bot.properties").getFile();
        final BotProperties botProperties = new BotProperties(new File(file));
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TravelBotSession(botProperties));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage()
                .enableMarkdown(true)
                .setChatId(message.getChatId())
                //.setReplyToMessageId(message.getMessageId())
                .setText(text);
        try {
            if ("/start".equals(message.getText())) {
                setButtons(sendMessage);
            }
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("1"));
        keyboardFirstRow.add(new KeyboardButton("2"));
        keyboardFirstRow.add(new KeyboardButton("3"));

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }


    @Override
    public void onUpdateReceived(Update update, Optional<Session> botSession) {

        final String startMessage = "Привет, " + update.getMessage().getFrom().getFirstName() + ", выбери один из вариантов поиска: " + "\n" +
                "1. Из аэропорта в аэропорт с выбором даты вылета и даты возвращения" + "\n" +
                "2. Из аэропорта в аэропорт без выбора дат" + "\n" +
                "3. Из аэропорта без выбора дат";

        final Message message = update.getMessage();


        if ("/start".equals(message.getText())) {
            botSession.ifPresent(session -> {
                session.setAttribute("question_number", 0);
            });
            sendMsg(message, startMessage + "\n" + "Если хочешь начать заново введи /start");
        } else {
            botSession.ifPresent(session -> {
                ResponseHandler handler = (ResponseHandler) session.getAttribute("handler");
                session.setAttribute("handler", handler == null ? new ResponseHandler() : handler);

                final Integer questionNumber = (Integer) session.getAttribute("question_number");
                final String toSend = handler.askQuestion(questionNumber, message.getText());
                sendMsg(message, toSend + "\n" + /*"qNum: " + questionNumber + " varNum: " + handler.optionNumber + " " + */
                        handler.getDAirport() + " " + handler.getAAirport() + " " + handler.getDTime() + " " + handler.getRTime());
                if (handler.correctAnswer && questionNumber <= 4) {
                    if (handler.isFirstVar) {
                        session.setAttribute("question_number", questionNumber == 0 ? 1 : questionNumber + 1);
                    } else {
                        session.setAttribute("question_number", 5);
                    }
                }
            });
        }
    }


    //        final String toSend = botSession.map(session -> {
//            final String lastMessage = (String) session.getAttribute("last_message");
//            final String firstMessage = (String) session.getAttribute("first_message");
//            if (lastMessage != null && firstMessage != null) {
//                final Integer count = (Integer) session.getAttribute("messages_count");
//                return "Последнее сообщение: " + lastMessage + "; всего: " + count + " , первое: " + firstMessage;
//            } else {
//                return "Привет, " + update.getMessage().getFrom().getFirstName() + "! Нет предыдущих сообщений";
//            }
//        }).orElse("Нет предыдущих сообщений");
//
//
//        final Message message = update.getMessage();
//        botSession.ifPresent(session -> {
//            final Integer messagesCount = (Integer) session.getAttribute("messages_count");
//            session.setAttribute("messages_count", messagesCount == null ? 1 : messagesCount + 1);
//            session.setAttribute("last_message", message.getText());
//            if(messagesCount == 1) {
//                session.setAttribute("first_message", message.getText());
//            }
//        });
//        sendMsg(message, toSend);


    @Override
    public String getBotUsername() {
        return properties.getName();
    }

    @Override
    public String getBotToken() {
        return properties.getToken();
    }
}
