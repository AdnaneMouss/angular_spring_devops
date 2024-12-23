package uir.ac.ma.inventory.inventoryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uir.ac.ma.inventory.inventoryapp.model.Orders;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

}
