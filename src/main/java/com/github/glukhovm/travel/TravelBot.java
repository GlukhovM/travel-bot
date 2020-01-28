package com.github.glukhovm.travel;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


public class TravelBot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TravelBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage()
                .enableMarkdown(true)
                .setChatId(message.getChatId())
                .setReplyToMessageId(message.getMessageId())
                .setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (update.hasMessage() && update.getMessage().hasText()) {
            switch (message.getText()) {
                case "Привет":
                    sendMsg(message, "Привет тебе странник!");
                    break;
                case "Добрейший вечерочек":
                    sendMsg(message, "Вам того же!");
                    break;
                default:
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "travel_flight_bot";
    }

    @Override
    public String getBotToken() {
        return "688895841:AAHuM39QsEuAqlY9LZu4JsFxkRdY2HnJrxM";
    }
}
