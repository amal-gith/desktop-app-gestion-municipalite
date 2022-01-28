package entity;

public class projet {
    private  int ID;
    private String NomP;
    private int Budget;
    private String DateD;
    private String Description;

    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public String getNomP() {
        return NomP;
    }

    public void setNomP(String nomP) {
        NomP = nomP;
    }

    public int getBudget() {
        return Budget;
    }

    public void setBudget(int budget) {
        Budget = budget;
    }

    public String getDateD() {
        return DateD;
    }

    public void setDateD(String dateD) {
        DateD = dateD;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public projet(int ID, String nomP, int budget, String dateD, String description) {
        this.ID = ID;
        NomP = nomP;
        Budget = budget;
        DateD = dateD;
        Description = description;
    }
}
