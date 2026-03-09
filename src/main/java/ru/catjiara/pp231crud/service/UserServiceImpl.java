package ru.catjiara.pp231crud.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.catjiara.pp231crud.dao.UserDao;
import ru.catjiara.pp231crud.models.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    @Transactional(readOnly=true)
    public List<User> index() {
        return userDao.index();
    }

    @Override
    @Transactional(readOnly=true)
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(int id, User user) {
        userDao.update(id, user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
}
