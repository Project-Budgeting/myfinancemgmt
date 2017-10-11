package com.example.user.financemgmt.TestStorageForDataObjects;

/**
 * Created by Palibin
 * класс, отражающий сводку по текущим средствам.
 */

public class Ballance {
    private static Ballance singleBallance;
    private long freeCash;               //свободные средства
    private long reservedCash;           //Зарезервированные средства
    private long targetsCost;            //стоимость целей

    public void setFreeCash(long freeCash) {
        this.freeCash = freeCash;
    }

    public void setReservedCash(int reservedCash) {
        this.reservedCash = reservedCash;
    }

    public void setTargetsCost(int targetsCost) {
        this.targetsCost = targetsCost;
    }

    public static Ballance getSingleBallance(){
        if (singleBallance==null) singleBallance = new Ballance();
        return singleBallance;

    }
    public long getFreeCash() {
        return freeCash;
    }

    public long getReservedCash() {
        return reservedCash;
    }

    public long getTargetsCost() {
        return targetsCost;
    }
}
