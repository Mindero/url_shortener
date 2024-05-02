package org.example;

import org.example.controller.UrlController;
import org.example.controller.dto.UrlDto;
import org.example.controller.dto.UserDto;
import org.example.exception.URLisNotFind;
import org.example.exception.UserExistException;
import org.example.exception.UserPasswordIncorrect;
import org.example.exception.logoutException;
import org.example.repo.url.UrlRepositoryImp;
import org.example.repo.user.UserRepositoryImp;
import org.example.service.url.UrlServiceImp;
import org.example.service.user.UserServiceImp;
import org.example.util.ReadUtil;
import org.example.jdbc.jdbcUtils;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try{
            boolean connected = jdbcUtils.createConnection();
            if (!connected){
                System.out.println("Error with connection to db");
                return;
            }
            // init:
            //jdbcUtils.createTables();
            userInterface();
        }
        finally {
            jdbcUtils.closeConnection();
        }
    }
    public static void userInterface(){
        while (true) {
            UrlController urlController = new UrlController(new UserServiceImp(new UserRepositoryImp(),
                    new UrlServiceImp(new UrlRepositoryImp())));
            System.out.println(printSelection());
            String choosenService = ReadUtil.readLine();
            Scanner sc = new Scanner(System.in); //System.in is a standard input stream
            if (choosenService.equals("1")) {
                System.out.println("Введите логин и пароль");
                String login = sc.next();
                String password = sc.next();
                try {
                    urlController.login(new UserDto(login, password));
                    System.out.println("Вы успешно вошли в аккаунт");
                } catch (UserPasswordIncorrect ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (choosenService.equals("2")) {
                System.out.println("Введите логин и пароль");
                String login = sc.next();
                String password = sc.next();
                try {
                    urlController.register(new UserDto(login, password));
                    System.out.println("Вы успешно зарегестрировались");
                } catch (UserExistException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if (choosenService.equals("3")) {
                System.out.println("Введите длинный url");
                String url = ReadUtil.readLine();
                String shortUrl = urlController.addShortUrl(new UrlDto(url));
                System.out.printf("Короткая ссылка: %s\n", shortUrl);
            } else if (choosenService.equals("4")) {
                System.out.println("Введите короткий url");
                String url = ReadUtil.readLine();
                try {
                    String longUrl = urlController.getLongUrl(url);
                    System.out.printf("Короткая ссылка: %s\n", longUrl);
                } catch (URLisNotFind ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if (choosenService.equals("5")) {
                try{
                    urlController.logout();
                    System.out.println("Вы вышли из аккаунта");
                }
                catch(logoutException ex){
                    System.out.println(ex.getMessage());
                }
            }
            urlController.print();
        }
    }

    private static String printSelection(){
        return "Выберите действие: 1. Login\n2. Register\n3. Получить короткую ссылку\n4. Получить длинную ссылку\n" +
                "5. logout";
    }
}

