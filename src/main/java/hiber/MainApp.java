package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Maxim", "kapustin", "maxkap@mail.ru");
      User user2 = new User("Kevin", "Holand", "holland@mail.ru");
      Car car1 = new Car("Lada",3821);
      Car car2 = new Car("Maserati",1321);
      user1.setCar(car1);
      user2.setCar(car2);
      userService.add(user1);
      userService.add(user2);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+ user.getCar().getModel());
      }
      System.out.println("------------------------------");
      System.out.println(userService.getByCar("Maserati",1321 ).toString());

      context.close();
   }
}
