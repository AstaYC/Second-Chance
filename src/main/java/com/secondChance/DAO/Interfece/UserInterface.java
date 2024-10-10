package com.secondChance.DAO.Interfece;

import com.secondChance.Models.UserEntity;
import java.util.List;

public interface UserInterface {
    void createUser(UserEntity user);
    UserEntity getUserById(int id);
    List<UserEntity> getAllUsers();
    void updateUser(UserEntity user);
    void deleteUser(int id);
    boolean validateUser(String email, String password);
    UserEntity getUserByEmail(String email);
}
