import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
    public static void importFromFile(String fileName, String delimiter) throws PlantException {
        String[] items = new String[0];
        String line;
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println(line);
                items = line.split(delimiter);
                String name = items[0];
                String notes = items[1];
                int frequencyOfWatering = Integer.parseInt(items[2]);
                LocalDate watering = LocalDate.parse(items[3]);
                LocalDate planted = LocalDate.parse(items[4]);
                Plant newPlant = new Plant(name, notes, planted, watering, frequencyOfWatering);
                plants.add(newPlant);
            }
        } catch (FileNotFoundException e) {
            throw new PlantException("Soubor " + fileName + " nebyl nalezen!" + e.getLocalizedMessage());
        } catch (DateTimeParseException e) {
            //17. Vyzkoušej, že se aplikace bude chovat správně při načtení vadného souboru kvetiny-spatne-datum.txt.
            throw new PlantException("Špatně zadané datum: " + items[3] + " nebo " + items[4]);
        } catch (NumberFormatException e) {
            //18. Vyzkoušej, že se aplikace bude chovat správně při načtení vadného souboru kvetiny-spatne-frekvence.txt
            throw new PlantException("Špatně zadané číslo: " + items[2]);
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



