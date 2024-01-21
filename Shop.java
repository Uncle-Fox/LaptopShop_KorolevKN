import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Shop {
    public static void main(String[] args) {
        // String name, String cpu, String memory, String gpu, String ram, String os,
        // String color
        Laptop laptop1 = new Laptop("Acer Swift X1", "AMD Ryzen 7 5800U", 512, "NVIDIA GeForce GTX 1650",
                16, "Windows 11", "Mystic Black");
        Laptop laptop2 = new Laptop("Dell Inspiron 14 Pro", "Intel Core i5-1135G7", 256,
                "Intel Iris Xe Graphics", 8, "Ubuntu Linux", "Silver");
        Laptop laptop3 = new Laptop("HP Spectre X360", "Intel Core i7-1165G7", 1024, "Intel Iris Xe",
                32, "Windows 10", "Poseidon Blue");
        Laptop laptop4 = new Laptop("ASUS ROG Zephyrus G14", "AMD Ryzen 9 5900HS", 1024, "NVIDIA RTX 3060",
                32, "Windows 11", "Eclipse Gray");
        Laptop laptop5 = new Laptop("Lenovo Yoga Slim 7", "AMD Ryzen 5 5600U", 512, "AMD Radeon Graphics",
                16, "Windows 10", "Slate Grey");
        Laptop laptop6 = new Laptop("MSI Creator Z16", "Intel Core i9-11900H", 2048, "NVIDIA RTX 3080",
                64, "Windows 11", "Space Grey");
        Laptop laptop7 = new Laptop("Razer Blade Stealth 13", "Intel Core i7-1165G7", 512, "NVIDIA GTX 1650 Ti",
                16, "Windows 10", "Mercury White");
        Laptop laptop8 = new Laptop("Apple MacBook Air M2", "Apple Silicon M2", 512, "Apple M2 GPU", 16,
                "macOS Monterey", "Silver");
        Laptop laptop9 = new Laptop("LG Gram 17", "Intel Core i7-1165G7", 1024, "Intel Iris Xe Graphics",
                16, "Windows 10", "Dark Silver");
        Laptop laptop10 = new Laptop("Microsoft Surface Laptop 4", "AMD Ryzen 7 4980U", 256,
                "AMD Radeon Graphics", 8, "Windows 11", "Platinum");

        Set<Laptop> laptops = new HashSet<>(Arrays.asList(laptop1, laptop2, laptop3, laptop4, laptop5, laptop6, laptop7,
                laptop8, laptop9, laptop10));

        for (Laptop laptop : laptops) {
            System.out.println(laptop);
        }

        userRequest(laptops);
    }

    public static void userRequest(Set<Laptop> laptops) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - Наименование");
        System.out.println("2 - Процессор");
        System.out.println("3 - Объем памяти SSD/HDD");
        System.out.println("4 - Видеокарта");
        System.out.println("5 - Объем оперативной памяти");
        System.out.println("6 - Операционная система");
        System.out.println("7 - Цвет");

        // *String name, String cpu, int memory, String gpu, int ram, String os, String
        // color

        int criteria = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        Map<String, Object> filterCriteria = new HashMap<>();

        switch (criteria) {
            case 1:
                System.out.println("Введите желаемую модель ноутбука:");
                Set<String> allModels = getAllModels(laptops);
                System.out.println("Все модели: " + allModels);
                String name = scanner.nextLine();
                filterCriteria.put("name", name);
                break;
            case 2:
                System.out.println("Введите желаемый процессор:");
                Set<String> allCpu = getAllCpu(laptops);
                System.out.println("Все модели: " + allCpu);
                String cpu = scanner.nextLine();
                filterCriteria.put("cpu", cpu);
                break;
            case 3:
                System.out.println("Введите минимальный объем памяти SSD/HDD:");
                Set<Integer> allMemory = getAllMemory(laptops);
                System.out.println("Все модели: " + allMemory);
                int minMemory = scanner.nextInt();
                filterCriteria.put("memory", minMemory);
                break;
            case 4:
                System.out.println("Введите желаемую видеокарту:");
                Set<String> allGpu = getAllGpu(laptops);
                System.out.println("Все модели: " + allGpu);
                String gpu = scanner.nextLine();
                filterCriteria.put("gpu", gpu);
                break;
            case 5:
                System.out.println("Введите минимальный объем оперативной памяти:");
                Set<Integer> allRam = getAllRam(laptops);
                System.out.println("Все модели: " + allRam);
                int minRam = scanner.nextInt();
                filterCriteria.put("ram", minRam);
                break;
            case 6:
                System.out.println("Введите желаемую операционную систему:");
                Set<String> allOs = getAllOs(laptops);
                System.out.println("Все модели: " + allOs);
                String os = scanner.nextLine();
                filterCriteria.put("os", os);
                break;
            case 7:
                System.out.println("Введите желаемый цвет:");
                Set<String> allColor = getAllColor(laptops);
                System.out.println("Все модели: " + allColor);
                String color = scanner.nextLine();
                filterCriteria.put("color", color);
                break;
            default:
                System.out.println("Некорректный выбор критерия.");
                return;
        }
        // *String name, String cpu, int memory, String gpu, int ram, String os, String

        Set<Laptop> filteredLaptops = filterLaptops(laptops, filterCriteria);

        System.out.println("Отфильтрованные ноутбуки:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }

    public static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, Object> filterCriteria) {
        Set<Laptop> filteredLaptops = new HashSet<>(laptops);

        for (Laptop laptop : laptops) {
            for (Map.Entry<String, Object> entry : filterCriteria.entrySet()) {
                String criteria = entry.getKey();
                Object value = entry.getValue();

                switch (criteria) {
                    case "name":
                        if (!laptop.getName().equals(value)) {
                            filteredLaptops.remove(laptop);
                        }
                        break;
                    case "cpu":
                        if (!laptop.getCpu().equals(value)) {
                            filteredLaptops.remove(laptop);
                        }
                        break;
                    case "memory":
                        if (laptop.getMemory() < (int) value) {
                            filteredLaptops.remove(laptop);
                        }
                        break;
                    case "gpu":
                        if (!laptop.getGpu().equals(value)) {
                            filteredLaptops.remove(laptop);
                        }
                        break;
                    case "ram":
                        if (laptop.getRam() < (int) value) {
                            filteredLaptops.remove(laptop);
                        }
                        break;
                    case "os":
                        if (!laptop.getOs().equals(value)) {
                            filteredLaptops.remove(laptop);
                        }
                        break;
                    case "color":
                        if (!laptop.getColor().equals(value)) {
                            filteredLaptops.remove(laptop);
                        }
                        break;
                }
                // *String name, String cpu, int memory, String gpu, int ram, String os, String
            }
        }

        return filteredLaptops;
    }

    // * Получаем весь модельный список
    public static Set<String> getAllModels(Set<Laptop> laptops) {
        return laptops.stream()
                .map(Laptop::getName)
                .distinct()
                .collect(Collectors.toSet());
    }

    // * Получаем весь список процессоров
    public static Set<String> getAllCpu(Set<Laptop> laptops) {
        return laptops.stream()
                .map(Laptop::getCpu)
                .distinct()
                .collect(Collectors.toSet());
    }

    // * Получаем весь список HDD/SSD по объему
    public static Set<Integer> getAllMemory(Set<Laptop> laptops) {
        return laptops.stream()
                .map(Laptop::getMemory)
                .distinct()
                .sorted()
                .collect(Collectors.toSet());
    }

    // * Получаем весь список видеокарт
    public static Set<String> getAllGpu(Set<Laptop> laptops) {
        return laptops.stream()
                .map(Laptop::getGpu)
                .distinct()
                .collect(Collectors.toSet());
    }

    // * Получаем весь список RAM
    public static Set<Integer> getAllRam(Set<Laptop> laptops) {
        return laptops.stream()
                .map(Laptop::getRam)
                .distinct()
                .sorted()
                .collect(Collectors.toSet());
    }

    // * Получаем весь список ос
    public static Set<String> getAllOs(Set<Laptop> laptops) {
        return laptops.stream()
                .map(Laptop::getOs)
                .distinct()
                .collect(Collectors.toSet());
    }

    // * Получаем весь список цветов
    public static Set<String> getAllColor(Set<Laptop> laptops) {
        return laptops.stream()
                .map(Laptop::getColor)
                .distinct()
                .collect(Collectors.toSet());
    }

    //* String name, String cpu, int memory, String gpu, int ram, String os, String
}
