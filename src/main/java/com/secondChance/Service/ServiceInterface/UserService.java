package com.secondChance.Service.ServiceInterface;

import com.secondChance.Models.UserEntity;
import java.util.List;

public interface UserService {
    void createUser(UserEntity user);
    UserEntity getUserById(int id);
    List<UserEntity> getAllUsers();
    void updateUser(UserEntity user);
    void deleteUser(int id);

    boolean validateUser(String email, String password);
    UserEntity getUserByEmail(String email);
}
