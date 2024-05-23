package models.dto;

public class Aeroporti {
    private int id;
    private  String emri;
    private String qyteti;

    public Aeroporti(){

    }
    public Aeroporti(int id,String emri,String qyteti){
        this.id=id;
        this.emri=emri;
        this.qyteti=qyteti;
    }
    public int getId(){
        return id;
    }
    public String getEmri(){
        return emri;
    }
    public String getQyteti(){
        return qyteti;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setEmri(String emri){
        this.emri=emri;
    }
    public void setQyteti(String qyteti){
        this.qyteti=qyteti;
}
}