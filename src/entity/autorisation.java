package entity;

public class autorisation {
 private int CIN;
  private String Nom;
   private String Prenom;
    private String Dateautorisation;
     private String autorisation;


    public autorisation(int CIN,String Nom,String Prenom,String Dateautorisation,String autorisation) {
        this.CIN = CIN;
        this.Nom=Nom;
        this.Prenom=Prenom;
        this.Dateautorisation=Dateautorisation;
        this.autorisation=autorisation;
    }

    public String getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(String autorisation) {
        this.autorisation = autorisation;
    }

    public String getDateautorisation() {
        return Dateautorisation;
    }

    public void setDateautorisation(String dateautorisation) {
        Dateautorisation = dateautorisation;
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

    public int getCIN() {
        return CIN;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }
}
