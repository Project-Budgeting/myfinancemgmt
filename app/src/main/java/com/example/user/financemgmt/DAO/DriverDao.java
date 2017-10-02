package com.example.user.financemgmt.DAO;

import com.example.user.financemgmt.DataModel.CashSource;
import com.example.user.financemgmt.TestStorageForDataObjects.Ballance;

import java.util.ArrayList;

/**
 * Created by Palibin
 * Данный класс отделяет реализацию доступа к данным от функционала.
 * Жду от вас запрос на необходимость
 * TODO: Дописать методы для Expense
 */

public class DriverDao {
    /*Заменив значение TEST_FROM_JAVA_ClASS на другое, мы переключим работу драйвера на
    совершенно другой источник, но методы продолжат выполняться без изменений.
    */
    private static FactoryDao actualFactory = FactoryDao.getFactoryDao(FactoryDao.TEST_FROM_JAVA_ClASS);


    /**********************************************
    *Блок кода по работе с объектами типа CashSource
    **************************************************/

    private static CashSourceDao cashSourceDriver = actualFactory.getCashSourceDAO();

    //Предоставить все объекты CashSource ввиде ArrayList<CashSource>
    public static ArrayList<CashSource> getCashSourceStorage(){
        return cashSourceDriver.fillStorage();
    }

    //Обновить информацию данного источника(кроме поля id)
    public static void updateCashSource(CashSource cs){
        cashSourceDriver.updateCashSource(cs);
    }

    //добавить новый объект CashSource
    public static void addCashSource(CashSource cs){
        cashSourceDriver.insertCashSource(cs);
    }


    /**********************************************
    Блок кода для объектов типа Ballance
    ***************************************************/

    private static BallanceDao ballanceDriver = actualFactory.getBallanceDao();

    // Получить объект балланса
    public static Ballance getBallance(){
        return ballanceDriver.getBallance();
    }

    //Увеличить поле "свободные средства"
    public static void increaseFreeCash(long amount) {
        Ballance ballance = getBallance();
        ballance.setFreeCash(ballance.getFreeCash()+ amount);
    }

    //Уменьшить поле "Свободные средства"
    public  static void decreaseFreeCash(long amount) {
        Ballance ballance = getBallance();
        ballance.setFreeCash(ballance.getFreeCash() - amount);
    }

}
