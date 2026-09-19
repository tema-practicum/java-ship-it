package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PerishableParcelTest {

    @Test
    void notExpiredBeforeDeadline() {
        PerishableParcel parcel = new PerishableParcel("Молоко", 1, "Тверь", 1, 5);
        assertFalse(parcel.isExpired(3));
    }

    @Test
    void notExpiredOnDeadlineDay() { // граничный: sendDay + ttl == currentDay
        PerishableParcel parcel = new PerishableParcel("Молоко", 1, "Тверь", 1, 5);
        assertFalse(parcel.isExpired(6));
    }

    @Test
    void expiredAfterDeadline() {
        PerishableParcel parcel = new PerishableParcel("Молоко", 1, "Тверь", 1, 5);
        assertTrue(parcel.isExpired(7));
    }
}