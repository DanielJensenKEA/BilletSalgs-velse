import java.util.Comparator;

public class studentIDComparator implements Comparator<BilletForsalgStudRabat> {
    @Override
    public int compare(BilletForsalgStudRabat o1, BilletForsalgStudRabat o2) {
        return Integer.compare(o1.getStudieKortID(),o2.getStudieKortID());
    }
}
