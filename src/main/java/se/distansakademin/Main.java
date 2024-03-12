package se.distansakademin;

import se.distansakademin.models.Furniture;
import se.distansakademin.repositories.FurnitureRepository;

import java.sql.SQLException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws SQLException {
        var repo = new FurnitureRepository();
        var randomFurniture = getRandomFurniture();
        repo.insert(randomFurniture);

        var furnitures = repo.getAll();

        for (var furniture : furnitures){
            System.out.println(furniture);
        }

    }

    private static Furniture getRandomFurniture() {
        // Array med potentiella ord för name
        String[] words = {"Viggen", "Draken", "Gripen", "Tunnan", "Lansen"};

        // Skapar en instans av Random-klassen
        Random random = new Random();

        // Slumpar en titel genom att välja två ord från arrayen
        String name = words[random.nextInt(words.length)] + " " + words[random.nextInt(words.length)];

        String[] types = {"Soffa", "Stol", "Säng", "Bord"};
        String type = types[random.nextInt(words.length)];

        return new Furniture(name, type);
    }

}