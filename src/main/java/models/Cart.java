package models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();
    private double discountPercent = 0.0;

    public void addItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть больше 0");
        }

        items.stream()
                .filter(n -> n.getProduct().name().equalsIgnoreCase(product.name()))
                .findFirst()
                .ifPresentOrElse(
                        existing -> //если нашли - увеличиваем количество
                                existing.setQuantity(existing.getQuantity()+quantity),
                        () -> //не нашли - добавляем новый товар
                                items.add(new CartItem(product, quantity))
                );
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscount(double percent) {
        this.discountPercent = percent;
    }
}