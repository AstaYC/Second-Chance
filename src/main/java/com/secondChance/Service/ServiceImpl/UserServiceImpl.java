package com.secondChance.Service.ServiceImpl;

import com.secondChance.DAO.DAOImpl.UserDAOImpl;
import com.secondChance.Models.UserEntity ;
import com.secondChance.Service.ServiceInterface.UserService;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public void createUser(UserEntity user) {
        userDAO.createUser(user);
    }

    @Override
    public UserEntity getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void updateUser(UserEntity user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    @Override
    public boolean validateUser(String email, String password) {
        return userDAO.validateUser(email, password);
    }
    @Override
    public UserEntity getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }
}
