package com.example.user.financemgmt.ExpensePage;

import android.graphics.Color;

import com.example.user.financemgmt.DAO.DriverDao;
import com.example.user.financemgmt.DataModel.Usage;
import com.example.user.financemgmt.R;

/**
 * Created by PalibinFamily on 08.10.2017.
 */

public class UsageCVPresenter extends FinanceCVPresenter<Usage> {

    public UsageCVPresenter(changingParentAdapter listener) {
        super(listener);
    }

    @Override
    public void updateView(int position) {
        view.get().updateName(getItemName(position));
        if (selectionArray[position]){
            view.get().changeBG(Color.RED);
        } else view.get().changeBG(Color.YELLOW);
    }

    @Override
    public String getItemName(int position) {
        return DriverDao.getCategoryUsageList().get(position).getName();
    }

    //В данной реализации не используется
    @Override
    public long getItemCash(int position) {
        return 0;
    }

    @Override
    public int getModelSize() {
        return DriverDao.getCategoryUsageList().size();
    }

    @Override
    public void onBindViewHolder(int position) {
        updateView(position);
    }
}
