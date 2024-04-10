package org.example;

import org.example.controller.UrlController;
import org.example.controller.dto.UrlDto;
import org.example.exception.URLisNotFind;
import org.example.repo.UrlRepositoryImp;
import org.example.service.UrlService;
import org.example.service.UrlServiceImp;
import org.example.service.object.Url;
import org.example.util.ReadUtil;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        while(true){
            UrlController urlController =
                new UrlController(new UrlServiceImp(new UrlRepositoryImp()));
                System.out.println(printSelection());
                String choosenService = ReadUtil.readLine();
                if (choosenService.equals("1")){
                    System.out.println("Введите длинный url");
                    String url = ReadUtil.readLine();
                    String shortUrl = urlController.addShortUrl(new UrlDto(url));
                    System.out.printf("Короткая ссылка: %s\n", shortUrl);
                }
                else if(choosenService.equals("2"))
                    System.out.println("Введите короткий url");
                    String url = ReadUtil.readLine();
                    try {
                        String longUrl = urlController.getLongUrl(url);
                        System.out.printf("Короткая ссылка: %s\n", longUrl);
                    }
                    catch(URLisNotFind ex){
                        System.out.println(ex.getMessage());
                    }
                }
        }
    }
    private static String printSelection(){
        return "Выберите действие: 1. Получить короткую ссылку 2. Получить длинную ссылку";
    }
}

