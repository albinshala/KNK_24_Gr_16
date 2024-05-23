package models.dto;

import java.sql.Date;

public class Perdoruesi {
    private int id;
    private String emri;
    private String mbiemri;
    private String username;
    private String fjalekalimi_salted;
    private String salt;

    private char gjinia;

    private boolean admin;
    private Date ditelindja;

    public Perdoruesi() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDitelindja() {
        return ditelindja;
    }

    public void setDitelindja(Date ditelindja) {
        this.ditelindja = ditelindja;
    }

    public Perdoruesi(int id, String emri, String mbiemri, String username, String fjalekalimi_salted,
                      String salt, char gjinia, boolean admin, Date ditelindja) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.username = username;
        this.fjalekalimi_salted = fjalekalimi_salted;
        this.salt = salt;
        this.gjinia = gjinia;
        this.admin = admin;
        this.ditelindja = ditelindja;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFjalekalimi_salted() {
        return fjalekalimi_salted;
    }

    public void setFjalekalimi_salted(String fjalekalimi_salted) {
        this.fjalekalimi_salted = fjalekalimi_salted;
    }


    public char getGjinia() {
        return gjinia;
    }

    public void setGjinia(char gjinia) {
        this.gjinia = gjinia;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
