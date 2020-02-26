package com.github.glukhovm.travel;

import com.github.glukhovm.travel.configuration.BotProperties;
import org.apache.shiro.session.Session;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.session.TelegramLongPollingSessionBot;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class TravelBotSession extends TelegramLongPollingSessionBot {
    private final BotProperties properties;

    public TravelBotSession(BotProperties properties){
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
                .setReplyToMessageId(message.getMessageId())
                .setText(text);
        try {
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
        return properties.getName();
    }

    @Override
    public String getBotToken() {
        return properties.getToken();
    }
}
