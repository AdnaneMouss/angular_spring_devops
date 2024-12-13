package uir.ac.ma.inventory.inventoryapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class InventoryappApplicationTests {

	@Test
	void contextLoads() {
	}

}
