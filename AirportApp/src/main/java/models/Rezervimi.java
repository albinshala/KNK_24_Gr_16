package models;

public class Rezervimi {
    private int id;
    private int pasagjeri_id;
    private int fluturimi_id;
    private int numri_uleses;
    private String kategoria;
    private int bileta_id;

    private static Perdoruesi perdoruesi;
    public static Perdoruesi getPerdoruesi() {
        return perdoruesi;
    }


    public Rezervimi(){

    }

    public Rezervimi(int id, int pasagjeri_id, int fluturimi_id, int numri_uleses, String kategoria, int biletaId) {
        this.id = id;
        this.pasagjeri_id = pasagjeri_id;
        this.fluturimi_id = fluturimi_id;
        this.numri_uleses = numri_uleses;
        this.kategoria = kategoria;
        bileta_id = biletaId;
    }

    public static void setPerdoruesi(Perdoruesi perdoruesii) {
        perdoruesi = perdoruesii;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPasagjeri_id() {
        return pasagjeri_id;
    }

    public void setPasagjeri_id(int pasagjeri_id) {
        this.pasagjeri_id = pasagjeri_id;
    }

    public int getFluturimi_id() {
        return fluturimi_id;
    }

    public void setFluturimi_id(int fluturimi_id) {
        this.fluturimi_id = fluturimi_id;
    }

    public int getNumri_uleses() {
        return numri_uleses;
    }

    public void setNumri_uleses(int numri_uleses) {
        this.numri_uleses = numri_uleses;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public int getBileta_id() {
        return bileta_id;
    }

    public void setBileta_id(int bileta_id) {
        this.bileta_id = bileta_id;
    }
}

