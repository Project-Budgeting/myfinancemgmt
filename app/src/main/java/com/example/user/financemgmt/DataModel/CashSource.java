package com.example.user.financemgmt.DataModel;

import com.example.user.financemgmt.DAO.DriverDao;

/*
 * Created by PalibinFamily on 01.10.2017.
 */

public class CashSource implements Decreasable {
    private String name;
    private long availableCash;
    private long id;
    private static long count=100;
    /*
    В дальнейшем при написании объектов целей, при достижении цели, она автоматически становится
    объектом типа "Источник средств" с единственно возможной тратой, указанной в данном поле
    TODO Внести данное поле в перегруженный конструктор.
    private Usage usage;
    */

    //Данный конструктор создан для подмены ссылки в геттере.
    public CashSource(CashSource cs) {
        this.name = cs.getName();
        this.availableCash = cs.getCashAmount();
        this.id = cs.getId();
    }



    public CashSource(String name, long availableCash) {
        this.name = name;
        this.availableCash = availableCash;
        this.id = generateid();
        count++;
        DriverDao.increaseFreeCashOfBallance(availableCash); // Отражаем доход в объекте Балланса
        //Делаем запись в журнал
        DriverDao.insertRecordInJournal(new JournalRecord(this.availableCash, name, null, id));
        //Отсылаем изменения в хранилище
        DriverDao.addCashSource(this);

    }

    private long generateid(){
    return count+1;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailableCash(long availableCash) {
        this.availableCash = availableCash;
    }

    public void increaseAvailableCash(long amount) {
        this.availableCash += amount;
        DriverDao.increaseFreeCashOfBallance(amount);
        DriverDao.insertRecordInJournal(new JournalRecord(amount, this.name, null, this.id));
        DriverDao.updateCashSource(this);
    }


    @Override
    public String getName() {

        return name;
    }

    @Override
    public long getCashAmount() {
        return availableCash;
    }

    @Override
    public void decrease(long amount) {
        this.availableCash= this.availableCash - amount;
        DriverDao.decreaseFreeCashOfBallance(amount);
        DriverDao.updateCashSource(this);
        //Запись в журнал не создаем, так как запись создаст объект Expense
    }
}
