package com.example.akashtechnolabinternship.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.akashtechnolabinternship.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreditcardActivity extends AppCompatActivity {

    TextInputLayout tnl_CardNumber,tnl_Expiry,tnl_SecurityCode,tnl_fName,tnl_sName;
    TextInputEditText txtCardNumber,txtExpiry,txtSecurityCode,txtFName,txtSName;
    TextView txtCardError,txtExpiryError,txtSecurityError,txtFNameError, txtSNameError;
    Button btnSubmit,btnReset;
    boolean cardSupported = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditcard);

        getSupportActionBar().setTitle("Credit card checker");

        tnl_CardNumber = findViewById(R.id.tnl_CardNumber);
        tnl_Expiry = findViewById(R.id.tnl_Expiry);
        tnl_SecurityCode = findViewById(R.id.tnl_SecurityCode);
        tnl_fName = findViewById(R.id.tnl_fName);
        tnl_sName = findViewById(R.id.tnl_sName);
        txtCardNumber = findViewById(R.id.txtCardNumber);
        txtExpiry = findViewById(R.id.txtExpiry);
        txtSecurityCode = findViewById(R.id.txtSecurityCode);
        txtFName = findViewById(R.id.txtFName);
        txtSName = findViewById(R.id.txtSName);
        txtCardError = findViewById(R.id.txtCardError);
        txtExpiryError = findViewById(R.id.txtExpiryError);
        txtSecurityError = findViewById(R.id.txtSecurityError);
        txtFNameError = findViewById(R.id.txtFNameError);
        txtSNameError = findViewById(R.id.txtSNameError);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnReset = findViewById(R.id.btnReset);

        btnSubmit.setOnClickListener(view1 -> {
            String cardNumber = txtCardNumber.getText().toString().trim();
            String expiryDate = txtExpiry.getText().toString().trim();
            String securityCode = txtSecurityCode.getText().toString().trim();
            String firstName = txtFName.getText().toString().trim();
            String lastName = txtSName.getText().toString().trim();

            setDefault();

            boolean validator = validatorClass(cardNumber,expiryDate,securityCode,firstName,lastName);

            if (validator){
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreditcardActivity.this);
                alertDialog.setMessage("Card Verified Successfully");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });
                alertDialog.show();
            }

        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCardNumber.setText("");
                txtExpiry.setText("");
                txtSecurityCode.setText("");
                txtFName.setText("");
                txtSName.setText("");
                txtCardNumber.requestFocus();
                setDefault();
            }
        });
    }

    private void setDefault() {
        txtCardError.setVisibility(View.GONE);
        txtSecurityError.setVisibility(View.GONE);
        txtExpiryError.setVisibility(View.GONE);
        txtFNameError.setVisibility(View.GONE);
        txtSNameError.setVisibility(View.GONE);
        tnl_CardNumber.setBoxStrokeColor(getResources().getColor(R.color.dark_yellow));
        tnl_Expiry.setBoxStrokeColor(getResources().getColor(R.color.dark_yellow));
        tnl_SecurityCode.setBoxStrokeColor(getResources().getColor(R.color.dark_yellow));
        tnl_fName.setBoxStrokeColor(getResources().getColor(R.color.dark_yellow));
        tnl_sName.setBoxStrokeColor(getResources().getColor(R.color.dark_yellow));
    }

    private boolean validatorClass(String cardNumber, String expiryDate, String securityCode, String firstName, String lastName) {

        if (cardNumber.isEmpty()){
            txtCardError.setVisibility(View.VISIBLE);
            txtCardError.setText("Enter card number");
            tnl_CardNumber.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            txtCardNumber.requestFocus();
            return false;
        }
        else if (expiryDate.isEmpty()){
            txtExpiryError.setVisibility(View.VISIBLE);
            txtExpiryError.setText("Enter expiry Dtae");
            tnl_Expiry.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            txtExpiry.requestFocus();
            return false;
        }
        else if (securityCode.isEmpty()){
            txtSecurityError.setVisibility(View.VISIBLE);
            txtSecurityError.setText("Enter Security code");
            tnl_SecurityCode.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            txtSecurityCode.requestFocus();
            return false;
        }
        else if (firstName.isEmpty()){
            txtFNameError.setVisibility(View.VISIBLE);
            txtFNameError.setText("Enter First Name");
            tnl_fName.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            txtFName.requestFocus();
            return false;
        }
        else if (lastName.isEmpty()) {
            txtSNameError.setVisibility(View.VISIBLE);
            txtSNameError.setText("Enter Last Name");
            tnl_sName.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            txtSName.requestFocus();
            return false;
        }

        if (!cardSupported){
            txtCardError.setVisibility(View.VISIBLE);
            txtCardError.setText("Card not supported");
            tnl_CardNumber.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            txtCardNumber.requestFocus();
            return false;
        }
        else if (!LuhnCheck(cardNumber)){
            txtCardError.setVisibility(View.VISIBLE);
            txtCardError.setText("Card digit error");
            tnl_CardNumber.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            txtCardNumber.requestFocus();
            return false;
        }
        else if (!expiryDate.matches("\\d{2}/\\d{2}")){
            txtExpiryError.setVisibility(View.VISIBLE);
            txtExpiryError.setText("Expiry format error");
            tnl_Expiry.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            txtExpiry.requestFocus();
            return false;
        }
        else if (!firstName.matches("[A-Za-z]+$")){
            txtFNameError.setVisibility(View.VISIBLE);
            txtFNameError.setText("First name error");
            tnl_fName.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            txtFName.requestFocus();
            return false;
        }
        else if (!lastName.matches("[A-Za-z]+$")){
            txtSNameError.setVisibility(View.VISIBLE);
            txtSNameError.setText("Last Name error");
            tnl_sName.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            txtSName.requestFocus();
            return false;
        }

        return true;
    }

    private boolean LuhnCheck(String cardNumber) {
        int cardLength = cardNumber.length();
        int nSum = 0;
        boolean isSecond = false;

        for (int i = cardLength -1; i >= 0; i--){
            int d = cardNumber.charAt(i) - '0';

            if (isSecond){
                d = d * 2;
            }

            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        return nSum % 10 == 0;
    }
}