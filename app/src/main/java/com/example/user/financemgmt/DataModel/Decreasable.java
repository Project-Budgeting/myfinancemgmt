package com.example.user.financemgmt.DataModel;

/**
 * Created by Palibin
 * Данный интерфейс объединяет объекты, способные к списанию среств.
 * Когда будет создаваться активити для расходов, то в верхнем HorizontalScroll-е будут отражены
 * доступные объекты для списания (источники, цели, зарезервированные средства)
 */

public interface Decreasable {
    void decrease(long amount);
}
