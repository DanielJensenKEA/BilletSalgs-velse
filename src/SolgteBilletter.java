import java.util.ArrayList;
import java.util.Collections;

public class SolgteBilletter {
    private ArrayList<BilletDør> BDListe;
    private ArrayList<BilletForsalg10Dag> BForsalgListe;
    private ArrayList<BilletForsalgStudRabat> BForsalgStudRabat;
    private ArrayList<Integer> studentIDRepo;

    public SolgteBilletter() {
        BDListe = new ArrayList<>();
        BForsalgListe = new ArrayList<>();
        BForsalgStudRabat = new ArrayList<>();
        studentIDRepo = new ArrayList<>();
    }

    public void addTicketDoor(BilletDør b) {
        BDListe.add(b);
    }
    public void addTicketForsalg(BilletForsalg10Dag b) {
        BForsalgListe.add(b);
    }
    public void addTicketForsalgStudRabat(BilletForsalgStudRabat b) {
        BForsalgStudRabat.add(b);
    }
    public int countNumOfTicketsDoor() {
        return BDListe.size();
    }
    public int countNumOfTicketsForsalg() {
        return BForsalgListe.size();
    }
    public int countNumOfTicketsStudRabat() {
        return BForsalgStudRabat.size();
    }
    public String collectAndPrintAllStudentIDs() {
        String result = "";
        int count = 1;

        for (BilletForsalgStudRabat b : BForsalgStudRabat) {
            studentIDRepo.add(b.getStudieKortID());
        }
        Collections.sort(studentIDRepo, new StudentIDComparator2());
        for(Integer b : studentIDRepo) {
            result += count+". "+b+"\n";
            count++;
        }
        return result;
    }
    public String collectAndPrintAllStudentIDS2() {
        String result ="";
        int count = 1;

        BForsalgStudRabat.sort(new studentIDComparator());
        for(BilletForsalgStudRabat b : BForsalgStudRabat) {
            result += "\n"+count+". Student ID: "+ b.getStudieKortID()+"\t\tName: "+b.getName();
            count++;
        }
        return result;
    }

    @Override
    public String toString() {
        int count = 1;
        String result = "";
        result += "Tickets sold by door: \n";
        for(BilletDør b : BDListe) {
            result += "\n"+b.toString();
        }
        result += "Tickets sold, preshow, 10 days before event: \n";
        for(BilletForsalg10Dag b : BForsalgListe) {
            result += "\n"+b.toString();
        }
        result += "Tickets sold, preshow, student discount: \n";
        for(BilletForsalgStudRabat b :BForsalgStudRabat) {
            result += "\n"+b.toString();
        }

        return result;
    }


}
