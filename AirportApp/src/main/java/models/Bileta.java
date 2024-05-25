package models;

public class Bileta {
    private int id;

    private int çmimi;

    public  Bileta(){

    }

    public Bileta(int id, int çmimi) {
        this.id = id;
        this.çmimi = çmimi;
    }

    public int getÇmimi() {
        return çmimi;
    }

    public void setÇmimi(int çmimi) {
        this.çmimi = çmimi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
}


}