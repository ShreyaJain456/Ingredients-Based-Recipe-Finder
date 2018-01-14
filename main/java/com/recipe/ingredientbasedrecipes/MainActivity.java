package com.recipe.ingredientbasedrecipes;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ExpandableListView list;
    Adapter adapter;
    List<String> groupList;
    HashMap<String,List<String>> ingredients;
    HashMap<String,Boolean[]> checkstate;
    ArrayList<String> choosen;

    String[] ItemName;
    List<Items> rowItem;
    ListView diary,vegetable,fruits;
    ListAdapter diary_adapter,veg_adapter,fruits_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        rowItem=new ArrayList<Items>();
        ItemName=getResources().getStringArray(R.array.diary);
        for(int i=0;i<ItemName.length;i++)
        {
            Items item=new Items(ItemName[i]);
            rowItem.add(item);
        }
        diary=(ListView)findViewById(R.id.ListView1);
        diary_adapter=new ListAdapter(this,rowItem);
        diary.setAdapter(diary_adapter);

        rowItem=new ArrayList<Items>();
        ItemName=getResources().getStringArray(R.array.vegetables);
        for(int i=0;i<ItemName.length;i++)
        {
            Items item=new Items(ItemName[i]);
            rowItem.add(item);
        }
        vegetable=(ListView)findViewById(R.id.ListView2);
        veg_adapter=new ListAdapter(this,rowItem);
        vegetable.setAdapter(veg_adapter);

        rowItem=new ArrayList<Items>();
        ItemName=getResources().getStringArray(R.array.fruits);
        for(int i=0;i<ItemName.length;i++)
        {
            Items item=new Items(ItemName[i]);
            rowItem.add(item);
        }
        fruits=(ListView)findViewById(R.id.ListView3);
        fruits_adapter=new ListAdapter(this,rowItem);
        fruits.setAdapter(fruits_adapter);
        /* init();
        list=(ExpandableListView)findViewById(R.id.expandableListView);
        adapter=new Adapter(this,groupList,ingredients);
        list.setAdapter(adapter);*/

        Button submit=(Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Category");
                alertDialog.setMessage("Choose your category");
                alertDialog.setPositiveButton("Non - Vegetarian", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Bundle b=new Bundle();
                        b.putBooleanArray("diary",diary_adapter.getCheckedState());
                        b.putBooleanArray("veg",veg_adapter.getCheckedState());
                        b.putBooleanArray("fruits",fruits_adapter.getCheckedState());
                        Intent vegactivity = new Intent(MainActivity.this,pdfListNonVeg.class);
                        vegactivity.putExtras(b);
                        startActivity(vegactivity);
                    }
                });
                alertDialog.setNegativeButton("Vegetarian", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Bundle b=new Bundle();
                        b.putBooleanArray("diary",diary_adapter.getCheckedState());
                        b.putBooleanArray("veg",veg_adapter.getCheckedState());
                        b.putBooleanArray("fruits",fruits_adapter.getCheckedState());
                        Intent vegactivity = new Intent(MainActivity.this,pdfListVeg.class);
                        vegactivity.putExtras(b);
                        startActivity(vegactivity);

                    }
                });
                alertDialog.show();
            }
        });
    }

  /*  private void choosenItems()
    {
        choosen=new ArrayList<String>();

        for(int i=0;i<groupList.size();i++)
        {
            for(int j=0;j<ingredients.get(groupList.get(i)).size();j++)
            {
                Boolean[] bool=checkstate.get(i);
                if(bool[j])
                    choosen.add(ingredients.get(i).get(j));
                else
                    continue;
            }
        }

    }*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_recipes) {

            Bundle b=new Bundle();
            b.putBooleanArray("diary",diary_adapter.getCheckedState());
            b.putBooleanArray("veg",veg_adapter.getCheckedState());
            b.putBooleanArray("fruits",fruits_adapter.getCheckedState());
            Intent vegactivity = new Intent(MainActivity.this,RecipeList.class);
            vegactivity.putExtras(b);
            startActivity(vegactivity);
        }if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this,about.class));
        } else if (id == R.id.nav_share) {
            Intent i=new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_SUBJECT,"Parking to JAVA link");
            i.putExtra(Intent.EXTRA_TEXT,"Parking to java app link");

            try
            {
                startActivity(Intent.createChooser(i,"Choose"));
            }catch(android.content.ActivityNotFoundException ex)
            {}

        } else if (id == R.id.nav_send) {
            Intent intent= new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","feedback@gmail.com",null));
            intent.putExtra(Intent.EXTRA_SUBJECT,"Parking Reservation app feedback");

            try
            {
                startActivity(Intent.createChooser(intent,"Send mail..."));
            }catch(android.content.ActivityNotFoundException ex)
            {}
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*private void init()
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
    }*/
}


