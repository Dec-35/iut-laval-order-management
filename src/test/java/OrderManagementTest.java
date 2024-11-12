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
    void setUp() {
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
    void testAddProductToCart() throws OutOfStockException {
        cart.addProduct(product);
        assertEquals(1, cart.getItemCount());
        assertEquals(10.0, cart.getTotalPrice());
        assertEquals(0, product.getStockQuantity());

        assertThrows(OutOfStockException.class, () -> cart.addProduct(product)); //Qu'un seul restant en stock

    }

    @Test
    void testCartSize(){
        assertEquals(0, cart.getItemCount());
        assertEquals(1, product.getStockQuantity());
    }

    @Test
    void testRemoveProductFromCart() throws OutOfStockException {
        cart.addProduct(product);
        assertEquals(1, cart.getItemCount());
        assertEquals(0, product.getStockQuantity());

        cart.removeProduct(product);
        assertEquals(0, cart.getItemCount());
        assertEquals(1, product.getStockQuantity());
    }

    @Test
    void testCartProducts() throws OutOfStockException {
        cart.addProduct(product);

        List<Product> products = new ArrayList<>();
        products.add(product);
        assertEquals(products, cart.getProductList());

        products = cart.getProductList();
        assertEquals(products, cart.getProductList());
    }

    // TODO: Implement tests for Invoice

    // TODO: Implement tests for Order
    @Test
    void testOrder() throws OutOfStockException {
        cart.addProduct(product);
        Order order = new Order(cart);
        assertEquals(15.0, order.getTotalPrice());
        assertEquals(5.0, order.getDeliveryFee());
        assertEquals(0.0, order.getDiscount());
        assertEquals(cart, order.getShoppingCart());
    }

    @Test
    void testOrderWithTenDiscount() throws OutOfStockException, InvalidDiscountCodeException {
        cart.addProduct(product);
        Order order = new Order(cart);
        order.applyDiscount("PROMO10");
        assertEquals(13.5, order.getTotalPrice());
        assertEquals(5.0, order.getDeliveryFee());
        assertEquals(0.10, order.getDiscount());
        assertEquals(cart, order.getShoppingCart());
    }

    @Test
    void testOrderWithTwentyDiscount() throws OutOfStockException, InvalidDiscountCodeException {
        cart.addProduct(product);
        Order order = new Order(cart);
        order.applyDiscount("PROMO20");
        assertEquals(12.0, order.getTotalPrice());
        assertEquals(5.0, order.getDeliveryFee());
        assertEquals(0.20, order.getDiscount());
        assertEquals(cart, order.getShoppingCart());
    }

    @Test
    void testOrderWithInvalidDiscount() throws OutOfStockException {
        cart.addProduct(product);
        Order order = new Order(cart);
        assertThrows(InvalidDiscountCodeException.class, () -> order.applyDiscount("PROMO30"));
    }

}
