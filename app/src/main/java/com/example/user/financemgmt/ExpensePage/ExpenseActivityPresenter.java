package com.example.user.financemgmt.ExpensePage;

import com.example.user.financemgmt.DAO.DriverDao;
import com.example.user.financemgmt.DataModel.Decreasable;
import com.example.user.financemgmt.DataModel.JournalRecord;
import com.example.user.financemgmt.DataModel.Usage;

import java.lang.ref.WeakReference;

/**
 * Created by Palibin
 */
public class ExpenseActivityPresenter {
    private WeakReference<ExpenseActivity> view;
    private Decreasable source;
    private Usage usage;



    public void onSourceSelected(int sourceId) {
        Decreasable source = null;
        if (sourceId!=-1) { source = (Decreasable) DriverDao.getDecreasableList().get(sourceId);
        this.source = source;}
        else this.source = null;
        if (isReadyToMakeExpense()) {
            view.get().showExpenseDialog(
                    source.getName(),
                    usage.getName(),
                    this
            );
        }
    }

    public void onUsageSelected(int usageId) {
        Usage usage = null;
        if (usageId!=-1) { usage = DriverDao.getCategoryUsageList().get(usageId);
            this.usage = usage;}
        else this.usage = null;
        if (isReadyToMakeExpense()) {
            view.get().showExpenseDialog(
                    source.getName(),
                    usage.getName(),
                    this
            );
        }
    }

    private boolean isReadyToMakeExpense(){
        return (this.source!=null&&this.usage!=null&&view!=null);
    }

    public void onOkPressedInDialog(String cost){
        //todo сделать обработку неверно введенных результатов
        Long amount = Long.parseLong(cost);
        this.source.decrease(amount);
        JournalRecord.makeRecordInJournal(this.usage,amount,this.usage.getName(),
                                          this.source.getName(),this.usage.getId());
        this.source = null;
        this.usage = null;
        view.get().unselectFragments();
    }

    public void onCancelPressedInDialog(){
        view.get().unselectFragments();
    }

    public void bindView(ExpenseActivity view) {
        this.view = new WeakReference<ExpenseActivity>(view);
    }

    interface ActivityCV {
       void bindPresenter();
       void showExpenseDialog(String sName, String uName, ExpenseActivityPresenter presenter);
       void unselectFragments();
    }
}
