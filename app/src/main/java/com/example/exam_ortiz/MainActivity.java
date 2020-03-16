//*******************
// Michael Ortiz
// Exam App
// 11/21/2019
//*******************

package com.example.exam_ortiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2, et3, et4, et5, et6;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        et5 = (EditText) findViewById(R.id.et5);
        et6 = (EditText) findViewById(R.id.et6);

        btn1 = (Button) findViewById(R.id.btn1);
    }

    public void calculate(View view) {

        // Checks each EditText to make sure a value was input into the field. If not,
        // sets a warning in the box to notify the user that the field cannot be empty.
        while (TextUtils.isEmpty(et1.getText()) == true || TextUtils.isEmpty(et2.getText()) == true ||
                TextUtils.isEmpty(et3.getText()) == true || TextUtils.isEmpty(et4.getText()) == true ||
                TextUtils.isEmpty(et5.getText()) == true || TextUtils.isEmpty(et6.getText()) == true){
            if(TextUtils.isEmpty(et1.getText()) == true) {
                et1.setError("This field cannot be empty!");
            }
            if(TextUtils.isEmpty(et2.getText()) == true){
                et2.setError("This field cannot be empty!");
            }
            if(TextUtils.isEmpty(et3.getText()) == true){
                et3.setError("This field cannot be empty!");
            }
            if(TextUtils.isEmpty(et4.getText()) == true){
                et4.setError("This field cannot be empty!");
            }
            if(TextUtils.isEmpty(et5.getText()) == true){
                et5.setError("This field cannot be empty!");
            }
            if(TextUtils.isEmpty(et6.getText()) == true){
                et6.setError("This field cannot be empty!");
            }
            return;
        }

        // Converts the values entered in the EditText fields to int.
        int totStdts = Integer.parseInt(et1.getText().toString());
        int aStdts = Integer.parseInt(et2.getText().toString());
        int bStdts = Integer.parseInt(et3.getText().toString());
        int cStdts = Integer.parseInt(et4.getText().toString());
        int dStdts = Integer.parseInt(et5.getText().toString());
        int fStdts = Integer.parseInt(et6.getText().toString());

        // Tests to see if the sum of students with grades A - F is equal to the total number
        // of students.
        if(aStdts + bStdts + cStdts + dStdts + fStdts != totStdts)
        {
            Toast.makeText(MainActivity.this,"The sum of your grade counts does not equal total students! Please reenter your numbers.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        // Creates a SharedPrefernce to store the values entered to be used to create the bar graph
        SharedPreferences sharedPreferences = getSharedPreferences("GradeData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("totalStudents", totStdts);
        editor.putInt("aStudents", aStdts);
        editor.putInt("bStudents", bStdts);
        editor.putInt("cStudents", cStdts);
        editor.putInt("dStudents", dStdts);
        editor.putInt("fStudents", fStdts);
        editor.commit();

        // Moves to the next activity that displays the bar graph
        Intent i = new Intent(view.getContext(), ChartActivity.class);
        startActivity(i);
    }
}
