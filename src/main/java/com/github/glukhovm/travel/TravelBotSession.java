package com.github.glukhovm.travel;

import org.apache.shiro.session.Session;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.session.TelegramLongPollingSessionBot;

import java.util.Optional;

import static com.github.glukhovm.travel.ThirdScenario.inputHandler;

public class TravelBotSession extends TelegramLongPollingSessionBot {

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
                //.setReplyToMessageId(message.getMessageId())
                .setText(text);
        try {
            /*if (message.getText().equals("/start")) {
                setButtons(sendMessage);
            }*/
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpdateReceived(Update update, Optional<Session> botSession) {
            Message message = update.getMessage();
            String text = message.getText();
            sendMsg(message, text);
    }


    @Override
    public String getBotUsername() {
        return BotConfig.MYBOTNAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.MYBOTTOKEN;
    }
}
