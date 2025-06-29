package org.rdty.bot.utils.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class InlineMarkupMaker {


    public InlineKeyboardMarkup make(String path) {
        List<InlineKeyboardButton> buttons = loadButtons(path);
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();//заполнение списска
        int i = 0;
        int j = -1;
        for(InlineKeyboardButton btn : buttons) {
            if(i % 2 == 0) {
                rows.add(new ArrayList<>());
                j++;
            }
            rows.get(j).add(btn);
            i++;
        }
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(); // создание клавиатуры
        markup.setKeyboard(rows); //добавление ряда новых кнопок из списк
        return markup;
    }

    private List<InlineKeyboardButton>loadButtons(String path)
    {
        Yaml yaml = new Yaml(new Constructor(InlineKeyboardButton.class, new LoaderOptions()));
        String text = readFileToString(path);
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        yaml.loadAll(text).forEach(obj -> buttons.add((InlineKeyboardButton) obj));
        return buttons;
    }

    private String readFileToString(String path) {
        try (InputStream inputStream = Files.newInputStream(Path.of(path))) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + path);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + path, e);
        }
    }
}

