package persistence;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import models.UserNameEntity;

public class UserPersistence {
    public UserNameEntity getUserByNameAndPass(String userName, String pass) {
        UserNameEntity user = Ebean.find(UserNameEntity.class)
                .where()
                .and(Expr.eq("userName", userName), Expr.eq("pass", pass))
                .findUnique();
        return user;
    }
}
