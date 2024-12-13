package uir.ac.ma.inventory.inventoryapp;

import org.springframework.boot.SpringApplication;

public class TestInventoryappApplication {

	public static void main(String[] args) {
		SpringApplication.from(InventoryappApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
