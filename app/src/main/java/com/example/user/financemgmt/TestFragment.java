package com.example.user.financemgmt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TestFragment extends Fragment {

// следует переименовать в recyclerView
    //Объявим RecyclerView
    RecyclerView rvMain;
//     следует переименовать в carsAdapter
    //И его адаптер
    RecViewAdapt Adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.journal_fragment_recycler, container, false);

        //Привяжем его к элементу
        rvMain = (RecyclerView) v.findViewById(R.id.rvRecyclerView);
        //Создадим адаптер
        Adapter = new RecViewAdapt(getCars());
        //Применим наш адаптер к RecyclerView
        rvMain.setAdapter(Adapter);
        //И установим LayoutManager
        rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }




    public ArrayList<Cars> getCars(){
        ArrayList<Cars> cars = new ArrayList<>();

        Cars Ferrari = new Cars();
        Ferrari.Manufacturer = "Ferrari";
        Ferrari.Model = "TestaRossa";
        Ferrari.id = "1";
        Ferrari.maxspeed = "300";
        cars.add(Ferrari);

        Cars Porsche = new Cars();
        Porsche.Manufacturer = "Porsche";
        Porsche.Model = "911";
        Porsche.id = "2";
        Porsche.maxspeed = "330";
        cars.add(Porsche);

        Cars Aston = new Cars();
        Aston.Manufacturer = "Aston Martin";
        Aston.Model = "DB9";
        Aston.id = "3";
        Aston.maxspeed = "337";
        cars.add(Aston);

        Cars AstonOne = new Cars();
        AstonOne.Manufacturer = "Aston Martin";
        AstonOne.Model = "DB9";
        AstonOne.id = "4";
        AstonOne.maxspeed = "337";
        cars.add(AstonOne);

        Cars FerrariOne = new Cars();
        FerrariOne.Manufacturer = "Ferrari";
        FerrariOne.Model = "TestaRossa";
        FerrariOne.id = "5";
        FerrariOne.maxspeed = "300";
        cars.add(FerrariOne);

        Cars PorscheOne = new Cars();
        PorscheOne.Manufacturer = "Porsche";
        PorscheOne.Model = "911";
        PorscheOne.id = "6";
        PorscheOne.maxspeed = "330";
        cars.add(PorscheOne);
        return cars;
    }

}
