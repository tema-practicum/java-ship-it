package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParcelTest {

    @Test
    void standardParcelCostIsWeightTimesTwo() {
        StandardParcel parcel = new StandardParcel("Книга", 5, "Москва", 1);
        assertEquals(10, parcel.calculateDeliveryCost());
    }

    @Test
    void fragileParcelCostIsWeightTimesFour() {
        FragileParcel parcel = new FragileParcel("Ваза", 3, "Питер", 1);
        assertEquals(12, parcel.calculateDeliveryCost());
    }

    @Test
    void perishableParcelCostIsWeightTimesThree() {
        PerishableParcel parcel = new PerishableParcel("Торт", 2, "Казань", 1, 3);
        assertEquals(6, parcel.calculateDeliveryCost());
    }

    @Test
    void zeroWeightCostIsZero() { // граничный
        StandardParcel parcel = new StandardParcel("Пусто", 0, "Сочи", 1);
        assertEquals(0, parcel.calculateDeliveryCost());
    }
}