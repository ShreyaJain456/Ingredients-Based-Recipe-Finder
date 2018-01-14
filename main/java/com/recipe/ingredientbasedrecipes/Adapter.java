package com.recipe.ingredientbasedrecipes;
/*
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Adapter extends BaseExpandableListAdapter{

    private Context context;
    private HashMap<String, List<String>> ingredients;
    private List<String> category;
    private HashMap<String,Boolean[]> checkStates;
    private boolean temp;

    public Adapter(Context context,List<String> category, HashMap<String, List<String>> ingredients)
    {
        this.context=context;
        this.ingredients=ingredients;
        this.category=category;
        this.checkStates = new HashMap<String,Boolean[]>();
        Boolean[] bool0=new Boolean[5];       //Dairy
        Arrays.fill(bool0,false);
        checkStates.put(category.get(0),bool0);

        Boolean[] bool1=new Boolean[10];   //Vegetables
        Arrays.fill(bool1,false);
        checkStates.put(category.get(1),bool1);

        Boolean[] bool2=new Boolean[12];   //Fruits
        Arrays.fill(bool2,false);
        checkStates.put(category.get(2),bool2);

        Boolean[] bool3=new Boolean[12];   //Spices
        Arrays.fill(bool3,false);
        checkStates.put(category.get(3),bool3);

        Boolean[] bool4=new Boolean[5];       //Meat
        Arrays.fill(bool4,false);
        checkStates.put(category.get(4),bool4);

        Boolean[] bool5=new Boolean[9];     //Fish
        Arrays.fill(bool5,false);
        checkStates.put(category.get(5),bool5);
    }
    @Override
    public int getGroupCount() {
        return category.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return ingredients.get(category.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return category.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {     //i for groupCount and i1 for childCount
        return ingredients.get(category.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        if(view==null)
        {
            view=LayoutInflater.from(context).inflate(R.layout.list_group,null);
        }

        String textGroup= (String)getGroup(i);
        TextView textViewGroup=(TextView)view.findViewById(R.id.listTitle);
        textViewGroup.setText(textGroup);

        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {   //i group position i1 child position
       if(view==null)
        {
            view=LayoutInflater.from(context).inflate(R.layout.list_item,null);
            CheckBox box=(CheckBox)view.findViewById(R.id.expandedListCheckBox);
            view.setTag(box);

        }

        CheckBox box=(CheckBox)view.getTag();
        String textGroup= (String)getChild(i,i1);
        TextView textViewGroup=(TextView)view.findViewById(R.id.expandedListItem);
        textViewGroup.setText(textGroup);

        box.setFocusable(false);
        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean[] bool;
                bool=checkStates.get(category.get(i));
                bool[i1]=temp;
                checkStates.put(category.get(i),bool);
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
*/