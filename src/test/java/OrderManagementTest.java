import fr.iut.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.iut.exceptions.InvalidDiscountCodeException;
import fr.iut.exceptions.OutOfStockException;
import fr.iut.Invoice;
import fr.iut.Order;
import fr.iut.Product;
import fr.iut.ShoppingCart;

import static org.junit.jupiter.api.Assertions.*;

public class OrderManagementTest {
    // TODO: Declare products and cart
    ShoppingCart cart;

    @BeforeEach
    void setUp() throws OutOfStockException {
        // TODO: Initialize products and cart
        Product product1 = new Product("product1", 10.0, 1);
        Product product2 = new Product("product2", 20.0, 10);

        cart = new ShoppingCart();
        cart.addProduct(product1);
        cart.addProduct(product2);
    }

    // TODO: Implement tests for Product
    @Test
    void testProduct() {
        Product product = new Product("product", 10.0, 1);
        assertEquals("product", product.getName());
        assertEquals(10.0, product.getPrice());
        assertEquals(1, product.getStockQuantity());
    }

    // TODO: Implement tests for ShoppingCart

    // TODO: Implement tests for Invoice

    // TODO: Implement tests for Order
}
