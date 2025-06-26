package services;

import models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreServiceTest {
    private StoreService storeService;

    @BeforeEach
    void setUp() {
        List<Product> catalog = new ArrayList<>();
        storeService = new StoreService(catalog);
        catalog.add(new Product("shirt", 1000));
        storeService.addProductToCart("shirt", 2);
    }

    @Test
    @DisplayName("Высвечивает каталог по команде")
    void showCatalog_shouldShowCatalog() {

    }

    @Test
    @DisplayName("Высвечивает сообщение о пустом каталоге")
    void showCatalog_shouldShowNoCatalog() {

    }

    @Test
    @DisplayName("Корректно добавляет товар в корзину")
    void addProductToCart_shouldAddProductToCart() {
        boolean result = storeService.addProductToCart("shirt", 1);
        assertTrue(result);
        assertEquals(1, storeService.getCartItems().size());
    }


    @Test
    @DisplayName("Выбрасывает исключение при количестве <=0")
    void addProductToCart_shouldThrowIllegalArgException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                storeService.addProductToCart("shirt", -1));
    }

    @Test
    @DisplayName("Корректное применение скидки")
    void applyDiscount_shouldApplyDiscount() {
        storeService.applyDiscount(25);
        double total = storeService.calculateTotal();

        assertEquals(1500, total);
    }

    @Test
    @DisplayName("Корректное применение скидки")
    void applyDiscount_shouldThrowIllegalArgException() {
        assertThrows(IllegalArgumentException.class, () -> storeService.applyDiscount(250));
        assertThrows(IllegalArgumentException.class, () -> storeService.applyDiscount(-5));
    }

    @Test
    @DisplayName("Должен корректно показать содержимое корзины")
    void printCart_shouldPrintCart() {

    }

    @Test
    @DisplayName("Должен корректно обработать пустую корзину")
    void printCart_shouldHandleEmptyCart() {

    }

    @Test
    @DisplayName("Корректный подсчет итоговой суммы с учетом скидки")
    void calculateTotal_shouldCalculate() {

    }

}
