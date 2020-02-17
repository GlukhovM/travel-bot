package com.github.glukhovm.travel;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//VPN ip 3.12.230.51
public class TravelBotProxy extends AbilityBot {

    protected TravelBotProxy(String botToken, String botUsername, DefaultBotOptions botOptions) {
        super(botToken, botUsername, botOptions);
    }

    public int creatorId() {
        return 0;
    }


    private static String BOT_NAME = "travel_flight_bot";
    private static String BOT_TOKEN = "688895841:AAHuM39QsEuAqlY9LZu4JsFxkRdY2HnJrxM";

    private static String PROXY_HOST = "103.216.82.18";
    private static Integer PROXY_PORT = 6667;

    public static void main(String[] args) {
        try {
            ApiContextInitializer.init();
            // Create the TelegramBotsApi object to register your bots
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            // Set up Http proxy
            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
            botOptions.setProxyHost(PROXY_HOST);
            botOptions.setProxyPort(PROXY_PORT);
            // Select proxy type: [HTTP|SOCKS4|SOCKS5] (default: NO_PROXY)
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS4);

            // Register your newly created AbilityBot
            TravelBotProxy bot = new TravelBotProxy(BOT_TOKEN, BOT_NAME, botOptions);
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
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
                case "Hello":
                    sendMsg(message, "Fuck you");
                    break;
                case "Good evening":
                    sendMsg(message, "Suck my dick");
                    break;
                default:
                    sendMsg(message, "gtfo sucker");
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
