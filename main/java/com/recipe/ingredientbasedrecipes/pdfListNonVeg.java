package com.recipe.ingredientbasedrecipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class pdfListNonVeg extends AppCompatActivity {

    ListView lv;
    String array1[],array2[];
    boolean diary[],veg[],fruits[];
    int a_i;
    String[] ing=new String[100];
    int n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_listveg);

        Bundle b=this.getIntent().getExtras();
        diary=b.getBooleanArray("diary");
        veg=b.getBooleanArray("veg");
        fruits=b.getBooleanArray("fruits");
        ingredients();
        Firebase.setAndroidContext(getApplicationContext());
        Firebase mRootRef =new Firebase("https://ingredientbasedrecipes.firebaseio.com/");
        mRootRef.child("Non- Vegetarian").addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {

                long size=dataSnapshot.getChildrenCount();
                array1=new String[(int)size];
                a_i=0;
                for(com.firebase.client.DataSnapshot recipe : dataSnapshot.getChildren())
                {
                    String str=recipe.getValue(String.class);
                    String[] item=str.split(",");
                    int flag=0,contains=1;
                    for(int i=0;i<n;i++)
                    {
                        flag=0;
                        for(int j=0;j<item.length;j++)
                        {
                            ing[i]=ing[i].trim();
                            item[j]=item[j].trim();
                            if(ing[i].equalsIgnoreCase(item[j]))
                            {
                                flag=1;
                            }
                        }
                        if(flag==0)
                        {
                            contains=0;
                            break;
                        }
                    }
                    if(contains==1) {
                        array1[a_i] = recipe.getKey();
                        a_i++;
                    }
                    array2=new String[a_i];
                    for(int i=0;i<a_i;i++)
                        array2[i]=array1[i];
                    displayList();

                }
            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    private void ingredients()
    {
        n=0;
        String[] diary_list=getResources().getStringArray(R.array.diary);
        String[] veg_list=getResources().getStringArray(R.array.vegetables);
        String[] fruits_list=getResources().getStringArray(R.array.fruits);

        for(int i=0;i<diary.length;i++)
        {
            if(diary[i]==true) {
                ing[n] = diary_list[i];
                n++;
            }
        }
        for(int i=0;i<veg.length;i++)
        {
            if(veg[i]==true) {
                ing[n] = veg_list[i];
                n++;
            }
        }
        for(int i=0;i<fruits.length;i++)
        {
            if(fruits[i]==true) {
                ing[n] = fruits_list[i];
                n++;
            }
        }

    }
    private void displayList()
    {
        lv=(ListView)findViewById(R.id.list);
        lv.setAdapter(new ArrayAdapter<String>(this,R.layout.item_list,R.id.item,array2));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle b=new Bundle();
                b.putString("Recipe",array2[i]);
                b.putString("Category","Non-Vegetarian");
                Intent intent=new Intent(pdfListNonVeg.this,pdfDownload.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed() {
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}

