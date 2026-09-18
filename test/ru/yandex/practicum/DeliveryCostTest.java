package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.yandex.practicum.delivery.*;

class DeliveryCostTest {

    @Test
    void standardParcelCost() {
        StandardParcel p = new StandardParcel("Книга", 5, "Москва", 1);
        assertEquals(10, p.calculateDeliveryCost());
    }

    @Test
    void fragileParcelCost() {
        FragileParcel p = new FragileParcel("Ваза", 3, "Питер", 1);
        assertEquals(12, p.calculateDeliveryCost());
    }

    @Test
    void perishableParcelCost() {
        PerishableParcel p = new PerishableParcel("Торт", 2, "Казань", 1, 3);
        assertEquals(6, p.calculateDeliveryCost());
    }

    @Test
    void zeroWeightCostIsZero() {
        StandardParcel p = new StandardParcel("Пусто", 0, "Сочи", 1);
        assertEquals(0, p.calculateDeliveryCost());
    }

    @Test
    void unitWeightCostEqualsBaseCost() {
        FragileParcel p = new FragileParcel("Мелочь", 1, "Сочи", 1);
        assertEquals(4, p.calculateDeliveryCost());
    }
}