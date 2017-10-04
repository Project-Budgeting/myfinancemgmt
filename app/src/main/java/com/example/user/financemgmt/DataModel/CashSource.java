package com.example.user.financemgmt.DataModel;

import com.example.user.financemgmt.DAO.DriverDao;

/**
 * Created by PalibinFamily on 01.10.2017.
 */

public class CashSource implements Decreasable {
    private String name;
    private long availableCash;
    private int id;
    private static int count=0;
    /*
    В дальнейшем при написании объектов целей, при достижении цели, она автоматически становится
    объектом типа "Источник средств" с единственно возможной тратой, указанной в данном поле
    TODO Внести данное поле в перегруженный конструктор.
    private Usage usage;
    */

    public CashSource(String name, long availableCash) {
        this.name = name;
        this.availableCash = availableCash;
        DriverDao.increaseFreeCashOfBallance(availableCash); // Отражаем доход в объекте Балланса
        this.id = generateid();

        count++;
    }

    private int generateid(){
    return count+1;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void increaseAvailableCash(long amount) {
        this.availableCash += amount;
        DriverDao.increaseFreeCashOfBallance(amount);
    }

    public String getName() {

        return name;
    }

    public long getAvailableCash() {
        return availableCash;
    }

    @Override
    public void decrease(long amount) {
        this.availableCash= this.availableCash - amount;
    }
}
