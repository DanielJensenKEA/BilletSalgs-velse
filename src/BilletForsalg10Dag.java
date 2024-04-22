public class BilletForsalg10Dag implements Billet{
    private String name;
    private double pris;
    double regPris = 120;
    private int dageFørKøb;
    private int billetID;

    public BilletForsalg10Dag(String name, double pris, int dageFørKøb, int billetID) {
        this.name = name;
        this.pris = pris;
        this.dageFørKøb = dageFørKøb;
        this.billetID = billetID;
    }


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

    @Override
    public void BeregnBilletPris() {
        double discount = 0.85; //15% rabat
        int rabatIndtrædelseDage = 10;
        double discountedResult = regPris*discount;
        if(dageFørKøb <= rabatIndtrædelseDage) {
            setPris(regPris);
        } else {
            setPris(discountedResult);
        }
    }

    @Override
    public String udSkrivBillet() {
        String result = "";
        result+="#############################################################################################\n";
        result += "Reg. name: "+name+"\t\tPrice: "+pris+"\t\tTicket ID: "+billetID+ "\t\tBought "+dageFørKøb+" days before event start."+"\t\tTicket ID: "+billetID;
        if(getPris() != regPris ) {
            result +="\n15% discount(normal price: "+regPris+",-"+")";
        }
        result +="\n#############################################################################################";

        return result;
    }
}
