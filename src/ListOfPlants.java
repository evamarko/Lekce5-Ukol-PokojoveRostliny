import java.io.*;
import java.util.*;

//8. Třída pro ukládání seznamu květin
public class ListOfPlants {
    List<Plant> plants = new ArrayList<>();


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
    public void importFromFile(String fileName) throws PlantException {
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
    //je dobré použít BufferedWriter, zefektivňuje zápis - píše se po větších blocích,
    //pokud ovšem nastane chyba a aplikace spadne, tak nemusí být ještě vše zapsané na disku
    //použít novější zápis try with resources, který soubor po načtení dat i uzavírá
    public void exportToFile(String fileName) throws PlantException {
        try (PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Plant plant : plants) {
                outputWriter.println(plant.exportToString());
            }
        } catch (IOException e) {
            throw new PlantException("Nepodařilo se nahrát data do souboru: " + fileName);
        }
    }

    //3. Seřadit načtené rostliny podle názvu
    public void sortPlants() {
        Collections.sort(plants);
    }

    //5.Provést druhý výpis, který vypíše rostliny seřazené podle data poslední zálivky
    public void sortPlantsByComparator() {
        Collections.sort(plants, new LastWateringDateComparator());
    }

}








