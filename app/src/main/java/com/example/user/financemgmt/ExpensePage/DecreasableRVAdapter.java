package com.example.user.financemgmt.ExpensePage;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.financemgmt.DAO.DriverDao;
import com.example.user.financemgmt.DataModel.Decreasable;
import com.example.user.financemgmt.DataModel.Usage;
import com.example.user.financemgmt.R;

import java.util.ArrayList;

/**
 * Created by user on 06.10.2017.
 */

public class DecreasableRVAdapter extends RecyclerView.Adapter<ExpenseRVHolder> implements FinanceCVPresenter.changingParentAdapter {
    /*       Создавать презентер в адаптере неправильно.
    Класс адаптера должен отвечать только за биндинг данных и представления.
    Презентер должен создаваться для Activity или Fragment'а.

    В onBindViewHolder можно или определить внешний вид элемента списка или лучше в классе
    ExpenseRVHolder создать метод bind(Decreasable desreasable) и в методе onBindViewHolder
    адаптера вызывать holder.bind(getItem(position))

    Внутри адаптера должен содержаться список элементов для отображения.
    Его можно передавать в конструкторе, а так же создать в адаптере метод
    setItems(List<Decreasable> items), который будет обновлять элементы списка.

    Также в адаптере нужно хранить id (или еще что-то уникальное) выбранного элемента
    Сделать в адаптере метод setSelectedItem, в который и передавать этот id.
    Во время bind'а view проверять, соответствует ли id текущего элемента выбранному. Если
    соответствует, то менять цвет фона.

    Выбор элемента обрабатывать следующим образом. В адаптере создать интерфейс с методом
    onItemSelected и хранить в адаптере ссылку на объект, имплементирующий этот метод.
    Во фрагменте этот метод сэмплиментировать и передавать фрагмент в конструктор адаптера.
    При клике на элемент в адаптере вызывать метод onItemSelected у объекта, переданного
    в конструктор.

    В самом фрагменте в методе onItemSelected передавать выбранный элемент в presenter и
    id выбранного элемента в адаптер
     */

    DecreasableCVPresenter presenter;

    // Что обозначает приписка RV и CV у имен классов?
    public DecreasableRVAdapter(FragmentActivity activity) {
        presenter = new DecreasableCVPresenter(this);
        presenter.bindActivity(activity);//TODO сделать кнопку "Добавить"
    }

    @Override
    public ExpenseRVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_for_expense, parent, false);
        return new ExpenseRVHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExpenseRVHolder holder, int position) {

        holder.bindPresenter(presenter);
       // holder.setClientTrigger(true);
        presenter.onBindViewHolder(position);

    }

    @Override
    public int getItemCount() {
        return presenter.getModelSize();
    }

    @Override
    public void refreshItem(int position) {
        notifyItemChanged(position);
    }
}
