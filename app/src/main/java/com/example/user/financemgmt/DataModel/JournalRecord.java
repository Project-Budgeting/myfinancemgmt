package com.example.user.financemgmt.DataModel;

/**
 * Created by Palibin
 * Данный класс является записью в журнале событий. Люое изменение кошелька будет создавать фиксацию
 * в журнале с помощью данного объекта.
 */

public class JournalRecord {
    String name; //Наименование объекта, создавшего событие (запись в журнале)
}
