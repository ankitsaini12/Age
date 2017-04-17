package com.ankit.agecaculatorapp;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    private TextView tvDateFirst, tvDateSecond, tvDate;
    private Button btnCalculate;
    private DatePickerDialog datePickerDialogFirst, datePickerDialogSecond;
    private String selectedDateFirst, selectedDateSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setUp();
        setListener();
    }

    private void init() {
        tvDateFirst = (TextView) findViewById(R.id.tvDateFirst);
        tvDateSecond = (TextView) findViewById(R.id.tvDateSecond);
        tvDate = (TextView) findViewById(R.id.tvDate);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
    }

    private void setUp() {
        final Calendar calendar = Calendar.getInstance();
        datePickerDialogFirst = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                Date selectedDate = calendar.getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
                selectedDateFirst = simpleDateFormat.format(selectedDate);
                tvDateFirst.setText(selectedDateFirst + "");
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialogSecond = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                Date selectedDate = calendar.getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
                selectedDateSecond = simpleDateFormat.format(selectedDate);
                tvDateSecond.setText(selectedDateSecond + "");
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void setListener() {
        tvDateFirst.setOnTouchListener(this);
        tvDateSecond.setOnTouchListener(this);
        btnCalculate.setOnClickListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;

        switch (v.getId()) {
            case R.id.tvDateFirst: {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (event.getRawX() >= (tvDateFirst.getRight() - tvDateFirst.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        datePickerDialogFirst.show();
                        return true;
                    }
                }
            }
            case R.id.tvDateSecond: {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (event.getRawX() >= (tvDateSecond.getRight() - tvDateSecond.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        datePickerDialogSecond.show();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCalculate: {
                int years,months,days;
                try {
                    Date date1;
                    Date date2;
                    SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");
                    //Setting dates
                    date1 = dates.parse(selectedDateFirst);
                    date2 = dates.parse(selectedDateSecond);
                    if (date1.getTime()>date2.getTime()){
                        years=date1.getYear()-date2.getYear();
                        months=date1.getMonth()-date2.getMonth();
                        days=date1.getDay()-date2.getDay();
                    }else {
                        years=date2.getYear()-date1.getYear();
                        months=date2.getMonth()-date1.getMonth();
                        days=date2.getDay()-date1.getDay();
                    }
                    tvDate.setText(years +" Years "+ months+" Months "+days +" Days");
                } catch (Exception exception) {
                    Log.e("DIDN'T WORK", "exception " + exception);
                }
                break;
            }
        }
    }
}
