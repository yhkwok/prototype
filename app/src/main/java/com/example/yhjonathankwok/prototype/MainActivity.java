package com.example.yhjonathankwok.prototype;

 import java.util.Calendar;
 import android.app.Activity;
 import android.app.DatePickerDialog;
 import android.app.TimePickerDialog;
 import android.app.Dialog;
 import android.os.Bundle;
 import android.view.View;
 import android.view.View.OnClickListener;
 import android.widget.Button;
 import android.widget.DatePicker;
 import android.widget.TimePicker;
 import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView tvDisplayDate;
    private TextView tvDisplayTime;
    //private DatePicker dpResult;
    //private TimePicker timePicker1;
    private Button btnChangeDate;
    private Button btnChangeTime;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    static final int DATE_DIALOG_ID = 999;
    static final int TIME_DIALOG_ID = 998;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setCurrentDateOnView();
        setCurrentTimeOverView();
        addListenerOnButton();

    }

    public void setCurrentTimeOverView(){
        tvDisplayTime = (TextView) findViewById(R.id.tvTime);

        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        tvDisplayTime.setText(
                new StringBuilder().append(pad(hour))
                        .append(":").append(pad(minute)));
    }
    // display current date
    public void setCurrentDateOnView() {

        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
        //dpResult = (DatePicker) findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        // set current date into datepicker
        //dpResult.init(year, month, day, null);

    }

    public void addListenerOnButton() {

        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
        btnChangeTime = (Button)findViewById(R.id.btnChangeTime);

        btnChangeDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });

        btnChangeTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, timePickerListener,
                        hour, minute, false);
        }

        return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener
            = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour,
                              int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;

            // set current time into textview
            tvDisplayTime.setText(new StringBuilder().append(pad(hour))
                    .append(":").append(pad(minute)));
        }
    };

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {
            // when dialog box is closed, below method will be called.
            public void onDateSet(DatePicker view, int selectedYear,
                                  int selectedMonth, int selectedDay) {
                year = selectedYear;
                month = selectedMonth;
                day = selectedDay;

                // set selected date into textview
                tvDisplayDate.setText(new StringBuilder().append(month + 1)
                        .append("-").append(day).append("-").append(year)
                        .append(" "));

                // set selected date into datepicker also
                //dpResult.init(year, month, day, null);
            }
    };


    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}

