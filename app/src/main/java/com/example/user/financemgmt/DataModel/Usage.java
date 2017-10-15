package com.example.user.financemgmt.DataModel;

import java.util.ArrayList;

/**
 * Created by Palibin
 * Класс определяет назначение расхода
 * Если поле ArrayList заполнено, то экземпляр класса представляет собой категорию расходов
 * Создан для удобства, чтобы можно было объеденить несколько видов расходов в одну категорию
 * Например: Еда, бензин, коммунальные услуги объеденить в категорию "На жизнь"
 */

public class Usage {
    private String id;
    private static int count = 100;
    private String name;
    private ArrayList<Usage> usages; //если not null, то данный объект - категоря расходов

    public Usage(String name, ArrayList<Usage> usages) {
        this.name = name;
        this.usages = usages;
        this.id = generateid();
        count++;
    }

    public String getId() {
        return id;
    }

    private String generateid(){
        return Integer.toString(count+1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsages(ArrayList<Usage> usages) {
        this.usages = usages;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Usage> getUsages() {
        return usages;
    }
}


