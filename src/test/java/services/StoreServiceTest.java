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
    @DisplayName("Каталог содержит ожидаемые строки")
    void getCatalogAsStringList_shouldReturnFormattedProductList() {
        List<String> catalogLines = storeService.getCatalogAsStringList();

        assertEquals(1, catalogLines.size());
        assertTrue(catalogLines.contains("shirt - 1000.0 руб."));
    }

    @Test
    @DisplayName("Пустой каталог возвращает пустой список строк")
    void getCatalogAsStringList_shouldReturnEmptyList() {
        StoreService svc = new StoreService(new ArrayList<>());
        List<String> catalogLines = svc.getCatalogAsStringList();
        assertTrue(catalogLines.isEmpty());
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
    @DisplayName("Корректно возвращает данные корзины")
    void getCartSummaryLines_shouldReturnFormattedLines() {
        storeService.applyDiscount(10);
        List<String> lines = storeService.getCartSummaryLines();

        assertTrue(lines.contains("shirt x2 = 2000.0"));
        assertTrue(lines.contains("Итого со скидкой: 1800,0"));
    }

    @Test
    @DisplayName("Возвращает 0 для пустой корзины")
    void calculateTotal_shouldBeZeroWhenEmpty() {
        StoreService svc = new StoreService(List.of());
        double total = svc.calculateTotal();
        assertEquals(0, total);
    }
}
