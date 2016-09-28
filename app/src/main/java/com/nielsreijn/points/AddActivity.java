package com.nielsreijn.points;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText etTeacher, etSubject, etGrade;
    Button button;
    private DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dataSource = new DataSource(this);

        etTeacher = (EditText) findViewById(R.id.etTeacher);
        etSubject = (EditText) findViewById(R.id.etSubject);
        etGrade = (EditText) findViewById(R.id.etGrade);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long pointId = dataSource.createPoint(etTeacher.getText().toString(),
                        etSubject.getText().toString(),etGrade.getText().toString());

                Intent resultIntent = new Intent();
                resultIntent.putExtra(MainActivity.EXTRA_POINT_ID, pointId);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });


    }


}
