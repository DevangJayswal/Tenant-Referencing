package com.example.devang.tenantreferencing.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.devang.tenantreferencing.R;
import com.example.devang.tenantreferencing.database.AddEnquiryDAO;
import com.example.devang.tenantreferencing.database.CountryDAO;
import com.example.devang.tenantreferencing.model.Enquiry;

import java.util.Calendar;

public class AddEnquiryActivity extends AppCompatActivity {

    static final int dialogID = 0;
    int yearX, monthX, dayX;
    AddEnquiryDAO addEnquiryDAO;
    //CountryDAO countryDAO;

    EditText editNumberOfTenants;
    EditText editAddress1;
    EditText editAddress2;
    EditText editTown;
    EditText editPostcode;
//    Spinner spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
//    Spinner spinnerProduct = (Spinner) findViewById(R.id.spinnerProduct);
//    Spinner spinnerTenancyTerm = (Spinner) findViewById(R.id.spinnerTenancyTerm);


    Button btnDate;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_enquiry);

        /* creating database instance */
        addEnquiryDAO = new AddEnquiryDAO(this);
        /* creating database instance end */

        /*inserting country */
//        countryDAO = new CountryDAO(this);
//        countryDAO.insertCountry("India");
//        countryDAO.insertCountry("USA");


        /*inserting country end */



        /* Date Picker */
        final Calendar calendar = Calendar.getInstance();
        yearX = calendar.get(Calendar.YEAR);
        monthX = (calendar.get(Calendar.MONTH) + 1);
        dayX = calendar.get(Calendar.DAY_OF_MONTH);
        showTenancyStartDateDialog();
        /* Date Picker Ends */

        /* On SUBMIT, data insertion */

        addData();

        /* On SUBMIT, data insertion ends */

    }

    public void showTenancyStartDateDialog() {
        btnDate = (Button) findViewById(R.id.buttonTenancyStartDate);
        btnDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(dialogID);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == dialogID)
            return new DatePickerDialog(this, dPickerListener, yearX, monthX, dayX);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            yearX = year;
            monthX = monthOfYear + 1;
            dayX = dayOfMonth;
            Toast.makeText(AddEnquiryActivity.this, yearX + "/" + monthX + "/" + dayX, Toast.LENGTH_LONG).show();
        }
    };

    public void addData() {
        btnSubmit = (Button) findViewById(R.id.buttonSubmit);
        btnSubmit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        editNumberOfTenants = (EditText) findViewById(R.id.editTextNumberOfTenants);
                        editAddress1 = (EditText) findViewById(R.id.editTextAddress1);
                        editAddress2 = (EditText) findViewById(R.id.editTextAddress2);
                        editTown = (EditText) findViewById(R.id.editTextTown);
                        editPostcode = (EditText) findViewById(R.id.editTextPostcode);
                        String date = yearX + "-" + monthX  + "-" + dayX;
                        Enquiry enquiry;
                        try {
                            enquiry = addEnquiryDAO.insertEnquiry(
                                    Long.parseLong(editNumberOfTenants.getText().toString()),
                                    editAddress1.getText().toString(),
                                    editAddress2.getText().toString(),
                                    editTown.getText().toString(),
                                    editPostcode.getText().toString(),
                                    1,
                                    date,
                                    1,
                                    1
                            );
                            Toast.makeText(AddEnquiryActivity.this, "Data Inserted, id = " + enquiry.getmId() +  "", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(AddEnquiryActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
//                        if (isInserted == true)
//                            Toast.makeText(AddEnquiryActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(AddEnquiryActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
}


