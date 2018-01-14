package com.recipe.ingredientbasedrecipes;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class pdfDownload extends AppCompatActivity {

    String recipe;
    File localFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_download);

        Bundle b=this.getIntent().getExtras();
        recipe=b.getString("Recipe");
        String category=b.getString("Category");

        TextView recipe_text=(TextView)findViewById(R.id.item_name);
        TextView category_text=(TextView)findViewById(R.id.category);
        Button download=(Button)findViewById(R.id.download);
        recipe_text.setText(recipe);
        category_text.setText(category);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File file=null;
                if(isExternalStorageWritable())
                   file=Environment.getExternalStorageDirectory();

                if(file!=null)
                localFile=new File(file,recipe+".pdf");

                FirebaseStorage storage=FirebaseStorage.getInstance();
                StorageReference ref=null;
                if(recipe.equalsIgnoreCase("Homemade Yogurt"))
                {
                    ref=storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/ingredientbasedrecipes.appspot.com/o/Yogurt%20Popsicles.pdf?alt=media&token=122e0256-89f0-4a43-bd1d-1c26a14b4277");
                }
                else if(recipe.equalsIgnoreCase("Microwave Scrambled Eggs"))
                {
                    ref=storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/ingredientbasedrecipes.appspot.com/o/Microwave%20Scrambled%20Eggs.pdf?alt=media&token=b1bc576c-318f-4b39-aae1-5bc5f2e42a58");
                }
                else if(recipe.equalsIgnoreCase("Vanilla Milkshake"))
                {
                    ref=storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/ingredientbasedrecipes.appspot.com/o/Vanilla%20Milkshake.pdf?alt=media&token=ffd63804-bedf-43e9-ac52-c719a6924d97");
                }
                else
                {
                    ref=storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/ingredientbasedrecipes.appspot.com/o/Yogurt%20Popsicles.pdf?alt=media&token=122e0256-89f0-4a43-bd1d-1c26a14b4277");
                }
                ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(),"File is downloaded",Toast.LENGTH_LONG).show();
                        Uri path=Uri.fromFile(localFile);
                        Intent pdfintent=new Intent(Intent.ACTION_VIEW);
                        pdfintent.setDataAndType(path,"application/pdf");
                        pdfintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        try
                        {
                            startActivity(pdfintent);
                        }catch(ActivityNotFoundException e)
                        {}
                    };
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"File is not downloaded",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }
    public boolean isExternalStorageWritable()
    {
        String state=Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state))
        {
            return true;
        }
        return false;
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
