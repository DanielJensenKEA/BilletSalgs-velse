public class BilletDør implements Billet{
    private String name;
    private int pris;
    private int billetID;

    public int getBilletID() {
        return billetID;
    }

    public void setBilletID(int billetID) {
        this.billetID = billetID;
    }

    public BilletDør(String name, int pris, int billetID) {
        this.name = name;
        this.pris = pris;
        this.billetID = billetID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    @Override
    public void BeregnBilletPris() {
        int normalPrice = 150;
        setPris(normalPrice);
    }

    @Override
    public String udSkrivBillet() {
        String result = " ";
        result += "#############################################################################################";
        result += "\nReg. name: "+getName()+"\t\tPrice: "+getPris()+"\t\t"+"Ticket ID: "+getBilletID();
        result += "\n#############################################################################################";
        return result;
    }
}
