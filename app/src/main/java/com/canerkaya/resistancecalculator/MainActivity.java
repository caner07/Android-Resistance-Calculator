package com.canerkaya.resistancecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String[] colors=new String[9];
    String[] colors2 =new String[10];
    String[] colors3 =new String[]{"(BOŞ)","0-  Siyah","1-  Kahverengi","2-  Kırmızı","3-  Turuncu","4-  Sarı","5-  Yeşil","6-  Mavi","7-  Mor","8-  Gri","9-  Beyaz"};
    String[] textColors=new String[]{"#000000","#4C2525","#FF0606","#F4511E","#FFB300","#43A047","#1E88E5","#5E35B1","#565459","#9E9AA5"};
    Spinner spinner1,spinner2,spinner3;
    TextView textView1,textView2,textView3,resultText,resOne,resTwo,resThree;
    int first,second,third;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i=2;i<colors3.length;i++){ // Birinci bant renk listesi
            colors[i-2]=colors3[i];
        }
        for (int i=1;i<colors3.length;i++){ // İkinci bant renk listesi
            colors2[i-1]=colors3[i];
        }
        first=1; second=0; third=20;
        resOne=findViewById(R.id.resOne);
        resTwo=findViewById(R.id.resTwo);
        resThree=findViewById(R.id.resThree);
        textView1=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        resultText=findViewById(R.id.result);
        spinner1 =findViewById(R.id.spinner);
        spinner2=findViewById(R.id.spinner2);
        spinner3=findViewById(R.id.spinner3);
        ArrayAdapter<String>arrayAdapter1=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,colors);
        spinner1.setAdapter(arrayAdapter1);
        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, colors2);
        spinner2.setAdapter(arrayAdapter2);
        ArrayAdapter<String>arrayAdapter3=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, colors3);
        spinner3.setAdapter(arrayAdapter3);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                for (int i=0;i<colors.length;i++){
                    if (parent.getSelectedItem().toString().matches(colors[i])){
                        resOne.setBackgroundColor(Color.parseColor(textColors[i+1]));
                        textView1.setTextColor(Color.parseColor(textColors[i+1]));
                        first=i+1;
                    }
                }
                calculate(first,second,third);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int i=0;i<colors2.length;i++){
                    if (parent.getSelectedItem().toString().matches(colors2[i])){
                        resTwo.setBackgroundColor(Color.parseColor(textColors[i]));
                        textView2.setTextColor(Color.parseColor(textColors[i]));
                        second=i;
                    }
                }
                calculate(first,second,third);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    for (int i=0;i<colors3.length;i++){
                        if (parent.getSelectedItem().toString().matches(colors3[i])){
                            resThree.setBackgroundColor(Color.parseColor(textColors[i-1]));
                            textView3.setTextColor(Color.parseColor(textColors[i-1]));
                            third=i-1;
                        }
                    }
                }else if (position==0){
                    third=20;
                    resThree.setBackgroundColor(Color.WHITE);
                    textView3.setTextColor(Color.BLACK);
                }
                calculate(first,second,third);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
    public void calculate(int first,int second,int third){
        int result;
        if (third!=20){
            result= ((first*10)+second)*(int) (Math.pow(10,third));
            resultText.setText(first+""+second+" x "+"(10 üzeri "+third+") = "+result+"Ω (Ohm)");
        }else{ // Üçüncü bant boşsa third değeri 20 gelir hesaplama yolu değişir..
            result=(first)*(int)(Math.pow(10,second));
            resultText.setText(first+" x (10 üzeri "+second+") = "+result+"Ω (Ohm)");
        }


    }
}






