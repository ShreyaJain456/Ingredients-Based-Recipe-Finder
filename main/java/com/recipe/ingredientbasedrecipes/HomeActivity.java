package com.recipe.ingredientbasedrecipes;

import android.content.Intent;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    ExpandableListView list;
    //Adapter adapter;
    List<String> groupList;
    HashMap<String,List<String>> ingredients;
    HashMap<String,Boolean[]> checkstate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    private void init()
    {
        List<String> groupList0 = new ArrayList<String>();   //Dairy
        groupList0.add("Milk");
        groupList0.add("Yogurt");
        groupList0.add("Ice Cream");
        groupList0.add("Eggs");
        groupList0.add("Cheese");

        List<String> groupList1 = new ArrayList<String>();     //Vegetables
        groupList1.add("Garlic");
        groupList1.add("Onion");
        groupList1.add("Tomato");
        groupList1.add("Potato");
        groupList1.add("Carrot");
        groupList1.add("Spinach");
        groupList1.add("Cabbage");
        groupList1.add("Cauliflower");
        groupList1.add("Pumpkin");
        groupList1.add("Sweet Corn");

        List<String> groupList2 = new ArrayList<String>();     //Fruits
        groupList2.add("Lemon");
        groupList2.add("Banana");
        groupList2.add("Apple");
        groupList2.add("Mango");
        groupList2.add("Coconut");
        groupList2.add("Orange");
        groupList2.add("Pineapple");
        groupList2.add("Strawberries");
        groupList2.add("Pear");
        groupList2.add("Watermelon");
        groupList2.add("Papaya");
        groupList2.add("Guava");

        List<String> groupList3 = new ArrayList<String>();     //Spices
        groupList3.add("Red Pepper Flake");
        groupList3.add("Oregano");
        groupList3.add("Garlic Powder");
        groupList3.add("Chilli Powder");
        groupList3.add("Peppercorn");
        groupList3.add("Clove");
        groupList3.add("Turmeric");
        groupList3.add("Herbs");
        groupList3.add("Vanilla Essence");
        groupList3.add("Garam Masala");
        groupList3.add("Saffron");
        groupList3.add("Mustard Seeds");

        List<String> groupList4 = new ArrayList<String>();     //meat
        groupList4.add("Chicken Breast");
        groupList4.add("Cooked Chicken");
        groupList4.add("Hot Dog");
        groupList4.add("Pork Roast");
        groupList4.add("Beef Roast");

        List<String> groupList5 = new ArrayList<String>();    //Fish
        groupList5.add("Salmon");
        groupList5.add("Canned Tuna");
        groupList5.add("Grouper");
        groupList5.add("Flounder");
        groupList5.add("White Fish");
        groupList5.add("SwordFish");
        groupList5.add("Red Snapper");
        groupList5.add("Bluefish");
        groupList5.add("Lemon Sole");

        groupList=new ArrayList<String>();
        groupList.add("Dairy");
        groupList.add("Vegetables");
        groupList.add("Fruits");
        groupList.add("Spices");
        groupList.add("Meat");
        groupList.add("Fish");

        ingredients = new HashMap<String,List<String>>();
        ingredients.put(groupList.get(0), groupList0);
        ingredients.put(groupList.get(1), groupList1);
        ingredients.put(groupList.get(2), groupList2);
        ingredients.put(groupList.get(3), groupList3);
        ingredients.put(groupList.get(4), groupList4);
        ingredients.put(groupList.get(5), groupList5);

        checkstate = new HashMap<String,Boolean[]>();

        Boolean[] bool0=new Boolean[5];       //Dairy
        Arrays.fill(bool0,false);
        checkstate.put(groupList.get(0),bool0);

        Boolean[] bool1=new Boolean[10];   //Vegetables
        Arrays.fill(bool1,false);
        checkstate.put(groupList.get(1),bool1);

        Boolean[] bool2=new Boolean[12];   //Fruits
        Arrays.fill(bool2,false);
        checkstate.put(groupList.get(2),bool2);

        Boolean[] bool3=new Boolean[12];   //Spices
        Arrays.fill(bool3,false);
        checkstate.put(groupList.get(3),bool3);

        Boolean[] bool4=new Boolean[5];       //Meat
        Arrays.fill(bool4,false);
        checkstate.put(groupList.get(4),bool4);

        Boolean[] bool5=new Boolean[9];     //Fish
        Arrays.fill(bool5,false);
        checkstate.put(groupList.get(5),bool5);

    }
}
