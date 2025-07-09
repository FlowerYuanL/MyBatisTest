package com.example.mapper;

import com.example.pojo.User;

import java.util.List;


public interface UserMapper {

    User findById(int id);

    int findTotal();

    void deleteById(int id);

    List<User> findAll();

    /*void addUser(User user);*/

    int addUser(User user);

    void simpleDelete(int id);

/*    void dynamicSelect(int id);*/
}
