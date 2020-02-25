package com.github.glukhovm.travel;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import java.util.ArrayList;
import java.util.List;
import static com.github.glukhovm.travel.ThirdScenario.inputHandler;


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
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            Message message = update.getMessage();
            String text = message.getText();
            if(text.equals("Вариант 3")){
                sendMsg(message, "Введи аэропорт вылета!");
                sendMsg(message, inputHandler(text));
            }
        }
    }

    /*if (update.hasMessage() && update.getMessage().hasText()) {
            if (update.getMessage().getText().equals("/start")) {
                sendMsg(update.getMessage(), "Введи Вариант 1");
            }
            if (update.getMessage().getText().equals("Вариант 1")) {
                sendMsg(update.getMessage(), "Введи код аэро вылета (введи CEК)!");
            }
            if (update.getMessage().getText().equals("CEK")) {
                sendMsg(update.getMessage(), "молодец, ты выбрал " + update.getMessage().getText() + ", теперь напиши дату 01/01/2020");
            }
            if (update.getMessage().getText().equals("01/01/2020")) {
                sendMsg(update.getMessage(), "good boy!");
            }
        }*/

            /*switch (update.getMessage().getText()) {
                case "/start":
                    sendMsg(update.getMessage(), "Привет, полетаем? Выбирай вариант!");
                    sendMsg(update.getMessage(),
                            "1. Поиск билетов из аэропорта в аэропорт с указанием дат вылета и прилета" + "\n" +
                                    "2. Поиск билетов из аэропорта в аэропорт без указания дат вылета и прилета" + "\n" +
                                    "3. Поиск билетов из аэропорта \"куда-нибуть\"");
                    break;
                case "Вариант 1":
                    sendMsg(update.getMessage(), "Ок, укажи код аэропорта вылета по IATA!");
                    break;
                case "Вариант 2":
                    sendMsg(message, "Good evening to you!");
                    break;
                case "Вариант 3":
                    sendMsg(message, "Ок, укажи код аэропорта вылета по IATA!");
                    break;
                default:
                    //sendMsg(message, "Введите корректные данные.");
            }*/


    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Вариант 1"));
        keyboardFirstRow.add(new KeyboardButton("Вариант 2"));
        keyboardFirstRow.add(new KeyboardButton("Вариант 3"));

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

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
