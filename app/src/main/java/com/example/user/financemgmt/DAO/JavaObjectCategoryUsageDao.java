package com.example.user.financemgmt.DAO;

import com.example.user.financemgmt.DataModel.Usage;
import com.example.user.financemgmt.TestStorageForDataObjects.UsageCategoryStorage;

import java.util.ArrayList;

/**
 * Created by Palibin
 */

public class JavaObjectCategoryUsageDao extends JavaObjectDaoFactory implements CategoryUsageDao {

    private static ArrayList<Usage> getStorage() {
        return UsageCategoryStorage.getUsageStorage().getStorageList();
    }

    @Override
    public ArrayList<Usage> getCategoryUsageList() {
        return getStorage();
    }

    @Override
    public void addUsageInList(Usage usage) {
        getStorage().add(usage);
    }
}
