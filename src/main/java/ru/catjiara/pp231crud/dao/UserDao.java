package ru.catjiara.pp231crud.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.catjiara.pp231crud.models.User;

import java.util.List;

@Repository
@Transactional
public class UserDao implements IUserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> index() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }
    @Override
    public User getUser(int id) {
        TypedQuery<User> query = entityManager.createQuery("from User where id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(int id, User user) {
        User user2Upd = getUser(id);
        user2Upd.setName(user.getName());
        entityManager.merge(user2Upd);
    }

    @Override
    public void delete(int id) {
        User user2del = getUser(id);
        entityManager.remove(user2del);
    }
}
