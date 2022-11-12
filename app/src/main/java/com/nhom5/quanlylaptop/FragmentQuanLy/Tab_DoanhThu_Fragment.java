package com.nhom5.quanlylaptop.FragmentQuanLy;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class Tab_DoanhThu_Fragment extends Fragment implements OnChartValueSelectedListener {

    PieChart mPieChart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_doanh_thu, container, false);
        mPieChart = view.findViewById(R.id.pieChart);
        setUpPieChart();
        return view;
    }

    private void setUpPieChart(){
        Description desc = new Description();
        desc.setText("Biểu đồ thống kê");
        mPieChart.setRotationEnabled(true);
        mPieChart.setDescription(desc);
        mPieChart.getDescription().setPosition(750,840);
        mPieChart.getDescription().setTextSize(23);
        mPieChart.setHoleRadius(15f);
        mPieChart.setTransparentCircleAlpha(0);
        mPieChart.setDrawEntryLabels(true);
        addDataSet(mPieChart);
        mPieChart.setOnChartValueSelectedListener(this);
    }

    private void addDataSet(PieChart pieChart){
        float[] yData = {650, 350};
        String[] xData = { "Khoản thu" , "Khoản chi" };
        ArrayList<PieEntry> entrys = new ArrayList<>();
        for (int i = 0; i< yData.length; i++){
            entrys.add(new PieEntry(yData[i], xData[i]));
        }
        PieDataSet pieDataSet = new PieDataSet(entrys,"");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(23);
        pieDataSet.setValueTextColor(Color.WHITE);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#ef5261"));
        colors.add(Color.parseColor("#f6c863"));
        pieDataSet.setColors(colors);
        Legend legend = pieChart.getLegend();
        legend.setTextSize(20);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setXEntrySpace(17);
        legend.setDrawInside(false);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}