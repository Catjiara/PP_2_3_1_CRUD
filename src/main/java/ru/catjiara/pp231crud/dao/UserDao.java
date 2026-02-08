package ru.catjiara.pp231crud.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.catjiara.pp231crud.models.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    private static int cnt;
    private List<User> users ;

    {
        users = new ArrayList<>();
        users.add(new User(++cnt, "Nikulin"));
        users.add(new User (++cnt, "Vitsyn"));
        users.add(new User(++cnt, "Morgunov"));
        users.add(new User(++cnt, "Pet'ka"));
        users.add(new User(++cnt, "Vasily Ivanovich"));

    }

    public List<User> index() {
        return this.users;
    }
    public User getUser(int id) {
        return this.users.stream().filter(user->user.getId() == id).findFirst().orElse(null);
    }

    public void save(User user) {
        user.setId(++cnt);
        users.add(user);
    }

    public void update(int id, User user) {
        User user2Upd = getUser(id);
        user2Upd.setName(user.getName());
    }
    public void delete(int id) {
        User user2del = getUser(id);
        this.users.remove(user2del);
    }
}
