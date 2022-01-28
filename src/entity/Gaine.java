package entity;

public class Gaine {
  private String Nom;
  private String DateA;
  private Float Prix;

    public Gaine(String Nom,String DateA,Float Prix) {
        this.Nom = Nom;
        this.DateA=DateA;
        this.Prix=Prix;
    }

    public Float getPrix() {
        return Prix;
    }

    public void setPrix(Float prix) {
        Prix = prix;
    }

    public String getDateA() {
        return DateA;
    }

    public void setDateA(String dateA) {
        DateA = dateA;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }
}
