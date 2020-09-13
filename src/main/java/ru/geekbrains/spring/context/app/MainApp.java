package ru.geekbrains.spring.context.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.spring.context.app.meta.Cart;
import ru.geekbrains.spring.context.app.meta.ProductService;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = context.getBean("productService", ProductService.class);
        Cart cart = context.getBean("cart", Cart.class);

        Scanner in = new Scanner(System.in);
        String command;

        System.out.print("Введите команду ('/quit' - выход): ");

        while (in.hasNextLine()) {
            command = in.nextLine();
            if (command.equals("/quit")) {
                break;
            } else {
                String[] parts = command.split(" ");
//                for (String part : parts) {
//                    System.out.println(part);
//                }

                switch (parts[0]) {
                    case "/help":
                        System.out.println("/help - список команд");
                        System.out.println("/list - список продуктов");
                        System.out.println("/product N - продукт N");
                        System.out.println("/add2Cart N - добавить продукт N в корзину");
                        System.out.println("/del2Cart N - удалить продукт N из корзины");
                        System.out.println("/cart - корзина");
                        System.out.println("/quit - выход");
                        break;
                    case "/list":
                        System.out.println(productService.getProducts());
                        break;
                    case "/product":
                        if (parts.length > 1) {
                            System.out.println(productService.getProduct(Long.valueOf(parts[1])));
                        } else System.out.println("Неверная команда, список команд /help");
                        break;
                    case "/add2Cart":
                        if (parts.length > 1) {
                            cart.addProduct(productService.getProduct(Long.valueOf(parts[1])));
                        } else System.out.println("Неверная команда, список команд /help");
                        break;
                    case "/del2Cart":
                        if (parts.length > 1) {
                            cart.delProduct(Long.valueOf(parts[1]));
                        } else System.out.println("Неверная команда, список команд /help");
                        break;
                    case "/cart":
                        System.out.println(cart.getProducts());
                        break;
                    default:
                        System.out.println("Неверная команда, список команд /help");
                }
            }
            System.out.print("Введите команду ('/quit' - выход): ");
        }
        context.close();
    }
}
