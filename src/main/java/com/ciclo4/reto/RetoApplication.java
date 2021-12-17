package com.ciclo4.reto;


import com.ciclo4.reto.interfaces.InterfaceOrder;
import com.ciclo4.reto.interfaces.InterfaceSupplements;
import com.ciclo4.reto.interfaces.InterfaceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


@Component
@SpringBootApplication

/**
**
*/
public class RetoApplication implements CommandLineRunner {
	@Autowired
	private InterfaceSupplements interfaceSupplements;
	@Autowired
	private InterfaceUser interfaceUser;
	@Autowired
	private InterfaceOrder interfaceOrder;
	public static void main(String[] args) {
		SpringApplication.run(RetoApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		interfaceSupplements.deleteAll();
		interfaceUser.deleteAll();
		interfaceOrder.deleteAll();
	}

}