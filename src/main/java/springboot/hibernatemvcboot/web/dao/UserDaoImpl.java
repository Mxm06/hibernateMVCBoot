package springboot.hibernatemvcboot.web.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springboot.hibernatemvcboot.web.model.User;


import java.util.List;
@Repository
public class UserDaoImpl implements UserDao{
    final private String FROM_USER = "From User";
    private EntityManager entityManager;
    // Я честно не знаю, почему не видит бин энтити менеджера, учитывая тот факт, что его создаёт автоматически, гугл ничего не ответил
    // А программа работает нормально, просто подавить это подчёркивание или я что-то не понимаю?
    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(User user) {
        entityManager.merge(user);
    }

    public void delete(User user) {
        entityManager.createQuery("delete from User where id =:id").setParameter("id",user.getId()).executeUpdate();
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public List<User> listAllUsers() {
        return entityManager.createQuery(FROM_USER).getResultList();
    }
}
