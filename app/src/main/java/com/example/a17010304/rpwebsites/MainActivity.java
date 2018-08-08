package com.example.a17010304.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Spinner spnCat, spnSub;
    Button btnGo;

    ArrayList<String> alCategory;
    ArrayAdapter<String> aaCategory;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnCat = findViewById(R.id.spinnerCat);
        spnSub = findViewById(R.id.spinnerSub);
        btnGo = findViewById(R.id.buttonGO);

        alCategory = new ArrayList<>();
        aaCategory = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alCategory);
        spnSub.setAdapter(aaCategory);

        spnCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                alCategory.clear();
                switch(i){
                    case 0:
                        //Get the string-array and store as an Array
                        String[] strCategory = getResources().getStringArray(R.array.sub_category1);

                        //Convert Array to List and add to the ArrayList
                        alCategory.addAll(Arrays.asList(strCategory));
                        spnSub.setSelection(0);
                        break;

                    case 1:
                        //Get the string-array and store as an Array
                        String[] strNumbers = getResources().getStringArray(R.array.sub_category2);

                        //Convert Array to List and add to the ArrayList
                        alCategory.addAll(Arrays.asList(strNumbers));
                        spnSub.setSelection(0);
                        break;

                }
                aaCategory.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = spnCat.getSelectedItemPosition();
                int pos2 = spnSub.getSelectedItemPosition();
                alCategory.clear();
                Intent intent = new Intent(getBaseContext(), Web.class);

                String sites[][] =  {
                    {
                            "https://www.rp.edu.sg/",
                            "https://www.rp.edu.sg/student-life",
                    },
                        {
                           "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12",

                        }
                };

                url = sites[spnCat.getSelectedItemPosition()][spnSub.getSelectedItemPosition()];

//                if (pos == 0){
//                    if (pos2 == 0){
//                        url = "https://www.rp.edu.sg/";
//                    }else{
//                        url = "https://www.rp.edu.sg/student-life";
//                    }
//                }else{
//                    if(pos2 == 0){
//                        url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47";
//                    }else{
//                        url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12";
//                    }
//                }

                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();

        int p1 = spnCat.getSelectedItemPosition();
        int p2 = spnSub.getSelectedItemPosition();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = pref.edit();

        prefEdit.putInt("first", p1);
        prefEdit.putInt("second", p2);

        prefEdit.commit();

    }
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        int thePos = pref.getInt("first", 0);
        int thePos2 = pref.getInt("second", 0);

        spnCat.setSelection(thePos);

        alCategory.clear();

        if (thePos == 0) {
            String[] strSubMenu = getResources().getStringArray(R.array.sub_category1);
            alCategory.addAll(Arrays.asList(strSubMenu));
        } else if (thePos==1) {
            String[] strSubMenu = getResources().getStringArray(R.array.sub_category2);
            alCategory.addAll(Arrays.asList(strSubMenu));
        }


        aaCategory.notifyDataSetChanged();
        spnSub.setSelection(thePos2);
    }

}
