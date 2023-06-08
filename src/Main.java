import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ListOfPlants plants = new ListOfPlants();

        //12. Načtení seznamu květin ze souboru
        try {
            ListOfPlants.importFromFile(Settings.fileName(), Settings.delimiter());
        } catch (PlantException e) {
            System.out.println("Nepodařilo se načíst data ze souboru! " + e.getLocalizedMessage());
        }

        //13. Vypsat informace o zálivce pro všechny květiny
        for (Plant plant : plants.getPlants()) {
            System.out.println(plant.getWateringInfo());
            }

        //14. Přidání nové květiny do seznamu
        try {
            plants.addPlant(new Plant("Bazalka v kuchyni", "", LocalDate.of(2021, 9, 4), LocalDate.of(2021, 9, 4), 3));
        } catch (PlantException e) {
            System.err.println("Nastala chyba při vytváření nákupu: " + e.getLocalizedMessage());
        }

        //14. Odebrání jedné květiny ze seznamu
        plants.removePlant(plants.get(2));

        //15. Uložit aktualizovný seznam květin do souboru a ověřit, zda je obsah správný
        ListOfPlants.exportToFile(plants.getPlants(), Settings.fileName());

        //16. Opětovné načtení vygenerovaného souboru
        try {
            ListOfPlants.importFromFile(Settings.fileName(), Settings.delimiter());
        } catch (PlantException e) {
            System.out.println("Nepodařilo se načíst data ze souboru! " + e.getLocalizedMessage());
        }

    }
}
