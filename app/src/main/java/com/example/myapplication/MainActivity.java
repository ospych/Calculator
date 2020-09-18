package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    Button button;
    TextView text;
    Editable firstGl, secondGl;
    double sum;
    int simInt;
    String sumS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText firstEdit = findViewById(R.id.first_number);
        firstGl = firstEdit.getText();

        EditText secondEdit = findViewById(R.id.second_number);
        secondGl = secondEdit.getText();

        text = findViewById(R.id.symbols);

        button = findViewById(R.id.equal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openResult();
            }
        });
    }

    public void plus(View view){
        text.setText("+");
    }

    public void minus(View view){ text.setText("-"); }

    public void mult(View view){ text.setText("*"); }

    public void div(View view){ text.setText("/"); }


    @SuppressLint("DefaultLocale")
    public void openResult() {
        try {
            Intent intent = new Intent(this, resultActivity.class);

            CheckBox fractionsCheckbox = findViewById(R.id.use_fractions);
            boolean hasFractions = fractionsCheckbox.isChecked();

            String symbol = text.getText().toString();

            int f = Integer.parseInt(String.valueOf(firstGl));
            int s = Integer.parseInt(String.valueOf(secondGl));

            double fDouble = Double.parseDouble(String.valueOf(firstGl));
            double sDouble = Double.parseDouble(String.valueOf(secondGl));

            if (hasFractions) {
                switch (symbol) {
                    case "/":
                        if (fDouble == 0.0) {
                            Toast.makeText(this, "Number must be more than 0", Toast.LENGTH_SHORT).show();
                        }
                        sum = fDouble / sDouble;
                        break;
                    case "+":
                        sum = fDouble + sDouble;
                        break;
                    case "-":
                        sum = fDouble - sDouble;
                        break;
                    case "*":
                        sum = fDouble * sDouble;
                        break;
                }
                String formatted = String.format("%.2f", sum);
                sumS = "" + formatted;
            } else {
                switch (symbol) {
                    case "/":
                        if (f == 0) {
                            Toast.makeText(this, "Number must be more than 0", Toast.LENGTH_SHORT).show();
                        }
                        simInt = f / s;
                        break;
                    case "+":
                        simInt = f + s;
                        break;
                    case "-":
                        simInt = f - s;
                        break;
                    case "*":
                        simInt = f * s;
                        break;
                }
                sumS = "" + simInt;
            }
            intent.putExtra(EXTRA_MESSAGE, sumS);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this,"Enter numbers!", Toast.LENGTH_SHORT).show();
        }

    }
}