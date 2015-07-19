package org.shop.test;

import java.util.List;

import org.shop.api.UserService;
import org.shop.data.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ShopRunner {

	public static void main(String[] args) {
		
		

/*		Реализовать метод main для ShopLauncher класса

		        Создать и инициализировать ApplicationContext
		        Получить bean по имени
		        Получить bean по типу
		        Получить bean по имени и типу
		        Получить bean по alias
		        Проверить работоспособность */
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/context.xml");

		System.out.println(">>> Получить bean по имени");
		UserService byName = (UserService) applicationContext.getBean("userService");
		printUsers(byName);

		System.out.println(">>> Получить bean по типу");
		UserService byType = applicationContext.getBean(UserService.class);
		printUsers(byType);

		System.out.println(">>> Получить bean по имени и типу");
		UserService byTypeAndName = applicationContext.getBean("userService", UserService.class);		
		printUsers(byTypeAndName);

		System.out.println(">>> Получить bean по alias");
		UserService byAlias = (UserService) applicationContext.getBean("userService_Al");
		printUsers(byAlias);
	}

	private static void printUsers(UserService bean) {
		List<User> users = bean.getUsers();
		for(User u : users){
			System.out.println(u);	
		}
	}

}
