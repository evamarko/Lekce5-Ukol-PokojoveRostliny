import java.time.LocalDate;
import java.time.format.DateTimeParseException;

//2. Upravit třídu Plant, tak aby bylo možné seznam rostlin řadit podle názvu rostliny pomocí Collections.sort
public class Plant implements Comparable<Plant> {
    //1. atributy třídy Plant
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    //2. tři konstruktory
    //Konstruktor pro nastavení všech atributů
    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.setWatering(watering);
        this.setFrequencyOfWatering(frequencyOfWatering);
    }

    //Druhý konstruktor, který nastaví poznámky jako prázný řetězec a datum poslední zálivky na dnešek
    public Plant(String name, LocalDate planted, int frequencyOfWatering) throws PlantException {
        this(name, "", planted, LocalDate.now(), frequencyOfWatering );
    }

    //Třetí konstruktor, totéž co druhý a navíc frekvenci zálivky na 7 dnů a datum zasazení na dnešek
    public Plant (String name) throws PlantException {
        this(name, LocalDate.now(), 7);
    }

    //3. Přístupové metody pro všechny atributy
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    //7. Ošetření zadávání data poslední zálivky, nesmí být starší než datum zasazení rostliny
    public void setWatering(LocalDate watering) throws PlantException {
        if (watering.isBefore(planted)) {
            throw new PlantException("Květina nemůže být zalita před datem zasazení (zadané datum zálivky: " + watering + ", zadané datum zasazení: " + planted + ")");
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    //6. Ošetření zadávání frekvence zálivky, parametr nemůže být 0, nebo záporné číslo
    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Frekvence zálivky musí být kladné číslo (zadáno: " + frequencyOfWatering + ")");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    //4. Metoda, která vrátí název rostliny, datum poslední zálivky a datum doporučené další zálivky
    public String getWateringInfo() {
        return "Květina: " + name + ", poslední zálivka: " + watering + ", další zálivka: " + watering.plusDays(frequencyOfWatering);
    }

    public static Plant parsePlant(String data) throws PlantException {
        String [] items = new String[0];
        try {
            items = data.split("\t");
            String name = items[0];
            String notes = items[1];
            int frequencyOfWatering = Integer.parseInt(items[2]);
            LocalDate watering = LocalDate.parse(items[3]);
            LocalDate planted = LocalDate.parse(items[4]);
            return new Plant(name, notes, planted, watering, frequencyOfWatering);
        } catch (DateTimeParseException e) {
            //17. Vyzkoušej, že se aplikace bude chovat správně při načtení vadného souboru kvetiny-spatne-datum.txt.
            throw new PlantException("Špatně zadané datum: " + items[3] + " nebo " + items[4]);
        } catch (NumberFormatException e) {
            //18. Vyzkoušej, že se aplikace bude chovat správně při načtení vadného souboru kvetiny-spatne-frekvence.txt
            throw new PlantException("Špatně zadané číslo: " + items[2]);
        }
    }

    public String exportToString() {
        return name + "\t" + notes + "\t" + frequencyOfWatering + "\t" + watering + "\t" + planted;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", planted=" + planted +
                ", watering=" + watering +
                ", frequencyOfWatering=" + frequencyOfWatering +
                '}';
    }

    //2. Upravit třídu Plant, tak aby bylo možné seznam rostlin řadit podle názvu rostliny pomocí Collections.sort
    @Override
    public int compareTo(Plant plant) {
        return this.name.compareTo(plant.name);
    }

}
