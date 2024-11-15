import fr.iut.*;
import fr.iut.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderManagementTest {
    // TODO: Declare products and cart
    Product product;
    ShoppingCart cart;
    User user;

    @BeforeEach
    void setUp() {
        product = new Product("product", 10.0, 1);
        cart = new ShoppingCart();
        user = new User("John", "Doe");
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

    @Test
    void testProductPrice() {
        assertThrows(InvalidPriceException.class, () -> new Product("product", -1.0, 1));
    }

    @Test
    void testProductStockQuantity() {
        assertThrows(InvalidStockQuantityException.class, () -> new Product("product", 10.0, -1));
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
    @Test
    void testInvoice() throws OutOfStockException, EmptyOrderException {
        cart.addProduct(product);
        Order order = new Order(cart, user);
        Invoice invoice = new Invoice(order);
    }

    @Test
    void testGenerateInvoice() throws OutOfStockException, EmptyOrderException {
        cart.addProduct(product);
        Order order = new Order(cart, user);
        Invoice invoice = new Invoice(order);
        String expected = "=== FACTURE ===\n\nClient: John Doe (100 points de fidélité) \nArticles:\n- product: 10,00 €\n\nSous-total: 10,00 €\nFrais de livraison: 5,00 €\n\nTotal: 15,00 €\n";
        assertEquals(expected, invoice.generateInvoice());
    }

    @Test
    void testGenerateInvoiceWithDiscount() throws OutOfStockException, InvalidDiscountCodeException, EmptyOrderException {
        cart.addProduct(product);
        Order order = new Order(cart, user);
        order.applyDiscount("PROMO10");
        Invoice invoice = new Invoice(order);
        String expected = "=== FACTURE ===\n\nClient: John Doe (100 points de fidélité) \nArticles:\n- product: 10,00 €\n\nSous-total: 10,00 €\nFrais de livraison: 5,00 €\nRemise: 10,00%\n\nTotal: 13,50 €\n";
        assertEquals(expected, invoice.generateInvoice());
    }


    // TODO: Implement tests for Order
    @Test
    void testOrder() throws OutOfStockException {
        cart.addProduct(product);
        Order order = new Order(cart, user);
        assertEquals(15.0, order.getTotalPrice());
        assertEquals(5.0, order.getDeliveryFee());
        assertEquals(0.0, order.getDiscount());
        assertEquals(cart, order.getShoppingCart());
    }

    @Test
    void testOrderWithTenDiscount() throws OutOfStockException, InvalidDiscountCodeException {
        cart.addProduct(product);
        Order order = new Order(cart, user);
        order.applyDiscount("PROMO10");
        assertEquals(13.5, order.getTotalPrice());
        assertEquals(5.0, order.getDeliveryFee());
        assertEquals(0.10, order.getDiscount());
        assertEquals(cart, order.getShoppingCart());
    }

    @Test
    void testOrderWithTwentyDiscount() throws OutOfStockException, InvalidDiscountCodeException {
        cart.addProduct(product);
        Order order = new Order(cart, user);
        order.applyDiscount("PROMO20");
        assertEquals(12.0, order.getTotalPrice());
        assertEquals(5.0, order.getDeliveryFee());
        assertEquals(0.20, order.getDiscount());
        assertEquals(cart, order.getShoppingCart());
    }

    @Test
    void testOrderWithInvalidDiscount() throws OutOfStockException {
        cart.addProduct(product);
        Order order = new Order(cart, user);
        assertThrows(InvalidDiscountCodeException.class, () -> order.applyDiscount("PROMO30"));
    }

    @Test
    void testInvoiceWithEmptyOrder() {
        while (cart.getItemCount() != 0){
            cart.removeProduct(product);
        }
        Order order = new Order(cart, user);
        assertThrows(EmptyOrderException.class, () -> new Invoice(order));
    }

    @Test
    void testConstants() {
        assertEquals(100, Constants.FIDELITY_POINTS_PER_TEN_EUROS);
    }

}
