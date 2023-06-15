//1. Využít kód z lekce 5, úkol Pokojové rostliny(1)

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ListOfPlants plants = new ListOfPlants();

        //12. Načtení seznamu květin ze souboru, v případě chybného vstupu vyhodit výjimku
        try {
            ListOfPlants.importFromFile(Settings.fileName());
        } catch (PlantException e) {
            System.err.println("Nepodařilo se načíst data ze souboru! " + e.getLocalizedMessage());
        }

        //13. Vypsat informace o zálivce pro všechny květiny
        for (Plant plant : ListOfPlants.plants) {
            System.out.println(plant.getWateringInfo());
            }

        //14. Přidání nových dvou květin do seznamu
       /* try {
            plants.addPlant(new Plant("Bazalka v kuchyni", "", LocalDate.of(2021, 9, 4), LocalDate.of(2021, 9, 4), 3));
        } catch (PlantException e) {
            System.err.println("Nastala chyba při přidávání rostliny: " + e.getLocalizedMessage());
        }*/

     /*   try {
            plants.addPlant(new Plant("Bazalka v koupelně", "", LocalDate.of(2021, 9, 4), LocalDate.of(2021, 9, 4), 3));
        } catch (PlantException e) {
            System.err.println("Nastala chyba při přidávání rostliny: " + e.getLocalizedMessage());
        }
*/
        //14. Odebrání květin ze seznamu
        //plants.removePlant(plants.get(2));

        //15. Uložit aktualizovný seznam květin do souboru a ověřit, zda je obsah správný
        //ListOfPlants.exportToFile(plants.getPlants(), Settings.fileName());

        //16. Opětovné načtení vygenerovaného souboru
       /* try {
            ListOfPlants.importFromFile(Settings.fileName());
        } catch (PlantException e) {
            System.err.println("Nepodařilo se načíst data ze souboru! " + e.getLocalizedMessage());
        }*/

        //3. Seřadit načtené rostliny podle názvu
        //4. Ponechat možnost řadit podle názvu stále funkční.
        Collections.sort(ListOfPlants.plants);
        System.out.println("=====================");
        ListOfPlants.plants.forEach( n -> { System.out.println(n.getName()); });

        //5.Provést druhý výpis, který vypíše rostliny seřazené podle data poslední zálivky.
        Collections.sort(ListOfPlants.plants, new LastWateringDateComparator());
        System.out.println("======================");
        ListOfPlants.plants.forEach(c -> System.out.println(c.getName() + ": " + c.getWatering()));

        //6. Najít a vypsat dny, kdy jsi zasadil(a) alespoň jednu rostlinu.
        //Pokud některý den žádná rostlina zasazena nebyla, dané datum nevypisuj. Pokud bylo v jeden den zasazeno několik rostlin, vypiš dané datum pouze jednou.
        // Set<String> jmena = new HashSet<>();
        Set<Plant> plantsPlantedDates = new HashSet<>();
        plantsPlantedDates.addAll(ListOfPlants.plants);
        System.out.println("======================");
        for (Plant plant : plantsPlantedDates) {
            System.out.println(plant.getPlanted());
        }

        //Výzva: jen poslední měsíc
        System.out.println("======================");
        for (Plant plant : plantsPlantedDates) {
            LocalDate datePlanted = plant.getPlanted();
            LocalDate dateToday = LocalDate.now();
            long timeBetweenDates = ChronoUnit.MONTHS.between(datePlanted, dateToday);
            if(timeBetweenDates < 1) {
                System.out.println(datePlanted);
            }
        }





    }

}
