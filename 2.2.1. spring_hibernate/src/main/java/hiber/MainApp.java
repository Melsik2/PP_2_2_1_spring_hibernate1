package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      CarService carService = context.getBean(CarService.class);
      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("BMW", 3);
      Car car2 = new Car("Audi", 5);
      Car car3 = new Car("Lada", 2107);
      Car car4 = new Car("Toyota", 1);

      carService.add(car1);
      carService.add(car2);
      carService.add(car3);
      carService.add(car4);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru", car1);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru", car2);
      User user3 = new User("User3", "Lastname3", "user3@mail.ru", car3);
      User user4 = new User("User4", "Lastname4", "user4@mail.ru", car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      System.out.println("Список всех пользователей:");
      for (User user : users) {
         System.out.println(user);
      }

      System.out.println("\nПоиск пользователя по машине (BMW, 3):");
      User userByCar = userService.getUserByCar("BMW", 3);
      System.out.println("Найден пользователь: " + userByCar);

      context.close();
   }
}

