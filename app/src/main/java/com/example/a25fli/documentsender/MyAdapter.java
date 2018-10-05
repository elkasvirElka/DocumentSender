package com.example.a25fli.documentsender;

        import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
//класс для вставки данных в грил размерностью больше одного столбца
public class MyAdapter extends BaseAdapter {

    ArrayList<myTableClass> data = new ArrayList<myTableClass>();
    Context context;

    public MyAdapter(Context context, ArrayList<myTableClass> arr) {
        if (arr != null) {
            data = arr;
        }
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int num) {
        // TODO Auto-generated method stub
        return data.get(num);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int i, View someView, ViewGroup arg2) {
        //Получение объекта inflater из контекста
        LayoutInflater inflater = LayoutInflater.from(context);
        //Если someView (View из ListView) вдруг оказался равен
        //null тогда мы загружаем его с помошью inflater
        if (someView == null) {
            someView = inflater.inflate(R.layout.list_view_item, arg2, false);
        }
        //Обявляем наши текствьюшки и связываем их с разметкой
        TextView header = (TextView) someView.findViewById(R.id.item_col1);
        TextView subHeader = (TextView) someView.findViewById(R.id.item_col2);
        TextView subHeader2 = (TextView) someView.findViewById(R.id.item_col3);

        //Устанавливаем в каждую текствьюшку соответствующий текст
        // сначала заголовок
        header.setText(data.get(i).col1);
        // потом подзаголовок
        subHeader.setText(data.get(i).col2);
        subHeader2.setText(data.get(i).col3);
        return someView;
    }

}
