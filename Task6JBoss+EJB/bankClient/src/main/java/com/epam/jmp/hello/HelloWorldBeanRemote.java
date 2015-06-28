package com.epam.jmp.hello;

import javax.ejb.Remote;

@Remote
public interface HelloWorldBeanRemote {

	public String sayHello();
}
