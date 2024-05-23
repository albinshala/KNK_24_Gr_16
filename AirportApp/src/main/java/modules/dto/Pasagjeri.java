package models.dto;

import java.sql.Date;

public class Pasagjeri extends Perdoruesi {
    private int id;



    public int getPerdoruesi_id() {
        return perdoruesi_id;
    }

    public void setPerdoruesi_id(int perdoruesi_id) {
        this.perdoruesi_id = perdoruesi_id;
    }

    private int perdoruesi_id;

    private String adresa;
    private String nacionaliteti;
    private String numri_telefonit;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getNumri_pasaportes() {
        return numri_pasaportes;
    }

    public void setNumri_pasaportes(String numri_pasaportes) {
        this.numri_pasaportes = numri_pasaportes;
    }

    private String numri_pasaportes;

    public Pasagjeri() {

    }

//    public Pasagjeri(int id, String emri, String mbiemri, String username, String fjalekalimi_salted, String salt,
//                     char gjinia, String nacionaliteti,
//                     String adresa, String numri_telefonit, boolean admin, Date ditelindja, int perdoruesiId) {
//        super(id, emri, mbiemri, username, fjalekalimi_salted, salt, gjinia, admin, ditelindja);
//        this.adresa = adresa;
//        this.nacionaliteti = nacionaliteti;
//        this.numri_telefonit = numri_telefonit;
//        perdoruesi_id = perdoruesiId;
//    }

    public Pasagjeri(int id, int perdoruesi_id, String adresa, String nacionaliteti, String numri_telefonit, String numri_pasaportes) {
        this.id = id;
        this.perdoruesi_id = perdoruesi_id;
        this.adresa = adresa;
        this.nacionaliteti = nacionaliteti;
        this.numri_telefonit = numri_telefonit;
        this.numri_pasaportes = numri_pasaportes;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNacionaliteti() {
        return nacionaliteti;
    }

    public void setNacionaliteti(String nacionaliteti) {
        this.nacionaliteti = nacionaliteti;
    }

    public String getNumri_telefonit() {
        return numri_telefonit;
    }

    public void setNumri_telefonit(String numri_telefonit) {
        this.numri_telefonit = numri_telefonit;
    }

    public String getAdresa() {
        return adresa;
    }
}

