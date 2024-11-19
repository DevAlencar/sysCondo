package org.sysCondo.components;

import lombok.Getter;
import lombok.Setter;
import org.sysCondo.model.user.User;

@Getter
@Setter
public class Session {
    private static User user;
}
