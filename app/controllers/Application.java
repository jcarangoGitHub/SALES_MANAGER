package controllers;

import models.ItemEntity;
import models.UserNameEntity;
import persistence.ItemPersistence;
import persistence.UserPersistence;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.seller;

import java.util.List;
import java.util.Map;

public class Application extends Controller {
  public static String homePageString = "This page has been retrieved ";

  public static Result index() {

    return ok(index.render(""));
  }

  public static Result authenticate() {
    final Map<String, String[]> values = request().body().asFormUrlEncoded();
    String userName = values.get("userName")[0];
    String pass = values.get("pass")[0];
    UserPersistence userPersistence = new UserPersistence();
    UserNameEntity user = userPersistence.getUserByNameAndPass(userName, pass);

    if (null != user) {
      ItemPersistence persistence = new ItemPersistence();
      List<ItemEntity> list = persistence.getAllItems();

      return ok(seller.render("Productos disponibles", list, userName));
    }else {
      return ok(index.render("Nombre de usuario o contraseña inválida"));
    }


  }

}
