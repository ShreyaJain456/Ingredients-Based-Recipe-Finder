package com.recipe.ingredientbasedrecipes;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter_list extends BaseAdapter {
    String result[];
    Context context;
    int position;
    private static LayoutInflater inflater = null;

    public CustomAdapter_list(pdfListVeg pdflistveg, String[] array) {
        result = array;
        context = pdflistveg;
    }

    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       View rowView = view;

        if(view ==null) {
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item, null);

        }

        TextView tv = (TextView) rowView.findViewById(R.id.item);

        tv.setText(result[i]);

        position =i;
     /*   rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,result[position],Toast.LENGTH_LONG).show();
            }
        });
*/
        return rowView;
    }

//        return null;
    }

