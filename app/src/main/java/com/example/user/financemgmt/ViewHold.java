package com.example.user.financemgmt;

/*
05.10.2017 Добавлен Butter Knife
*/

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ViewHold extends RecyclerView.ViewHolder {
    //поля созданные в файле интерфейса
    /*public  TextView manufacturer;
    public  TextView model;
    public  TextView maxspeed;
    public  TextView id;*/
    @BindView(R.id.Manufacturer)
    TextView manufacturer;
    @BindView(R.id.Model)
    TextView model;
    @BindView(R.id.maxspeed)
    TextView maxspeed;
    @BindView(R.id.id)
    TextView id;

    public ViewHold(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        //привязываем элементы к полям
        /*manufacturer = (TextView)itemView.findViewById(R.id.Manufacturer);
        model = (TextView)itemView.findViewById(R.id.Model);
        maxspeed = (TextView)itemView.findViewById(R.id.maxspeed);
        id = (TextView)itemView.findViewById(R.id.id);*/
    }
}
