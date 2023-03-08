package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Order;
import mk.ukim.finki.wp.magacin.models.User;

import java.util.List;

public interface UserService {
  void register(String username, String password, String repeatPassword, String role);

  User login(String username, String password);

  void updateUser(String username, String password, String firstName,
                  String lastName,
                  String address,
                  String email,
                  String city,
                  String country,
                  String zipcode);

  void adminUserUpdate(String username, String firstName,
                       String lastName,
                       String address,
                       String email,
                       String city,
                       String country,
                       String zipcode);

  User getUser(String username);

  List<User> getAllUsers();

  User findUserById(String username);

  void deleteUser(String username);

  void changePassword(String username, String oldPassword, String newPassword);

  boolean checkPassword(String username, String pass);

  List<Order> listAllUserOrders(String username);
}
