package ru.catjiara.pp231crud.dao;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.catjiara.pp231crud.models.User;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDao {

    //debug
//    private static int cnt;
//    private List<User> users ;

//    {
//        users = new ArrayList<>();
//        users.add(new User(++cnt, "Nikulin"));
//        users.add(new User (++cnt, "Vitsyn"));
//        users.add(new User(++cnt, "Morgunov"));
//        users.add(new User(++cnt, "Pet'ka"));
//        users.add(new User(++cnt, "Vasily Ivanovich"));
//
//    }
    //end debug
    @Autowired
    private SessionFactory sessionFactory;

    public List<User> index() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
        //debug
        //return this.users;
    }
    public User getUser(int id) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where id = :id");
        query.setParameter("id", id);
        return query.getSingleResult();
        //debug
        //return this.users.stream().filter(user->user.getId() == id).findFirst().orElse(null);
    }

    public void save(User user) {
        sessionFactory.getCurrentSession().persist(user);
        //debug
//        user.setId(++cnt);
//        users.add(user);
    }

    public void update(int id, User user) {
        User user2Upd = getUser(id);
        user2Upd.setName(user.getName());
        User updatedUser = (User) sessionFactory.getCurrentSession().merge(user2Upd);
    }
    public void delete(int id) {
        User user2del = getUser(id);
        sessionFactory.getCurrentSession().remove(user2del);
        //this.users.remove(user2del);
    }
}
