package com.feby.asyst.learnsession;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.feby.asyst.learnsession.utility.SessionUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etNama, etAddress;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;
    Spinner spEducation;
    Button btnSubmit;

    SessionUtil sessionUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionUtil = new SessionUtil(this);

        etNama = findViewById(R.id.name_edittext);
        etAddress = findViewById(R.id.address_edittext);
        rgGender = findViewById(R.id.gender_radiogroup);
        rbMale = findViewById(R.id.male_radiobutton);
        rbFemale = findViewById(R.id.female_radiobutton);
        spEducation = findViewById(R.id.education_spinner);
        btnSubmit = findViewById(R.id.submit_button);

        btnSubmit.setOnClickListener(this);

        String name = sessionUtil.loadName();
        etNama.setText(name);

        String address = sessionUtil.loadAddress();
        etAddress.setText(address);

        String gender = sessionUtil.loadGender();
        if (gender.equalsIgnoreCase("male")){
            rbMale.setChecked(true);
        }else if (gender.equalsIgnoreCase("female")){
            rbFemale.setChecked(true);
        }

        String edu = sessionUtil.loadEducation();
            for (int i = 0; i <spEducation.getAdapter().getCount() ; i++) {

                if (spEducation.getAdapter().getItem(i).toString().equalsIgnoreCase(edu)){
                    spEducation.setSelection(i);
                    break;
                }
            }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_button:

                    if (TextUtils.isEmpty(etNama.getText().toString())){
                        etNama.setError("Required");
                    }else if (TextUtils.isEmpty(etAddress.getText().toString())){
                        etAddress.setError("Required");
                    }else {
                        sessionUtil.saveName(etNama.getText().toString());
                        sessionUtil.saveAddress(etAddress.getText().toString());
                        String edu = spEducation.getSelectedItem().toString();
                        String gender = "male";
                        if (rbMale.isChecked()){
                            gender = "male";
                        } else if(rbFemale.isChecked()){
                            gender = "female";
                        }

                        sessionUtil.saveGender(gender);
                        sessionUtil.saveEducation(edu);
                        Toast.makeText(getApplicationContext(), "DATA TERSIMPAN", Toast.LENGTH_SHORT).show();
                    }

                    break;
        }
    }
}
