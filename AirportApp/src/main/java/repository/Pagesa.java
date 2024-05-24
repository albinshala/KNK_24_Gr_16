package models.dto;

import java.sql.Date;

public class Pagesa {
    private int id;
    private String menyraPageses;
    private String emriKartes;
    private String numriKartes;
    private Date dataSkadimit;
    private String kodiCvv;
    private int biletadId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenyraPageses() {
        return menyraPageses;
    }

    public void setMenyraPageses(String menyraPageses) {
        this.menyraPageses = menyraPageses;
    }

    public String getEmriKartes() {
        return emriKartes;
    }

    public void setEmriKartes(String emriKartes) {
        this.emriKartes = emriKartes;
    }

    public String getNumriKartes() {
        return numriKartes;
    }

    public void setNumriKartes(String numriKartes) {
        this.numriKartes = numriKartes;
    }

    public Date getDataSkadimit() {
        return dataSkadimit;
    }

    public void setDataSkadimit(Date dataSkadimit) {
        this.dataSkadimit = dataSkadimit;
    }

    public String getKodiCvv() {
        return kodiCvv;
    }

    public void setKodiCvv(String kodiCvv) {
        this.kodiCvv = kodiCvv;
    }

    public Pagesa(int id, String menyraPageses, String emriKartes, String numriKartes, Date dataSkadimit, String kodiCvv, int biletadId) {
        this.id = id;
        this.menyraPageses = menyraPageses;
        this.emriKartes = emriKartes;
        this.numriKartes = numriKartes;
        this.dataSkadimit = dataSkadimit;
        this.kodiCvv = kodiCvv;
        this.biletadId = biletadId;
    }

    public int getBiletadId() {
        return biletadId;
    }

    public void setBiletadId(int biletadId) {
        this.biletadId = biletadId;
    }
}