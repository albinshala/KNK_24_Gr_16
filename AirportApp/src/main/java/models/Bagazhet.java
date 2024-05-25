package models;

public class Bagazhet {
    private int id;
    private  int pasagjeri_id;
    private int numri_bagazhit;
    private double pesha_bagazhit;

    public Bagazhet(){

    }
    public Bagazhet(int id,int pasagjeri_id,int numri_bagazhit, double pesha_bagazhit){
        this.id=id;
        this.pasagjeri_id=pasagjeri_id;
        this.numri_bagazhit=numri_bagazhit;
        this.pesha_bagazhit=pesha_bagazhit;
    }
    public int getId(){
        return id;
    }
    public int getPasagjeri_Id(){
        return pasagjeri_id;
    }
    public int getNumri_bagazhit() {
        return numri_bagazhit;
    }
    public double getPesha_bagazhit(){
        return pesha_bagazhit;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setPasagjeri_Id(int pasagjeri_id){
        this.pasagjeri_id=pasagjeri_id;
    }
    public void setNumri_bagazhit(int numri_bagazhit){
        this.numri_bagazhit=numri_bagazhit;
    }
    public void setPesha_bagazhit(double pesha_bagazhit){
        this.pesha_bagazhit=pesha_bagazhit;
}
}