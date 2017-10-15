package com.example.user.financemgmt.DAO.db;

import com.example.user.financemgmt.DAO.BallanceDao;
import com.example.user.financemgmt.DAO.CashSourceDao;
import com.example.user.financemgmt.DAO.CategoryUsageDao;
import com.example.user.financemgmt.DAO.FactoryDao;
import com.example.user.financemgmt.DAO.JavaObjectBallanceDao;
import com.example.user.financemgmt.DAO.JavaObjectCashSourceDao;
import com.example.user.financemgmt.DAO.JavaObjectJournalDao;
import com.example.user.financemgmt.DAO.JournalDao;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;


/**
 * Created by PalibinFamily on 15.10.2017.
 */

public class DBDaoFactory extends FactoryDao {
    public static final String BASE_URL = "https://android-cources.simbirsoft1.com/api/";
    protected CategoryUsageDao restService;

    protected Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(this.BASE_URL)
            .client(provideClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    protected OkHttpClient provideClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
    }

    private CategoryUsageDao getRestService(){
        if (restService==null) retrofit.create(CategoryUsageDao.class);
        return restService;
    }

    /*
    TODO ВНИМАНИЕ! Костыли для тестирвоания. Переработать
     */

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
/*
Конец костылей
 */

    @Override
    public CategoryUsageDao getCategoryUsageDao() {
        getRestService();
        return new DBCategoryUsageDao();
    }
}
