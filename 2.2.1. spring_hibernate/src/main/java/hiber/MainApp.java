package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      if (userService.listUsers().isEmpty()) {
         userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW", 3)));
         userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Audi", 5)));
         userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Lada", 2107)));
         userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Toyota", 1)));
      }

      System.out.println("Список всех пользователей:");
      for (User user : userService.listUsers()) {
         System.out.println(user);
      }

      System.out.println("\nПоиск пользователя по машине:");
      User user = userService.getUserByCar("BMW", 3);
      System.out.println("Найден пользователь: " + user);

      context.close();
   }
}
