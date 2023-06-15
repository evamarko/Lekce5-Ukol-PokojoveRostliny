import java.util.Comparator;

//3. Upravit kód tak, aby rostliny bylo možno řadit i podle data poslední zálivky.
public class LastWateringDateComparator implements Comparator<Plant> {
    @Override
    public int compare(Plant p1, Plant p2) {
        return p1.getWatering().compareTo(p2.getWatering());
    }
}

