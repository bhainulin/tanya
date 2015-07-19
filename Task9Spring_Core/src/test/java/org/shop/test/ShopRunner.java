package org.shop.test;

import java.util.List;

import org.shop.api.UserService;
import org.shop.data.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ShopRunner {

	public static void main(String[] args) {
		
		

/*		����������� ����� main ��� ShopLauncher ������

		        ������� � ���������������� ApplicationContext
		        �������� bean �� �����
		        �������� bean �� ����
		        �������� bean �� ����� � ����
		        �������� bean �� alias
		        ��������� ����������������� */
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/context.xml");

		System.out.println(">>> �������� bean �� �����");
		UserService byName = (UserService) applicationContext.getBean("userService");
		printUsers(byName);

		System.out.println(">>> �������� bean �� ����");
		UserService byType = applicationContext.getBean(UserService.class);
		printUsers(byType);

		System.out.println(">>> �������� bean �� ����� � ����");
		UserService byTypeAndName = applicationContext.getBean("userService", UserService.class);		
		printUsers(byTypeAndName);

		System.out.println(">>> �������� bean �� alias");
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
