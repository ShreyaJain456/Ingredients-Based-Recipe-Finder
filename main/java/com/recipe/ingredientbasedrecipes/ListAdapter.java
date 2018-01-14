package com.recipe.ingredientbasedrecipes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Dell pc on 04-11-2017.
 */

public class ListAdapter extends BaseAdapter{

    Context context;
    List<Items> rowItem;
    View listView;
    boolean checkState[];

    ViewHolder holder;

    public ListAdapter(Context context,List<Items> rowItem)
    {
        this.context=context;
        this.rowItem=rowItem;
        checkState=new boolean[rowItem.size()];
    }

    public boolean[] getCheckedState()
    {
        return checkState;
    }
    @Override
    public int getCount()
    {
        return rowItem.size();
    }

    @Override
    public Object getItem(int position)
    {
        return rowItem.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return rowItem.indexOf(getItem(position));
    }

    public class ViewHolder
    {
        TextView item;
        CheckBox check;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent)
    {
        holder=new ViewHolder();
        final Items item=rowItem.get(position);
        LayoutInflater layout=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(view==null) {
            listView = new View(context);
            listView = layout.inflate(R.layout.list_item, parent, false);
            holder.item = (TextView) listView.findViewById(R.id.expandedListItem);
            holder.check = (CheckBox) listView.findViewById(R.id.expandedListCheckBox);
            listView.setTag(holder);
        }
        else {
            listView = (View) view;
            holder = (ViewHolder) listView.getTag();
        }
        holder.item.setText(item.getItems());
        holder.check.setChecked(checkState[position]);

        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              checkState[position]=true;
            }
        });
        return listView;

        }
    }
