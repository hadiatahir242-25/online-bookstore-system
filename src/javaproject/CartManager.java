package javaproject;

import java.util.HashMap;
import java.util.Map;

public class CartManager {
    public static Map<BookItem, Integer> cartItems = new HashMap<>();

    public static void addToCart(BookItem item) {
        cartItems.put(item, cartItems.getOrDefault(item, 0) + 1);
    }

    public static void removeOne(BookItem item) {
        if (cartItems.containsKey(item)) {
            int count = cartItems.get(item);
            if (count > 1) {
                cartItems.put(item, count - 1);
            } else {
                cartItems.remove(item);
            }
        }
    }

    public static void clearCart() {
        cartItems.clear();
    }

    public static double getTotal() {
        double total = 0;
        for (Map.Entry<BookItem, Integer> entry : cartItems.entrySet()) {
            try {
                double price = Double.parseDouble(entry.getKey().getPrice().replace("$", ""));
                total += price * entry.getValue();
            } catch (NumberFormatException ignored) {}
        }
        return total;
    }
}
