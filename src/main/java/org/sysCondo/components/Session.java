package org.sysCondo.components;

import lombok.Getter;
import lombok.Setter;
import org.sysCondo.model.user.User;

public class Session {
    private static User currentUser;

    public static void setCurrentUser(User currentUser) {
        Session.currentUser = currentUser;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
