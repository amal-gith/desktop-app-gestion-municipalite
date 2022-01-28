package entity;


public class matériaux {
    private int ID;
    private String Type;
    private String DateAchat;
    private int Nombre;

    public matériaux(int ID,String Type,String DateAchat,int Nombre) {
        this.ID = ID;
        this.Type=Type;
        this.DateAchat=DateAchat;
        this.Nombre=Nombre;
    }

    public int getNombre() {
        return Nombre;
    }

    public void setNombre(int nombre) {
        Nombre = nombre;
    }

    public String getDateAchat() {
        return DateAchat;
    }

    public void setDateAchat(String dateAchat) {
        DateAchat = dateAchat;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
