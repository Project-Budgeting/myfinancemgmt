package com.example.user.financemgmt.ExpensePage;

import com.example.user.financemgmt.DAO.DriverDao;
import com.example.user.financemgmt.DataModel.Decreasable;

import java.util.ArrayList;

/**
 * Created by user on 11.10.2017.
 */

public class DecreasableFragmentPresenter extends FinanceFragmentPresenter<Decreasable> {
    private static DecreasableFragmentPresenter singlePresenter;

    private DecreasableFragmentPresenter() {
        super();
    }

    @Override
    public ArrayList<Decreasable> getModel() {
        return DriverDao.getDecreasableList();
    }

    public static DecreasableFragmentPresenter getInstance() {
        if(singlePresenter==null) singlePresenter = new DecreasableFragmentPresenter();
        return singlePresenter;
    }
}
