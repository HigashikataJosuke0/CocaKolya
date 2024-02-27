package ru.kata.spring.boot_security.demo.dao;



import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserDao {

    public User getUserById(long  id);
    void save(User user);
    void removeById (long id);

    public List<User> getAll();

}
