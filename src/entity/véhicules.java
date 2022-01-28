package entity;

public class véhicules {
    private int Matricule;
    private String Nom;
    private String Type;
    private String DateAchat;
    private int Nombre;


    public véhicules(int Matricule ,String Nom, String Type, String DateAchat,int Nombre) {
        this.Matricule=Matricule;
        this.Nom = Nom;
        this.Type=Type;
        this.DateAchat=DateAchat;
        this.Nombre=Nombre;
    }

    public int getNombre() { return Nombre; }
    public void setNombre(int nombre) { Nombre = nombre; }

    public String  getDateAchat() {
        return DateAchat;
    }

    public void setDateAchat(String  dateAchat) {
        DateAchat = dateAchat;
    }

    public int getMatricule() {
        return Matricule;
    }

    public void setMatricule(int matricule) {
        Matricule = matricule;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }




}
