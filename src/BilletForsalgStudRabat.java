public class BilletForsalgStudRabat implements Billet{
    private String name;
    private double pris;
    private int dageFørKøb;
    private int billetID;
    int studieKortID;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public int getDageFørKøb() {
        return dageFørKøb;
    }

    public void setDageFørKøb(int dageFørKøb) {
        this.dageFørKøb = dageFørKøb;
    }

    public int getStudieKortID() {
        return studieKortID;
    }

    public void setStudieKortID(int studieKortID) {
        this.studieKortID = studieKortID;
    }

    public BilletForsalgStudRabat(String name, double pris, int dageTilKøb,int billetID, int studieKortID) {
        this.name = name;
        this.pris = pris;
        this.dageFørKøb = dageTilKøb;
        this.billetID = billetID;
        this.studieKortID = studieKortID;
    }


    @Override
    public void BeregnBilletPris() {
        double discount = 0.85; //15% rabat
        int dageRabatIndtrædelse = 10;
        double regPris = 90;
        double discountedResult = regPris*discount;
        if(dageFørKøb > dageRabatIndtrædelse) {
            setPris(regPris);
        } else {
            setPris(discountedResult);
        }
    }

    @Override
    public String udSkrivBillet() {
        String result = "";
        result += "#############################################################################################";
        result += "\nReg. name: "+name+"\t\tPrice: "+pris+ "\t\tBought "+dageFørKøb+" before event start."+"\t\tTicket ID: "+billetID;
        result += "\nPlease bring your student membership card at the event to confirm enrolment.";
        result += "\n#############################################################################################";
        return result;
    }
}
