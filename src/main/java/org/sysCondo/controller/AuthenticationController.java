package org.sysCondo.controller;

import org.sysCondo.model.user.User;

import java.util.Objects;

public class AuthenticationController {

    public User login(String userDocument, String password){
        UserController userController = new UserController();
        User user = userController.getUserByDocument(userDocument);
        if( user == null || !Objects.equals(user.getUserPassword(), password)){
            throw new RuntimeException("Senha ou Documento inválido");
        }
        return user;
    }
}
