package com.example.user.financemgmt.DAO;

import com.example.user.financemgmt.TestStorageForDataObjects.Ballance;

/**
 * Created by Palibin
 * Класс, необходимый для реализации Паттерна Data Access Object
 * Данный класс в зависимости от переданного значения ему значения возвращает обработчик
 * соответствующий источнику. В первоначальном виде исчтоник будет один - обычный экземпляр класса.
 * В дальнейшем в качестве источника прибавится база данных. Также класс определяет, какие виды
 * объектов можно извлекать с помощью вызываемого обработчика.
 */

public abstract class FactoryDao {
    //Передаваемый классу параметр для определения исчтоника объектов данных.
    public static final int TEST_FROM_JAVA_ClASS = 1;

    //Список объектов, которые должен уметь обрабатывать наш обработчик.
    //TODO: написать CashSourceDao Interface
    public abstract CashSourceDao getCashSourceDAO();
    public abstract BallanceDao getBallanceDao();
    public abstract JournalDao getJournalDao();
    public abstract CategoryUsageDao getCategoryUsageDao();

    public static FactoryDao getFactoryDao(int type) {
        switch (type) {
            case (TEST_FROM_JAVA_ClASS):
                return new JavaObjectDaoFactory();
            default:
                return null;
        }
    }
}
