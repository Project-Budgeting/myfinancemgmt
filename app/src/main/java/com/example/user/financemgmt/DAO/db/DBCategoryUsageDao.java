package com.example.user.financemgmt.DAO.db;

import com.example.user.financemgmt.DAO.CategoryUsageDao;
import com.example.user.financemgmt.DAO.DriverDao;
import com.example.user.financemgmt.DataModel.Usage;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Body;

/**
 * Created by PalibinFamily on 15.10.2017.
 */

public class DBCategoryUsageDao extends DBDaoFactory implements CategoryUsageDao{
private ArrayList<Usage> categoryUsageList;
    @Override
    public ArrayList<Usage> getCategoryUsageList() {
        Observable<ArrayList<Usage>> observableList;
        ArrayList<Usage> result;
        restService.getUsageResponse().map(BaseResponse::getData)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultList->{this.SetupDB(resultList);});
        return this.categoryUsageList;
    }

    private void SetupDB(ArrayList<Usage> list){
        this.categoryUsageList = list;
    }

    @Override
    public void addUsageInList(@Body Usage usage) {
        restService.addUsageInList(usage);
    }
}
