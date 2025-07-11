package org.rdty;

import org.rdty.bot.utils.callback.CallbackHandler;
import org.rdty.bot.utils.commands.CommandHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

//ответственность бота принимать запросы и отправлять запросы
//

public class Bot extends TelegramLongPollingBot
{
    private final String name = "Gastro Bot";
    private final CommandHandler commandHandler = new CommandHandler();
    private final CallbackHandler callbackHandler = new CallbackHandler();
    public Bot(String botToken) {
        super(botToken);
    }

    public static void main( String[] args )
    {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            System.out.println(System.getenv("bot_token"));
            botsApi.registerBot(new Bot(System.getenv("bot_token")));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText())
        {
            if(update.getMessage().getText().startsWith("/"))
            {
                sendPhotoMessage(commandHandler.handle(update));
            }
            else
            {
                //обработка ввода без комманды
            }
        }
        else if(update.hasCallbackQuery())
        {
            //sendPhotoMessage(callbackHandler.handle());
        }
    }

    void sendMessage(SendMessage message)
    {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    void sendPhotoMessage(SendPhoto photoMessage)
    {
        try {
            execute(photoMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
