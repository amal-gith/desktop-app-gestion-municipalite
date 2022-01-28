package entity;

public class profit {
    private int id;
    private String Nom;
    private String DateP;
    private int Prix;

    public int getId() {
        return id;
    }

    public void setId(int ID) {
        this.id = ID;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getDateP() {
        return DateP;
    }

    public void setDate(String DateP) {
        this.DateP = DateP;
    }

    public int getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public profit(int ID, String Nom, String DateP, int Prix) {
        this.id = ID;
        this.Nom = Nom;
        this.DateP = DateP;
        this.Prix = Prix;
    }
}
