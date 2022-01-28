package entity;

public class employe {
    private int CIN;
    private String Nom;
    private String Prenom;

    private String Adresse;

    public employe(int CIN,String Nom,String Prenom,String Adresse) {
        this.CIN=CIN;
        this.Nom = Nom;
        this.Prenom=Prenom;
        this.Adresse=Adresse;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public int getCIN() {
        return CIN;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

}
