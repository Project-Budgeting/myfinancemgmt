package com.example.user.financemgmt.DAO;

import com.example.user.financemgmt.DAO.FactoryDao;
import com.example.user.financemgmt.DataModel.CashSource;
import com.example.user.financemgmt.TestStorageForDataObjects.CashSourcesStorage;

import java.util.ArrayList;

/**
 * Created by Palibin
 * Данный класс представляет собой обработчик объектов, содержащих информацию ввиде Java объектов
 * Он отвечает за то, хранилище какого содержания будет получено для обработки (Доходы, Расходы и т.д).
 * То есть выполняет маршрутизацию, если тип хранилища - база данных, то возвращает классы доступа
 * к базам данных, если выбрано тестовое хранилище, то вызывает классы доступа к тестовым
 * хранилищам.
 * ArrayList содержит все объекты одного типа, созданные пользователем(хранилище).
 *
 */

public class JavaObjectDaoFactory extends FactoryDao {

    @Override
    public CashSourceDao getCashSourceDAO() {
        return new JavaObjectCashSourceDao();
    }

    @Override
    public BallanceDao getBallanceDao() {
        return new JavaObjectBallanceDao();
    }

    @Override
    public JournalDao getJournalDao() {
        return new JavaObjectJournalDao();
    }

    @Override
    public CategoryUsageDao getCategoryUsageDao() {
        return new JavaObjectCategoryUsageDao();
    }


}
