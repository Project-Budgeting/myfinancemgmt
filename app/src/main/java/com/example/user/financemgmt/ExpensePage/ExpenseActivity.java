package com.example.user.financemgmt.ExpensePage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.user.financemgmt.R;

/**
 * Created by Palibin on 05.10.2017.
 */

public class ExpenseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_activity);
        FragmentManager fm =getSupportFragmentManager();
        ForExpenseFragment decreasableFragment = new ForExpenseFragment();
        decreasableFragment.setFragmentTrigger(true);
        ForExpenseFragment usageFragment = new ForExpenseFragment();
        usageFragment.setFragmentTrigger(false);
        fm.beginTransaction()
          .add(R.id.decreaseFragmentContainer,decreasableFragment)
          .commit();
        fm.beginTransaction()
          .add(R.id.usageFragmentContainer,usageFragment)
          .commit();

    }
}
