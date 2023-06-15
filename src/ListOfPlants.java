import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//8. Třída pro ukládání seznamu květin
public class ListOfPlants {
    private static List<Plant> plants = new ArrayList<>();

    //9. Metody pro přídání květiny, získání květiny dle indexu a odebrání květiny ze seznamu
    //Přidání květiny
    public void addPlant(Plant newPlant) {
        plants.add(newPlant);
    }

    //Získání květiny dle indexu
    public Plant get(int index) {
        return plants.get(index);
    }

    //Odebrání květiny
    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }

    //10. Metoda pro načtení květin ze souboru, v případě chybného vstupu vyhoďit výjimku
    public static void importFromFile(String fileName) throws PlantException {
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                plants.add(Plant.parsePlant(line));
            }
        } catch (FileNotFoundException e) {
            throw new PlantException("Soubor " + fileName + " nalezen!");
        }
    }

    //11. Metoda pro uložení akutalizovaného seznamu do souboru
    public static void exportToFile(List<Plant> dataToWrite, String fileName) {
        try (PrintWriter outputWriter = new PrintWriter(new FileWriter(fileName))) {
            for (Plant plant : dataToWrite) {
                outputWriter.println(plant.exportToString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



