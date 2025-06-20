package org.rdty.model.user;



import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.User;

@Getter
public class BotUser {
    private Long id;
    private User profile;

    public BotUser(User profile)
    {
        this.profile = profile;
        id = profile.getId();
    }
}
