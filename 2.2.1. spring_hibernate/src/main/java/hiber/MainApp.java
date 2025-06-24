package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      if (userService.listUsers().isEmpty()) {

         List<User> users = new ArrayList<>();
         users.add(new User("User1", "Lastname1", "user1@mail.ru", null));
         users.add(new User("User2", "Lastname2", "user2@mail.ru", null));
         users.add(new User("User3", "Lastname3", "user3@mail.ru", null));
         users.add(new User("User4", "Lastname4", "user4@mail.ru", null));

         for (User user : users) {
            userService.add(user);
         }

         List<Car> cars = new ArrayList<>();
         cars.add(new Car("BMW", 3));
         cars.add(new Car("Audi", 5));
         cars.add(new Car("Lada", 2107));
         cars.add(new Car("Toyota", 1));

         for (Car car : cars) {
         }
         List<User> savedUsers = userService.listUsers();

         for (int i = 0; i < Math.min(savedUsers.size(), cars.size()); i++) {
            User user = savedUsers.get(i);
            user.setCar(cars.get(i));
            userService.add(user);
         }
      }

      System.out.println("Список всех пользователей:");
      for (User user : userService.listUsers()) {
         System.out.println(user);
      }

      System.out.println("\nПоиск пользователя по машине:");
      User foundUser = userService.getUserByCar("BMW", 3);
      if (foundUser != null) {
         System.out.println("Найден пользователь: " + foundUser);
      } else {
         System.out.println("Пользователь не найден.");
      }

      context.close();
   }
}
