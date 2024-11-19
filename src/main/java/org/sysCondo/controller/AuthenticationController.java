package org.sysCondo.controller;

import org.sysCondo.model.user.User;

public class AuthenticationController {

    public Long login(String userDocument, String password){
        UserController userController = new UserController();
        User user = userController.getUserByDocument(userDocument);

        try {
            if(user.getUserPassword() != password){
                throw new RuntimeException("Senha inválida");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return user.getUserId();
    }
}
