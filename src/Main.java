import java.time.LocalDate;

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
        for (Plant plant : plants.getPlants()) {
            System.out.println(plant.getWateringInfo());
            }

        //14. Přidání nových dvou květin do seznamu
        try {
            plants.addPlant(new Plant("Bazalka v kuchyni", "", LocalDate.of(2021, 9, 4), LocalDate.of(2021, 9, 4), 3));
        } catch (PlantException e) {
            System.err.println("Nastala chyba při přidávání rostliny: " + e.getLocalizedMessage());
        }

        try {
            plants.addPlant(new Plant("Bazalka v koupelně", "", LocalDate.of(2021, 9, 4), LocalDate.of(2021, 9, 4), 3));
        } catch (PlantException e) {
            System.err.println("Nastala chyba při přidávání rostliny: " + e.getLocalizedMessage());
        }

        //14. Odebrání květin ze seznamu
        plants.removePlant(plants.get(2));
        plants.removePlant(plants.get(2));

        //15. Uložit aktualizovný seznam květin do souboru a ověřit, zda je obsah správný
        //ListOfPlants.exportToFile(plants.getPlants(), Settings.fileName());

        //16. Opětovné načtení vygenerovaného souboru
        try {
            ListOfPlants.importFromFile(Settings.fileName());
        } catch (PlantException e) {
            System.err.println("Nepodařilo se načíst data ze souboru! " + e.getLocalizedMessage());
        }
    }
}
