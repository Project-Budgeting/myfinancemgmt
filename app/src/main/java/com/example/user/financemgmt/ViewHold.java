package com.example.user.financemgmt;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class ViewHold extends RecyclerView.ViewHolder {
    //поля созданные в файле интерфейса
    public  TextView vManufacturer;
    public  TextView vModel;
    public  TextView vmaxspeed;
    public  TextView vid;

    //объявляем конструктор
    public ViewHold(View itemView){
        super(itemView);
        //привязываем элементы к полям
        vManufacturer = (TextView)itemView.findViewById(R.id.Manufacturer);
        vModel = (TextView)itemView.findViewById(R.id.Model);
        vmaxspeed = (TextView)itemView.findViewById(R.id.maxspeed);
        vid = (TextView)itemView.findViewById(R.id.id);
    }
}
