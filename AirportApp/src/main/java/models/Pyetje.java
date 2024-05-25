package models;

public class Pyetje {
    private int id;
    private String pyetje;
    private int perdoruesiId;

    public Pyetje(int id, String pyetje, int perdoruesiId) {
        this.id = id;
        this.pyetje = pyetje;
        this.perdoruesiId = perdoruesiId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPyetje() {
        return pyetje;
    }

    public void setPyetje(String pyetje) {
        this.pyetje = pyetje;
    }

    public int getPerdoruesiId() {
        return perdoruesiId;
    }

    public void setPerdoruesiId(int perdoruesiId) {
        this.perdoruesiId = perdoruesiId;
    }
}

