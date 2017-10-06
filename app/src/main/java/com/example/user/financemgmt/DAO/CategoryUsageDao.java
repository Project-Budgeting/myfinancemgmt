package com.example.user.financemgmt.DAO;

import com.example.user.financemgmt.DataModel.Usage;

import java.util.ArrayList;

/**
 * Created by Palibin
 */

public interface CategoryUsageDao {
    ArrayList<Usage> getCategoryUsageList();
    void addUsageInList(Usage usage);
}
