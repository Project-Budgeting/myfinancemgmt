package com.example.user.financemgmt.DAO.db;

import com.example.user.financemgmt.DAO.CategoryUsageDao;
import com.example.user.financemgmt.DataModel.Usage;

import java.util.ArrayList;

import retrofit2.http.Body;

/**
 * Created by PalibinFamily on 15.10.2017.
 */

public class DBCategoryUsageDao extends DBDaoFactory implements CategoryUsageDao{
    @Override
    public ArrayList<Usage> getCategoryUsageList() {
        return restService.getCategoryUsageList();
    }

    @Override
    public void addUsageInList(@Body Usage usage) {
        restService.addUsageInList(usage);
    }
}
