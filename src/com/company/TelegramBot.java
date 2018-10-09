package com.company;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class TelegramBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            String message_text = update.getMessage().getText();

            long chat_id = update.getMessage().getChatId();

            if (message_text.equals("/start")) {

                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText(message_text);

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (message_text.equals("/pic")) {
                SendPhoto msg = new SendPhoto()
                        .setChatId(chat_id)
                        .setPhoto("AgADAgADuKoxG1dE6UmOGOcCFvHmM0Ljtw4ABI0xtSzwD6X0efUAAgI")
                        .setCaption("Photo");
                try {
                    execute(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (message_text.equals("/markup")) {
                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Here your keyboard");
                // Create ReplyKeyboardMarkup object
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                // Create the keyboard (list of keyboard rows)
                List<KeyboardRow> keyboard = new ArrayList<>();
                // Create a keyboard row
                KeyboardRow row = new KeyboardRow();

                row.add("Row 1 Button 1");
                row.add("Row 1 Button 2");
                row.add("Row 1 Button 3");

                keyboard.add(row);

                row = new KeyboardRow();
                row.add("Row 2 Button 1");
                row.add("Row 2 Button 2");
                row.add("Row 2 Button 3");

                keyboard.add(row);

                keyboardMarkup.setKeyboard(keyboard);

                message.setReplyMarkup(keyboardMarkup);

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (message_text.equals("Row 1 Button 1")){
                SendPhoto msg = new SendPhoto()
                        .setChatId(chat_id)
                        .setPhoto("AgADAgADuKoxG1dE6UmOGOcCFvHmM0Ljtw4ABI0xtSzwD6X0efUAAgI")
                        .setCaption("Photo");
                try {
                    execute(msg);
                } catch (TelegramApiException e){
                    e.printStackTrace();
                }

            } else if (message_text.equals("/hide")){
                SendMessage msg = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Keyboard hidden");
                ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
                msg.setReplyMarkup(keyboardMarkup);
                try {
                    execute(msg);
                } catch (TelegramApiException e){
                    e.printStackTrace();
                }

            } else {
                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Unknown command");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
            // Message contains photo
            // Set variables

            long chat_id = update.getMessage().getChatId();

            // Array with photo objects with different sizes
            List<PhotoSize> photos = update.getMessage().getPhoto();

            // Know file_id
            String f_id = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getFileId();
            int f_width = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getWidth();
            int f_height = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getHeight();

            String caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);

            SendPhoto msg = new SendPhoto()
                    .setChatId(chat_id)
                    .setPhoto(f_id)
                    .setCaption(caption);
            try {
                execute(msg);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        // TODO
        return "asdefgfsccbot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "690012854:AAFkcduP8j3K2Vp2cyYbvLNisoV7GukxN30";
    }
}