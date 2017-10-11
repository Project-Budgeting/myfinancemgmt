package com.example.user.financemgmt.ExpensePage;

/**
 * Created by Palibin on 11.10.2017.
 */

public interface FinanceFragmentView {
    void updateCardView(int position);
    void updateSelections (int old, int selected);
    void onFinanceClicked(int position);
}
