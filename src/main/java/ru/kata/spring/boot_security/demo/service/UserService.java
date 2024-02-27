package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    public User getUserById(long  id);
    void save(User user);
    void removeById (long id);
    public List<User> getAll();
}
