package services;

import models.Cart;
import models.CartItem;
import models.Product;

import java.util.List;
import java.util.Optional;

public class StoreService {
    private final List<Product> catalog;
    private final Cart cart;

    public StoreService(List<Product> catalog) {
        this.catalog = catalog;
        this.cart = new Cart();
    }

    //вынесена логика в getCatalogAsStringList
    public void showCatalog() {
        getCatalogAsStringList().forEach(System.out::println);
    }

    //возвращает список товаров в виде строк для отображения в showCatalog или тестирования
    public List<String> getCatalogAsStringList() {
        return catalog.stream()
                .map(p -> p.name() + " - " + p.price() + " руб.")
                .toList();
    }

    // Возвращает true, если товар добавлен в корзину, иначе false
    public boolean addProductToCart(String name, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество не может быть отрицательным.");
        }

        return findProductByName(name)
                .map(product -> {
                    cart.addItem(product, quantity);
                    return true;
                })
                .orElse(false);
    }

    // Поиск товара по имени (инкапсулируем Stream)
    private Optional<Product> findProductByName(String name) {
        return catalog.stream()
                .filter(p -> p.name().equalsIgnoreCase(name))
                .findFirst();
    }

    public void applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Скидка должна быть от 0 до 100%.");
        }
        cart.setDiscount(percent);
    }

    //вынесена логика в getCartSummaryLines
    public void printCart() {
        getCartSummaryLines().forEach(System.out::println);
    }

    //возвращает список строк, которые нужно вывести в корзине
    public List<String> getCartSummaryLines() {
        List<String> lines = cart.getItems().stream()
                .map(item -> item.getProduct().name()
                        + " x" + item.getQuantity()
                        + " = " + item.getTotalPrice())
                .collect(java.util.stream.Collectors.toCollection(java.util.ArrayList::new));

        lines.add(String.format("Итого со скидкой: %.1f", calculateTotal()));

        return lines;
    }

    public double calculateTotal() {
        double total = cart.getItems().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
        return total * (1 - cart.getDiscountPercent() / 100);
    }

    //метод для тестирования
    public List<CartItem> getCartItems() {
        return cart.getItems();
    }
}