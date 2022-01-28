package entity;

public class reclamation {
    private int CIN;
    private String Nom;
    private String Adresse;
    private  String choixrec;

    public reclamation(int CIN,String Nom,String Adresse,String choixrec) {
        this.CIN = CIN;
        this.Nom=Nom;
        this.Adresse=Adresse;
    this.choixrec=choixrec;}

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public int getCIN() {
        return CIN;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public String getChoixrec() {
        return choixrec;
    }

    public void setChoixrec(String choixrec) {
        this.choixrec = choixrec;
    }
}
