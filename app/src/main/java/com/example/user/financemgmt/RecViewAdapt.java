package com.example.user.financemgmt;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class RecViewAdapt extends RecyclerView.Adapter<ViewHold> {
    //Здесь мы будем хранить набор наших данных
    private ArrayList<Cars> cars;

    public RecViewAdapt(ArrayList<Cars> cars1) {
        this.cars = cars1;
    }

    //Этот метод вызывается при прикреплении нового элемента к RecyclerView
    @Override
    public void onBindViewHolder(ViewHold viewHold, int i) {
        //Получаем элемент набора данных для заполнения
        Cars currentCars = cars.get(i);
        //Заполняем поля viewHolder'а данными из элемента набора данных
        viewHold.vManufacturer.setText(currentCars.Manufacturer);
        viewHold.vModel.setText(currentCars.Model);
        viewHold.vmaxspeed.setText(currentCars.maxspeed);
        viewHold.vid.setText(currentCars.id);
    }

    //Этот метод вызывается при создании нового ViewHolder'а

    @Override
    public ViewHold onCreateViewHolder(ViewGroup viewGroup, int i) {
        //Создаём новый view при помощи LayoutInflater
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cards, viewGroup, false);

        return new ViewHold(itemView);
    }

    //Этот метод возвращает количество элементов списка
    @Override
    public int getItemCount() {
        return cars.size();
    }
}

