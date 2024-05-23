package models.dto;

public class Airoplani {
    private int id;
    String kompania;
    private int kapaciteti;
    private String tipi;

    public Airoplani(){

    }

    public Airoplani(int id,String kompania, int kapaciteti, String tipi) {
        this.id = id;
        this.kompania = kompania;
        this.kapaciteti = kapaciteti;
        this.tipi = tipi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKompania() {
        return kompania;
    }

    public void setKompania(String kompania) {
        this.kompania = kompania;
    }

    public int getKapaciteti() {
        return kapaciteti;
    }

    public void setKapaciteti(int kapaciteti) {
        this.kapaciteti = kapaciteti;
    }

    public String getTipi() {
        return tipi;
    }

    public void setTipi(String tipi) {
        this.tipi=tipi;
}
}