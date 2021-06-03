package com.example.akashtechnolabinternship.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akashtechnolabinternship.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class PrimeNumber extends AppCompatActivity {

    TextInputEditText number, first_number, second_number;
    TextView answer;
    Button interval_check_button, single_check_button, single_button, number_interval;
    LinearLayout interval_linear_layout, single_linear_layout;
    TextInputLayout single_number_layout;
    boolean isPrime = false;
    ArrayList<Integer> primeNumbers;
    int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_number);

        answer = findViewById(R.id.answer);
        number = findViewById(R.id.single_number);
        first_number = findViewById(R.id.first_number);
        second_number = findViewById(R.id.second_number);
        interval_check_button = findViewById(R.id.interval_checkButton);
        single_check_button = findViewById(R.id.single_checkButton);
        single_button = findViewById(R.id.singleNumber);
        number_interval = findViewById(R.id.NumberInterval);
        interval_linear_layout = findViewById(R.id.interval_linear);
        single_linear_layout = findViewById(R.id.single_linear_layout);
        single_number_layout = findViewById(R.id.single_numberLayout);

        primeNumbers = new ArrayList<>();

        single_button.setOnClickListener(view -> {

            single_button.setBackgroundResource(R.drawable.custom_button_clicked);
            number_interval.setBackgroundResource(R.drawable.custom_button);
            single_linear_layout.setVisibility(View.VISIBLE);
            interval_linear_layout.setVisibility(View.INVISIBLE);
        });

        number_interval.setOnClickListener(view -> {

            single_button.setBackgroundResource(R.drawable.custom_button);
            number_interval.setBackgroundResource(R.drawable.custom_button_clicked);
            interval_linear_layout.setVisibility(View.VISIBLE);
            single_linear_layout.setVisibility(View.INVISIBLE);
        });

        single_check_button.setOnClickListener(view -> {
            String strNumber = number.getText().toString().trim();
            if (strNumber.isEmpty()) {
                number.setError("Please enter number");
            } else {
                checkSinglePrime(Integer.parseInt(number.getText().toString()));
                if (!isPrime) {
                    answer.setText(number.getText().toString() + " is a prime number");
                } else {
                    answer.setText(number.getText().toString() + " is not a prime number");
                }
            }
        });

        interval_check_button.setOnClickListener(view -> {
            primeNumbers.clear();
            isPrime = false;
            String firstNumber = first_number.getText().toString();
            String secondNumber = second_number.getText().toString();

            if (firstNumber.isEmpty() || secondNumber.isEmpty()) {
                Toast.makeText(this, "Both numbers are required.", Toast.LENGTH_SHORT).show();
            } else {

                int firstInt = Integer.parseInt(firstNumber);
                int secondInt = Integer.parseInt(secondNumber);


                if (firstInt > secondInt) {
                    first_number.setError("First number must be smaller than second number");
                }

                for (int i = firstInt; i <= secondInt; i++) {
                    isPrime = false;
                    for (j = 2; j < i; j++) {
                        if (i % j == 0) {
                            isPrime = true;
                        }
                    }
                    if (!isPrime) {
                        System.out.println("I " + i);
                        primeNumbers.add(i);
                        answer.setText(String.valueOf(primeNumbers));
                    }

                }
            }
        });

    }

    boolean checkSinglePrime(int n) {
        if (n == 0 || n == 1) {
            return true;
        } else {
            for (int i = 2; i < n; i++) {
                if (n % i == 0) {
                    isPrime = true;
                }
            }

        }
        return true;
    }
}



