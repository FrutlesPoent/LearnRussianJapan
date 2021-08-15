package com.example.learnjapanwords;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Dialog dialog;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dialog = new Dialog(MainActivity.this);
        dbHelper = new DBHelper(this);
        dbHelper.getReadableDatabase();
//        dbHelper.deleteDB(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setColorFilter(Color.argb(255, 255, 255, 255));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.dialog);
                Button dismiss = (Button) dialog.findViewById(R.id.button_dismiss);
                Button addSingle = (Button) dialog.findViewById(R.id.button_add);
                Button addDocument = (Button) dialog.findViewById(R.id.button_add_document);

                dismiss.setOnClickListener(new View.OnClickListener() { // cancel adding
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                addSingle.setOnClickListener(new View.OnClickListener() { // add single pair of words
                    @Override
                    public void onClick(View v) {
                        TextInputEditText wordsOnJapan = dialog.findViewById(R.id.input_japan_word);
                        TextInputEditText wordsOnRussian = dialog.findViewById(R.id.input_russian_word);
                        String japanWord = wordsOnJapan.getText().toString();
                        String russiaWord = wordsOnRussian.getText().toString();
                        Parser parse = new Parser();
                        if (!parse.checkWords(russiaWord, japanWord)) {
                            Toast.makeText(getApplicationContext(), parse.getErrorMessage(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            dbHelper.dbEnterData(russiaWord, japanWord);
                            dialog.dismiss();
                        }
                    }
                });
                addDocument.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ReadFile readFile = new ReadFile();
                        PDFBoxResourceLoader.init(getApplicationContext());
                        AssetManager assetManager;
                        assetManager = getAssets();
                        readFile.parsePDFile(assetManager, dbHelper);
                        dialog.dismiss();
                    }
                });
                dialog.setCancelable(false);
                dialog.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}