package org.rdty.bot.utils.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private Map<String, Command> commandsMap = Map.of(
            "/start", new StartCommand()
    );

    public SendPhoto handle(Update update)
    {
        String text = update.getMessage().getText().split(" ")[0];
        Command command = commandsMap.get(text);
        SendPhoto message = null;

        if(command != null)
        {
            message = command.apply(update);
        }
        else
        {

        }

        return message;
    }

}
