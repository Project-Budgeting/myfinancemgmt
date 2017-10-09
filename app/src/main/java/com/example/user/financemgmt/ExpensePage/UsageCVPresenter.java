package com.example.user.financemgmt.ExpensePage;

import com.example.user.financemgmt.DAO.DriverDao;
import com.example.user.financemgmt.DataModel.Usage;

/**
 * Created by PalibinFamily on 08.10.2017.
 */

public class UsageCVPresenter extends FinanceCVPresenter<Usage> {

    @Override
    public void updateView(int position) {
        view.get().updateName(getItemName(position));
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
