package com.example.saglikd2;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAdd,btnClear;
    private EditText etTaskEntry,inputTextX;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    GraphView graphView;
    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[0]);
    MyHelper myHelper;
    SQLiteDatabase sqLiteDatabase;
    SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");


    private List<TaskModel> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Kan Şekeri Takip Çizelgesi");


        btnAdd = findViewById(R.id.btn_add);
        recyclerView = findViewById(R.id.recyclerview);
        etTaskEntry = findViewById(R.id.et_entry);
        btnClear = findViewById(R.id.btn_clear);
        inputTextX = findViewById(R.id.glukozokuma);
        graphView= findViewById(R.id.graphview);

        taskList = PrefConfig.readListFromPref(this);

        if (taskList == null)
            taskList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new TaskAdapter(getApplicationContext(), taskList);
        recyclerView.setAdapter(adapter);

        myHelper= new MyHelper(this);
        sqLiteDatabase = myHelper.getWritableDatabase();
        graphView.addSeries(series);
        series.setColor(Color.rgb(220,15,82));
        series.setThickness(12);
        series.setDrawBackground(true);
        series.setBackgroundColor(Color.argb(150,95,226,156));
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(15);
        series.setAnimated(true);
        series.setTitle("glukoz değerleri");
        graphView.getLegendRenderer().setVisible(true);
        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMaxY(300.0);
        graphView.getViewport().setMinY(60.0);
        graphView.getViewport().setScrollable(true);
        graphView.getViewport().setScrollableY(true);
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);

        // set manual x bounds to have nice steps
        //graphView.getViewport().setMinX(firs.getTime());
        //graphView.getViewport().setMaxX(getTime());
        graphView.getViewport().setXAxisBoundsManual(true);

// as we use dates as labels, the human rounding to nice readable numbers
// is not necessary
        graphView.getGridLabelRenderer().setHumanRounding(false);


        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX)
                {
                    return sdf.format(new Date((long)value));
                }else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });
        series.resetData(getDataPoint());
        exqInsert();




        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.remove(0);
                PrefConfig.writeListInPref(getApplicationContext(), taskList);
                Collections.reverse(taskList);
                adapter.setTaskModelList(taskList);

                if (!etTaskEntry.getText().equals(""))
                    etTaskEntry.setText("");

            }
        });

    }

    private String getDate() {
        Date cal = Calendar.getInstance().getTime();
        long date = cal.getTime();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
        return String.valueOf(dateFormat.format(date));
    }
    private DataPoint[] getDataPoint(){
        String[] colums ={"xValues","yvalues"};
        Cursor cursor=sqLiteDatabase.query("myTable",colums,null,null,null,null,null);
        DataPoint[] dp = new DataPoint[cursor.getCount()];

        for (int i=0; i<cursor.getCount();i++){
            cursor.moveToNext();
            dp[i] =new DataPoint(cursor.getLong(0),cursor.getInt(1));

        }
        return dp;
    }
    private void exqInsert() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskModel taskModel = new TaskModel(etTaskEntry.getText().toString(), getDate());
                taskList.add(taskModel);
                PrefConfig.writeListInPref(getApplicationContext(), taskList);
                Collections.reverse(taskList);
                adapter.setTaskModelList(taskList);

                if (!etTaskEntry.getText().equals(""))
                    etTaskEntry.setText("");

                long xValue = new Date().getTime();
                int yValue = Integer.parseInt(String.valueOf(inputTextX.getText()));
                myHelper.insertData(xValue,yValue);
                series.resetData(getDataPoint());

                graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
                    @Override
                    public String formatLabel(double value, boolean isValueX) {

                        if (isValueX)
                        {
                            return sdf.format(new Date((long)value));
                        }else {
                            return super.formatLabel(value, isValueX);
                        }
                    }
                });

            }
        });

    }

}
