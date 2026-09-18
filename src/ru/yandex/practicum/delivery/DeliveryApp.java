package ru.yandex.practicum.delivery;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);

    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<Trackable> trackableParcels = new ArrayList<>();

    private static final ParcelBox<StandardParcel> standardBox = new ParcelBox<>(50);
    private static final ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(20);
    private static final ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(30);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    showBoxContent();
                    break;
                case 5:
                    reportStatusForTrackable();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Показать содержимое коробки");
        System.out.println("5 — Трекинг отправлений");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Выберите тип посылки: 1 — стандартная, 2 — хрупкая, 3 — скоропортящаяся");
        int type = Integer.parseInt(scanner.nextLine());

        System.out.print("Описание: ");
        String description = scanner.nextLine();

        System.out.print("Вес: ");
        int weight = Integer.parseInt(scanner.nextLine());

        System.out.print("Адрес доставки: ");
        String address = scanner.nextLine();

        System.out.print("День отправки (число): ");
        int sendDay = Integer.parseInt(scanner.nextLine());

        switch (type) {
            case 1 -> {
                StandardParcel parcel = new StandardParcel(description, weight, address, sendDay);
                allParcels.add(parcel);
                standardBox.addParcel(parcel);
            }
            case 2 -> {
                FragileParcel parcel = new FragileParcel(description, weight, address, sendDay);
                allParcels.add(parcel);
                trackableParcels.add(parcel);
                fragileBox.addParcel(parcel);
            }
            case 3 -> {
                System.out.print("Срок хранения (дней): ");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel parcel = new PerishableParcel(description, weight, address, sendDay, timeToLive);
                allParcels.add(parcel);
                perishableBox.addParcel(parcel);
            }
            default -> System.out.println("Неверный тип посылки.");
        }
    }

    private static void sendParcels() {
        if (allParcels.isEmpty()) {
            System.out.println("Нет посылок для отправки.");
            return;
        }
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        if (allParcels.isEmpty()) {
            System.out.println("Нет посылок для расчёта.");
            return;
        }
        int total = 0;
        for (Parcel parcel : allParcels) {
            total += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость доставки: " + total);
    }

    private static void showBoxContent() {
        System.out.println("Выберите коробку: 1 — стандартные, 2 — хрупкие, 3 — скоропортящиеся");
        int type = Integer.parseInt(scanner.nextLine());
        switch (type) {
            case 1 -> printBox(standardBox);
            case 2 -> printBox(fragileBox);
            case 3 -> printBox(perishableBox);
            default -> System.out.println("Неверный выбор.");
        }
    }

    private static void printBox(ParcelBox<? extends Parcel> box) {
        List<? extends Parcel> parcels = box.getAllParcels();
        if (parcels.isEmpty()) {
            System.out.println("Коробка пуста.");
            return;
        }
        for (Parcel parcel : parcels) {
            System.out.println("- " + parcel.getDescription());
        }
    }

    private static void reportStatusForTrackable() {
        if (trackableParcels.isEmpty()) {
            System.out.println("Нет отправлений с трекингом.");
            return;
        }
        System.out.print("Введите новое местоположение: ");
        String newLocation = scanner.nextLine();
        for (Trackable t : trackableParcels) {
            t.reportStatus(newLocation);
        }
    }
}