import java.util.ArrayList;
import java.util.Collections;

public class SolgteBilletter {

    private ArrayList<BilletForsalgStudRabat> studentIDRepo;
    private ArrayList<Billet> solgteBill;

    public SolgteBilletter() {

        studentIDRepo = new ArrayList<>();
        solgteBill = new ArrayList<>();
    }

    public void addTicketsToList(Billet b) {
        solgteBill.add(b);
    }


    public int countNumOfTicketsDoor() {
        int count = 0;
        for(Billet b : solgteBill) {
            if (b instanceof BilletDør) {
                count++;
            }
        }
        return count;
    }
    public int countNumOfTicketsForsalg() {
        int count = 0;
        for(Billet b : solgteBill) {
            if (b instanceof BilletForsalg10Dag) {
                count++;
            }
        }
        return count;
    }
    public int countNumOfTicketsStudRabat() {
        int count = 0;
        for(Billet b : solgteBill) {
            if (b instanceof BilletForsalgStudRabat) {
                count++;
            }
        }
        return count;
    }
    public String collectAndPrintAllStudentIDS2() {
        String result ="";
        int count = 1;

        for(Billet b : solgteBill) {
            if(b instanceof BilletForsalgStudRabat studRab) {
                studentIDRepo.add(studRab);
            }
        }
        studentIDRepo.sort(new StudentIDComparator2());
        for(BilletForsalgStudRabat b : studentIDRepo) {
            result += "\n"+count+". Student ID: "+b.getStudieKortID()+"\t\tName: "+b.getName();
            count++;
        }


        return result;
    }

    @Override
    public String toString() {
        int count = 1;
        String result = "";
        result += "Tickets sold by door: \n";

        for(Billet b : solgteBill) {
            if(b instanceof BilletDør billetDør) {
                billetDør.toString();
            }
        }
        result += "Tickets sold, preshow, 10 days before event: \n";

        for(Billet b : solgteBill) {
            if(b instanceof BilletForsalg10Dag forSalg) {
                result += forSalg.toString();
            }
        }
        result += "Tickets sold, preshow, student discount: \n";

        for(Billet b : solgteBill) {
            if (b instanceof BilletForsalgStudRabat studRabat) {
                result += "\n"+studRabat.toString();
            }
        }

        return result;
    }


}
