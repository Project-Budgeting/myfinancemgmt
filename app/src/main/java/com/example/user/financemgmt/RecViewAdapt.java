package com.example.user.financemgmt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class RecViewAdapt extends RecyclerView.Adapter<ViewHold> {
    //Здесь мы будем хранить набор наших данных
    private ArrayList<Cars> cars;
    private final CustomAdapterCallBack listener;

    public RecViewAdapt(ArrayList<Cars> cars1, Context activity) {
        this.cars = cars1;
        listener = (CustomAdapterCallBack) activity;
    }

    //Этот метод вызывается при прикреплении нового элемента к RecyclerView
    @Override
    public void onBindViewHolder(ViewHold viewHold, int i) {
        //Получаем элемент набора данных для заполнения
        Cars currentCars = cars.get(i);
        //Заполняем поля viewHolder'а данными из элемента набора данных
        viewHold.manufacturer.setText(currentCars.Manufacturer);
        viewHold.model.setText(currentCars.Model);
        viewHold.maxspeed.setText(currentCars.maxspeed);
        viewHold.id.setText(currentCars.id);
    }

    //Этот метод вызывается при создании нового ViewHolder'а

    @Override
    public ViewHold onCreateViewHolder(ViewGroup viewGroup, int i) {
        //Создаём новый view при помощи LayoutInflater
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cards, viewGroup, false);

        return new ViewHold(itemView, listener);
    }

    //Этот метод возвращает количество элементов списка
    @Override
    public int getItemCount() {
        return cars.size();
    }

    //добавляем интерфейс.
    interface CustomAdapterCallBack {
        void onItemClick(int position);
    }
}

