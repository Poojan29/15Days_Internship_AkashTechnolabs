package com.example.akashtechnolabinternship.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.akashtechnolabinternship.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;

import org.jetbrains.annotations.NotNull;

public class BMICalculator extends AppCompatActivity {

    Slider height_slider, weight_slider;
    TextView height, weight;
    CardView male_card, female_card;
    MaterialButton materialButton;
    boolean isMale = true;
    float bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        String activityName = getIntent().getStringExtra("ActivityName");
        getSupportActionBar().setTitle(activityName);

        height_slider = findViewById(R.id.height_slider);
        weight_slider = findViewById(R.id.weight_slider);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        male_card = findViewById(R.id.male_card);
        female_card = findViewById(R.id.female_card);
        materialButton = findViewById(R.id.calculate);

        male_card.setOnClickListener(view -> {
            if (isMale){
                male_card.setCardBackgroundColor(Color.parseColor("#ff8a00"));
                female_card.setCardBackgroundColor(Color.parseColor("#ffffff"));
            }else{
                male_card.setCardBackgroundColor(Color.parseColor("#ffffff"));
                female_card.setCardBackgroundColor(Color.parseColor("#ff8a00"));
            }
            isMale = !isMale;
        });

        female_card.setOnClickListener(view -> {
            if (!isMale){
                female_card.setCardBackgroundColor(Color.parseColor("#ff8a00"));
                male_card.setCardBackgroundColor(Color.parseColor("#ffffff"));
            }else{
                female_card.setCardBackgroundColor(Color.parseColor("#ffffff"));
                male_card.setCardBackgroundColor(Color.parseColor("#ff8a00"));
            }
            isMale = !isMale;
        });

        height_slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull @NotNull Slider slider, float value, boolean fromUser) {
                float current_height = slider.getValue();
                height.setText(String.valueOf(current_height));
            }
        });

        weight_slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull @NotNull Slider slider, float value, boolean fromUser) {
                float current_height = slider.getValue();
                weight.setText(String.valueOf(current_height));
            }
        });

        materialButton.setOnClickListener(view -> {
            float weight = weight_slider.getValue();
            float height_in_cm = height_slider.getValue();
            float height_in_meter = height_in_cm / 100;
            float square_height = height_in_meter*height_in_meter;

            bmi = weight / square_height;
            AlertDialog.Builder alert = new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("Your Result.")
                    .setMessage(String.valueOf(bmi) + "\n" + "\n" + getResult() + "\n" + "\n" + getInterpretation())
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            alert.show();
        });
    }


    String getResult() {
        if (bmi >= 25) {
            return "OVERWEIGHT";
        } else if (bmi > 18.5) {
            return "NORMAL";
        } else {
            return "UNDERWEIGHT";
        }
    }

    String getInterpretation() {
        if (bmi >= 25) {
            return "You have a higher than normal body weight. Try to exercise more.";
        } else if (bmi >= 18.5) {
            return "You have a normal body weight. Good job!";
        } else {
            return "You have a lower than normal body weight. You can eat a bit more.";
        }
    }
}