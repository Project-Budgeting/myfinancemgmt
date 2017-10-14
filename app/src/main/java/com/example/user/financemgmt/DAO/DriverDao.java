package com.example.user.financemgmt.DAO;

import com.example.user.financemgmt.DataModel.CashSource;
import com.example.user.financemgmt.DataModel.Decreasable;
import com.example.user.financemgmt.DataModel.JournalRecord;
import com.example.user.financemgmt.DataModel.TypesOfCashObjects;
import com.example.user.financemgmt.DataModel.Usage;
import com.example.user.financemgmt.TestStorageForDataObjects.Ballance;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.name;

/**
 * Created by Palibin
 * Данный класс отделяет реализацию доступа к данным от функционала.
 * Жду от вас запрос на необходимость
 * TODO: Дописать методы для Expense
 * TODO: Везде получаю ссылку на приватные поля синглтона и напрямую пишу изменения по ссылке
 * TODO: проверить работают ли так эти методы
 */

public class DriverDao {
    /*Заменив значение TEST_FROM_JAVA_ClASS на другое, мы переключим работу драйвера на
    совершенно другой источник, но методы продолжат выполняться без изменений.
    */
    private static FactoryDao actualFactory = FactoryDao.getFactoryDao(FactoryDao.TEST_FROM_JAVA_ClASS);


    /**********************************************
    *Блок кода по работе с хранилищем типа CashSource
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

    public static CashSource getCashSourceById(String id) {
        return cashSourceDriver.getSourceById(id);}


    /**********************************************
    Блок кода для объектов типа Ballance
    ***************************************************/

    private static BallanceDao ballanceDriver = actualFactory.getBallanceDao();

    // Получить объект балланса
    public static Ballance getBallance(){
        return ballanceDriver.getBallance();
    }

    //Увеличить поле "свободные средства"
    public static void increaseFreeCashOfBallance(long amount) {
        Ballance ballance = getBallance();
        ballance.setFreeCash(ballance.getFreeCash()+ amount);
    }

    //Уменьшить поле "Свободные средства"
    public  static void decreaseFreeCashOfBallance(long amount) {
        Ballance ballance = getBallance();
        ballance.setFreeCash(ballance.getFreeCash() - amount);
    }

    /**********************************************
     Блок кода по работе с хранилищем JournalStorage
     ***************************************************/
    private static JournalDao journalDao = actualFactory.getJournalDao();

    //Вставка записи в журнал
    public static void insertRecordInJournal(JournalRecord jr){
        journalDao.insertJournalRecordByDate(jr);

    }
    //Выгружает из журнала записи указанного типа за указанный период
    public static HashMap<GregorianCalendar, ArrayList<JournalRecord>> getCustomMapFromStorage
    (GregorianCalendar startDate, GregorianCalendar endDate, TypesOfCashObjects type){
        return journalDao.getCustomMapFromStorage(startDate, endDate, type);
    }

    /*Выдает сумму полей amount всех объектов JournalRecord, Отвечающих требованиям заданного
     временного периода, типа записей и наименования объекта. Если в параметре name передать null,
     то требования к выборке по названию не предъявляются.
     Например при name = null: Возвращает сумму все доходов за указанный период или сумму всех затрат
     При заданном name = зарплата: Возвращает сумму всех пополнений с исчтоника "Зарплата" за
     указанный период.
     При заданном name = на еду: Возвращает сумму потраченных средств категории "на еду".
     TODO как обработать null в параметре type?*/
    public long getSummOfRecordType
    (GregorianCalendar startDate,
     GregorianCalendar endDate,
     TypesOfCashObjects type,
     String name) {

        HashMap<GregorianCalendar, ArrayList<JournalRecord>> recordsOfTypeMap =
                journalDao.getCustomMapFromStorage(startDate, endDate, type);
        long amountSumm = 0;

        for (Map.Entry<GregorianCalendar, ArrayList<JournalRecord>> entry : recordsOfTypeMap.entrySet()) {

            ArrayList<JournalRecord> typeList = entry.getValue();

            if (typeList != null) {
                for (JournalRecord jr : typeList) {
                    if(name!=null) {
                        if (jr.getName().equals(name)) amountSumm += jr.getAmount();
                    } else amountSumm += jr.getAmount();
                }
            }
        }
        return amountSumm;
    }

   /* TODO внедрим, когда в модели данных внедрим соответствующий функционал
    //Сумма отложенных средств на выполнение целей в указанный период
    public long getTargetReservs() {
        return 0;
    }
    */

    /****************************************************
     * Блок кода по работе с хранилищем UsageCategoryStorage
     *****************************************************/
   private static CategoryUsageDao categoryUsageDriver = actualFactory.getCategoryUsageDao();

    public static ArrayList<Usage> getCategoryUsageList() {
        return categoryUsageDriver.getCategoryUsageList();
    }

    public static void addUsageInList(Usage usage) {
        categoryUsageDriver.addUsageInList(usage);
    }

    /****************************************
     * Остальные методы
     */
    public static ArrayList getDecreasableList(){
        return getCashSourceStorage();
    }

}
