package com.application.testapp.entity;

public class Cake {

    private Integer cakeId;  //cake ID that will be in the database
    private String  cakeName; //Cake Name that will be in database

    private String  cakeFlavour;

    public Cake() {

    }
    public Cake(Integer cakeId, String cakeName, String cakeFlavour) {
        this.cakeId = cakeId;
        this.cakeName = cakeName;
        this.cakeFlavour = cakeFlavour;
    }


    public Integer getCakeId() {
        return cakeId;
    }

    public void setCakeId(Integer cakeId) {
        this.cakeId = cakeId;
    }

    public String getCakeName() {
        return cakeName;
    }

    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }

    public String getCakeFlavour() {
        return cakeFlavour;
    }

    public void setCakeFlavour(String cakeFlavour) {
        this.cakeFlavour = cakeFlavour;
    }

    @Override
    public String toString() {
        return "Cake{" +
                "cakeId=" + cakeId +
                ", cakeName='" + cakeName + '\'' +
                ", cakeFlavour='" + cakeFlavour + '\'' +
                '}';
    }
}
