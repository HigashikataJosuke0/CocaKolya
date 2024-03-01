package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {
    private UserDao userDao;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImp(UserDao userDao, RoleRepository roleRepository) {
        this.userDao = userDao;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        Set<Role> newSet = new HashSet<>();

        newSet.add(roleRepository.getById(1L));

        user.setRoles(newSet);
        userDao.save(user);
    }

    @Override
    @Transactional
    public void removeById(long id) {
        userDao.removeById(id);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userDao.getAll();
    }

}
