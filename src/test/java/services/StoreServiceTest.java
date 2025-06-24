package services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StoreServiceTest {
    private StoreService storeService;

    @BeforeEach
    void setUp() {
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
    @DisplayName("Корректно добавляет товар в каталог")
    void addProductToCart_shouldAddProductToCart() {

    }

    @Test
    @DisplayName("Выбрасывает исключение при количестве <=0")
    void addProductToCart_shouldThrowIllegalArgException() {

    }

    @Test
    @DisplayName("Корректное применение скидки")
    void applyDiscount_shouldApplyDiscount() {

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
