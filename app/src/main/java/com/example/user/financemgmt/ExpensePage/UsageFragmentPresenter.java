package com.example.user.financemgmt.ExpensePage;

import com.example.user.financemgmt.DAO.DriverDao;
import com.example.user.financemgmt.DataModel.Usage;

import java.util.ArrayList;

/**
 * Created by user on 11.10.2017.
 */

public class UsageFragmentPresenter extends FinanceFragmentPresenter<Usage> {
    private static UsageFragmentPresenter singlePresenter;
    @Override
    public ArrayList<Usage> getModel() {
        return DriverDao.getCategoryUsageList();
    }

    private UsageFragmentPresenter() {
        super();
    }

    public static UsageFragmentPresenter getInstance() {
        if (singlePresenter==null) singlePresenter = new UsageFragmentPresenter();
        return singlePresenter;
    }
}
