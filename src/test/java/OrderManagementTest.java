import fr.iut.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.iut.exceptions.InvalidDiscountCodeException;
import fr.iut.exceptions.OutOfStockException;
import fr.iut.Invoice;
import fr.iut.Order;
import fr.iut.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderManagementTest {
    // TODO: Declare products and cart
    Product product;
    ShoppingCart cart;

    @BeforeEach
    void setUp() throws OutOfStockException {
        product = new Product("product", 10.0, 1);
        cart = new ShoppingCart();
    }

    // Tests for Product
    @Test
    void testCreateProduct() {
        assertEquals("product", product.getName());
        assertEquals(10.0, product.getPrice());
        assertEquals(1, product.getStockQuantity());
    }

    @Test
    void testProductStock() {
        product.decrementStock();
        assertEquals(0, product.getStockQuantity());
        product.decrementStock();
        assertEquals(0, product.getStockQuantity());
        product.incrementStock();
        assertEquals(1, product.getStockQuantity());
    }

    // Tests for ShoppingCart
    @Test
    void testCart() throws OutOfStockException {
        Product product = new Product("product", 10.0, 1);
        cart.addProduct(product);
        assertEquals(1, cart.getItemCount());
        assertEquals(10.0, cart.getTotalPrice());
        assertEquals(0, product.getStockQuantity());
        cart.removeProduct(product);

        assertEquals(0, cart.getItemCount());
        assertEquals(1, product.getStockQuantity());

        cart.removeProduct(product); // Ne fait rien

        cart.addProduct(product);

        List<Product> products = new ArrayList<>();
        products.add(product);
        assertEquals(products, cart.getProductList());

        products = cart.getProductList();
        assertEquals(products, cart.getProductList());

        assertThrows(OutOfStockException.class, () -> cart.addProduct(product)); //Qu'un sel stock
    }

    // TODO: Implement tests for Invoice

    // TODO: Implement tests for Order
}
