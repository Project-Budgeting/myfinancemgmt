package com.example.user.financemgmt.ExpensePage;

/**
 * Created by Palibin on 11.10.2017.
 * создан, чтобы у вью хлдера не было привязки к конкретному адаптеру.
 */

public interface SelectableRecyclerViewItem {
    void onItemClicked(int position);
}
