package com.example.shivu.chonmonguitinnhan.model.entity;

import android.content.Context;

import com.example.shivu.chonmonguitinnhan.R;

import java.util.ArrayList;

public class TacoOrder {
    private String size;
    private String tortilla;
    private ArrayList<String> fillings;
    private ArrayList<String> beverage;

    public TacoOrder(){
        size = "";
        tortilla = "";
        fillings = new ArrayList<>();
        beverage = new ArrayList<>();
    }

    public TacoOrder(String size, String tortilla, ArrayList<String> fillings, ArrayList<String> beverage) {
        this.size = size;
        this.tortilla = tortilla;
        this.fillings = fillings;
        this.beverage = beverage;
    }

    public String toString(Context context) {
        StringBuilder strResult=new StringBuilder();
        strResult.append(context.getString(R.string.size) + ": " + this.size+"\n");
        strResult.append(context.getString(R.string.tortilla) + ": " + this.tortilla+"\n");
        strResult.append(context.getString(R.string.fillings) + ":\n");
        int lenFillings = this.fillings.size();
        for(int i=0;i<lenFillings;++i){
            strResult.append("\t+ "+this.fillings.get(i)+"\n");
        }
        strResult.append(context.getString(R.string.beverage)+":\n");
        int lenBeverage = this.beverage.size();
        for(int i=0;i<lenBeverage;++i){
            strResult.append("\t+ "+this.beverage.get(i)+"\n");
        }
        return strResult.toString();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTortilla() {
        return tortilla;
    }

    public void setTortilla(String tortilla) {
        this.tortilla = tortilla;
    }

    public ArrayList<String> getFillings() {
        return fillings;
    }

    public void setFillings(ArrayList<String> fillings) {
        this.fillings = fillings;
    }

    public ArrayList<String> getBeverage() {
        return beverage;
    }

    public void setBeverage(ArrayList<String> beverage) {
        this.beverage = beverage;
    }
}
