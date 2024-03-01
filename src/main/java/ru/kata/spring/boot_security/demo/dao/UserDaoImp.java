package ru.kata.spring.boot_security.demo.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    public UserDaoImp(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserById(long  id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.merge(user);
    }

    @Override
    public void removeById(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
    @Override
    public List<User> getAll(){
        return entityManager.createQuery("from User user", User.class)
                .getResultList();
    }
}
