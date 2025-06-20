package org.rdty.repos;

import org.rdty.model.user.BotUser;

import java.util.List;

public interface BotUserDAO {
    List<BotUser> getAllUsers();
    BotUser getUser(Long id);

    void createUser(BotUser user);
    void update(BotUser user);
    void delete(Long id);

    void createUsers(List<BotUser> newUsers);
    void updateAll(List<BotUser> users);
    void deleteALL(List<Long> ids);

}
