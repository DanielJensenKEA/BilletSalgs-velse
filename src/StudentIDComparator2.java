import java.util.Comparator;

public class StudentIDComparator2 implements Comparator<Billet> {
        @Override
    public int compare(Billet o1, Billet o2) {
        return Integer.compare(((BilletForsalgStudRabat)o1).getStudieKortID(), ((BilletForsalgStudRabat)o2).getStudieKortID());
    }
}
