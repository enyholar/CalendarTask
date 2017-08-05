package com.gideondev.datedisable;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.roomorama.caldroid.CaldroidFragment;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity
    extends AppCompatActivity {

    private CaldroidFragment caldroidFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        caldroidFragment = new CaldroidFragment();

        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt( CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY    );
        caldroidFragment.setArguments(args);

        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();

        ArrayList<Date> enableDates = new ArrayList<Date>();

        try {
            enableDates.add(getDate("2017-08-01"));
            enableDates.add(getDate("2017-08-02"));
            enableDates.add(getDate("2017-08-03"));
            //for (Date dat : enableDates){
            //    dat.compareTo()
            //}
            //for (int i=0; i<enableDates.size(); i++){
            //    enableDates.get(i).getDate();
            //
            //}
        } catch (ParseException e) {
            e.printStackTrace();
        }
        


        final Button customizeButton = (Button) findViewById(R.id.customize_button);
        // Customize the calendar
        customizeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Set disabled dates
                try {
                    ArrayList<Date> disabledDates = new ArrayList<Date>();

                    disabledDates.add(getDate("2017-08-01"));
                    disabledDates.add(getDate("2017-08-02"));
                    disabledDates.add(getDate("2017-08-03"));

                    // Customize
                    caldroidFragment.setDisableDates(disabledDates);


                    // Refresh
                    caldroidFragment.refreshView();

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    private Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();

        // month start at 1. Need to minus 1 to get javaMonth
        calendar.set(year, month - 1, day);

        return calendar.getTime();
    }

    private Date getDate(String strDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(strDate);

        Calendar calendar = Calendar.getInstance();
        calendar.clear();

        calendar.setTime(date);

        return calendar.getTime();
    }
}
