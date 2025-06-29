package org.rdty.bot.utils.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    SendPhoto apply(Update update);
}
