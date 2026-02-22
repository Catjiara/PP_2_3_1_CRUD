package ru.catjiara.pp231crud.dao;

import ru.catjiara.pp231crud.models.User;
import java.util.List;


public interface IUserDao {
    public List<User> index();
    public User getUser(int id);
    public void save(User user);
    public void update(int id, User user);
    public void delete(int id);
}
