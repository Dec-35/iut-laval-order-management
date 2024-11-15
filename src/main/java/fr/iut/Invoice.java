package fr.iut;

import fr.iut.exceptions.EmptyOrderException;
import fr.iut.exceptions.EmptyShoppingCartException;
import fr.iut.exceptions.NegativeOrderPrice;

public class Invoice {
    private final Order order;

    public Invoice(Order order) throws EmptyOrderException, NegativeOrderPrice, EmptyShoppingCartException {
        if (order == null)
        {
            throw new EmptyOrderException();
        }
        if (order.getTotalPrice() < 0)
        {
            throw new NegativeOrderPrice();
        }
        if (order.getShoppingCart() == null)
        {
            throw new EmptyShoppingCartException();
        }
        this.order = order;
    }

    public String generateInvoice() {
        StringBuilder invoice = new StringBuilder();
        invoice.append("=== FACTURE ===\n\n");
        User user = order.getUser();
        invoice.append(String.format("Client: %s \n", user.toString()));
        invoice.append("Articles:\n");
        
        for (Product product : order.getShoppingCart().getProductList()) {
            invoice.append(String.format("- %s: %.2f €\n", 
                product.getName(), product.getPrice()));
        }
        
        invoice.append(String.format("\nSous-total: %.2f €\n", 
            order.getShoppingCart().getTotalPrice()));
        invoice.append(String.format("Frais de livraison: %.2f €\n", 
            order.getDeliveryFee()));
        
        if (order.getDiscount() > 0) {
            invoice.append(String.format("Remise: %.2f%%\n",
                order.getDiscount()*100)); //Added conversion to percentage
        }
        
        invoice.append(String.format("\nTotal: %.2f €\n", 
            order.getTotalPrice()));
        
        return invoice.toString();
    }
}
