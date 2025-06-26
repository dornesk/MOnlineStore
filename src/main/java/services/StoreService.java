package services;

import models.Cart;
import models.CartItem;
import models.Product;

import java.util.List;

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

    //Добавлена проверка на отрицательное количество и использование Stream API для поиска товара
    public void addProductToCart(String name, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество не может быть отрицательным.");
        }

        catalog.stream()
                .filter(n -> n.name().equalsIgnoreCase(name))
                .findAny()
                .ifPresentOrElse(o -> {
                            cart.addItem(o, quantity);
                            System.out.println("Добавлено: " + name + " x" + quantity);
                        },
                        () -> System.out.println("Товар не найден: " + name));

    }

    public void applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Скидка должна быть от 0 до 100%.");
        }
        cart.setDiscount(percent);
    }

    public void printCart() {
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getProduct().name() + " x" + item.getQuantity() + " = " + item.getTotalPrice());
        }
        System.out.println("Итого со скидкой: " + calculateTotal());
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : cart.getItems()) {
            total += item.getTotalPrice();
        }
        double discountAmount = total * cart.getDiscountPercent() / 100;
        return total - discountAmount;
    }

    //метод для тестирования
    public List<CartItem> getCartItems() {
        return cart.getItems();
    }
}