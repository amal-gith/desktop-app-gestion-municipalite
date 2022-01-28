package sample1;

public class GaineA {
    private String Nom;
    private String DateG;
    private int Budget;
    private String Description;

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getDateG() {
        return DateG;
    }

    public void setDateG(String dateG) {
        DateG = dateG;
    }

    public int getBudget() {
        return Budget;
    }

    public void setBudget(int prix) {
        Budget = Budget;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public GaineA(String nom, String dateG, int budget, String description) {
        Nom = nom;
        DateG = dateG;
        Budget=budget;
        Description=description;
    }

}
