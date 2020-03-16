//*******************
// Michael Ortiz
// Exam App
// 11/21/2019
//*******************

package com.example.exam_ortiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {

    public static final int DEFAULT = 0;
    TextView tv7, tv8, tv9 ,tv10, tv11;
    BarChart mBarChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);
        tv9 = findViewById(R.id.tv9);
        tv10 = findViewById(R.id.tv10);
        tv11 = findViewById(R.id.tv11);

        mBarChart = findViewById(R.id.bg1);

        // Retrieves the values from SharedPreferences entered in the previous activity.
        SharedPreferences sharedPreferences = getSharedPreferences("GradeData", Context.MODE_PRIVATE);
        int totalStudents = sharedPreferences.getInt("totalStudents", DEFAULT);
        int aStudents = sharedPreferences.getInt("aStudents", DEFAULT);
        int bStudents = sharedPreferences.getInt("bStudents", DEFAULT);
        int cStudents = sharedPreferences.getInt("cStudents", DEFAULT);
        int dStudents = sharedPreferences.getInt("dStudents", DEFAULT);
        int fStudents = sharedPreferences.getInt("fStudents", DEFAULT);

        // Calculates the percentage of students who earned each grade.
        double aPct = ((double) aStudents / (double) totalStudents) * 100;
        double bPct = ((double) bStudents / (double) totalStudents) * 100;
        double cPct = ((double) cStudents / (double) totalStudents) * 100;
        double dPct = ((double) dStudents / (double) totalStudents) * 100;
        double fPct = ((double) fStudents / (double) totalStudents) * 100;

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        // Creates an ArrayList for the Y-Axis containing the number of students in
        // each grade A - F for the bar graph.
        BarDataSet barDataSet = new BarDataSet(barEntries, "# of Students");
        barDataSet.addEntry(new BarEntry(aStudents,0));
        barDataSet.addEntry(new BarEntry(bStudents,1));
        barDataSet.addEntry(new BarEntry(cStudents,2));
        barDataSet.addEntry(new BarEntry(dStudents,3));
        barDataSet.addEntry(new BarEntry(fStudents,4));
        barDataSet.setColors(new int[] {Color.BLUE, Color.CYAN, Color.GREEN, Color.YELLOW, Color.RED});

        // Creates an ArrayList containing the labels A - F for the X-Axis of the bar graph
        ArrayList<String> grades = new ArrayList<>();
        grades.add("A");
        grades.add("B");
        grades.add("C");
        grades.add("D");
        grades.add("F");

        // Sets the calculated percentages to the each EditText field corresponding to each letter
        // grade underneath the bar graph.
        tv7.setText(Double.toString(aPct) + "%");
        tv8.setText(Double.toString(bPct) + "%");
        tv9.setText(Double.toString(cPct) + "%");
        tv10.setText(Double.toString(dPct) + "%");
        tv11.setText(Double.toString(fPct) + "%");

        BarData gradeData = new BarData(grades, barDataSet);
        mBarChart.setData(gradeData);



    }
}
