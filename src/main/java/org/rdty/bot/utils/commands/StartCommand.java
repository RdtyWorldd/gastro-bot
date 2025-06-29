package org.rdty.bot.utils.commands;

import org.rdty.model.user.BotUser;
import org.rdty.repos.BotUserDAO;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class StartCommand implements Command
{
    private final String DIRECTORY = "src/main/resources/";
    private BotUserDAO usersDao = new BotUserDAO() {
        @Override
        public List<BotUser> getAllUsers() {
            return List.of();
        }

        @Override
        public BotUser getUser(Long id) {
            return null;
        }

        @Override
        public void createUser(BotUser user) {

        }

        @Override
        public void update(BotUser user) {

        }

        @Override
        public void delete(Long id) {

        }

        @Override
        public void createUsers(List<BotUser> newUsers) {

        }

        @Override
        public void updateAll(List<BotUser> users) {

        }

        @Override
        public void deleteALL(List<Long> ids) {

        }
    };
    private SendPhoto message;
    @Override
    public SendPhoto apply(Update update) {
        Long id = update.getMessage().getFrom().getId();
        BotUser user = usersDao.getUser(id);
        InputFile picture = new InputFile();
        try(InputStream stream = Files.newInputStream(Path.of(DIRECTORY + "test.png")))
        {
            picture.setMedia(stream, DIRECTORY + "test.png");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        if(user == null)
        {
            User tg_user = update.getMessage().getFrom();
            user = new BotUser(tg_user);
            usersDao.createUser(user);
            message = SendPhoto.builder()
                    .chatId(id)
                    .photo(new InputFile(new File("src/main/resources/test.png")))
                    .caption("Добро пожаловать в наш домашний ресторан!")
                    //.replyMarkup()
                    .build();
        }
        else
        {
            //наверное что-то приветственное
            //мол рады приветствовать тебя или не знаю хочешь обновить заказик?
            //ну или можно вообще ничего не присылать
        }

        return message;
    }
}
