package com.example.user.financemgmt;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by PalibinFamily on 01.10.2017.
 */

public class CashControlRecyclerAdapter extends RecyclerView.Adapter<CashControlRecyclerAdapter
                                                            .CashControlsViewHolder> {

    @Override
    public CashControlsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CashControlsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class CashControlsViewHolder extends RecyclerView.ViewHolder {

        public CashControlsViewHolder(View itemView) {
            super(itemView);
        }
    }
}
