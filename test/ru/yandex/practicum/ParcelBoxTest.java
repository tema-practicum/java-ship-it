package ru.yandex.practicum;
import ru.yandex.practicum.delivery.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParcelBoxTest {

    @Test
    void parcelIsAddedWhenWeightFits() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        box.addParcel(new StandardParcel("Книга", 5, "Москва", 1));
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void parcelIsNotAddedWhenWeightExceeds() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        box.addParcel(new StandardParcel("Книга", 11, "Москва", 1));
        assertTrue(box.getAllParcels().isEmpty());
    }

    @Test
    void parcelIsAddedWhenWeightEqualsLimit() { // граничный
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        box.addParcel(new StandardParcel("Книга", 10, "Москва", 1));
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void secondParcelRejectedIfSumExceedsLimit() { // граничный на накопление
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        box.addParcel(new StandardParcel("A", 6, "Москва", 1));
        box.addParcel(new StandardParcel("B", 5, "Москва", 1));
        assertEquals(1, box.getAllParcels().size());
    }
}