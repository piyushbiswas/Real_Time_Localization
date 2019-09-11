package com.example.real_time_localization;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button changelanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadLocal();
        setContentView(R.layout.activity_main);



        changelanguage= findViewById(R.id.button3);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        changelanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showchangelanguagedialog();
            }
        });
    }


    private void showchangelanguagedialog() {

        final String[] listitems = {"English","मराठी","বাংলা","español"};

        final AlertDialog.Builder mbuilder = new AlertDialog.Builder(MainActivity.this);

        mbuilder.setTitle("change language");
        mbuilder.setSingleChoiceItems(listitems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (i == 0) {

                    setLocal("En");
                    recreate();
                } else if (i == 1) {

                    setLocal("mr");
                    recreate();

                } else if (i == 2) {

                    setLocal("bn");
                    recreate();

                } else if (i == 3)
                {

                    setLocal("es");
                    recreate();
                }


                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog=mbuilder.create();
        alertDialog.show();
    }

    private void setLocal(String en) {

        Locale locale = new Locale(en);
        locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Setting",MODE_PRIVATE).edit();
        editor.putString("My lang",en);
        editor.apply();
    }
    private void loadLocal() {

        SharedPreferences preferences = getSharedPreferences("Setting", Activity.MODE_PRIVATE);
        String language = preferences.getString("my lang","");
        setLocal(language);


    }
}