package org.sysCondo.controller;

import org.sysCondo.model.user.User;

public class AuthenticationController {

    public User login(String userDocument, String password){
        UserController userController = new UserController();
        User user = userController.getUserByDocument(userDocument);
        try {
            if( user == null || user.getUserPassword() != password){
                throw new RuntimeException("Senha ou Documento inválido");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
