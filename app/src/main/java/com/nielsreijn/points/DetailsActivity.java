package com.nielsreijn.points;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private DataSource datasource;
    private TextView tvTeacher, tvSubject, tvGrade;
    private bPoint bPoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvTeacher = (TextView) findViewById(R.id.tvTeacher);
        tvSubject = (TextView) findViewById(R.id.tvSubject);
        tvGrade = (TextView) findViewById(R.id.tvGrade);

        datasource = new DataSource(this);

        long pointId = getIntent().getLongExtra(MainActivity.EXTRA_POINT_ID, -1);
        bPoint = datasource.getbPoint(pointId);

        tvTeacher.setText(bPoint.getTeacher());
        tvSubject.setText(bPoint.getSubject());
        tvGrade.setText(bPoint.getGrade());
    }
}
