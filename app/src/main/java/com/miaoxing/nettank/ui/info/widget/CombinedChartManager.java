package com.miaoxing.nettank.ui.info.widget;

import android.graphics.Color;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Administrator
 * @Date : 2018/10/30 0030
 */
public class CombinedChartManager {

    private CombinedChart mCombinedChart;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private XAxis xAxis;

    public CombinedChartManager(CombinedChart combinedChart) {
        this.mCombinedChart = combinedChart;
        leftAxis = mCombinedChart.getAxisLeft();
        xAxis = mCombinedChart.getXAxis();
        rightAxis = mCombinedChart.getAxisRight();
    }

    /**
     * 初始化Chart
     */
    private void initChart() {

        mCombinedChart.getDescription().setEnabled(false);

        mCombinedChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR,
                CombinedChart.DrawOrder.BAR
        });

        mCombinedChart.setBackgroundColor(Color.WHITE);
        mCombinedChart.setDrawGridBackground(false);
        mCombinedChart.setDrawBarShadow(false);
        mCombinedChart.setHighlightFullBarEnabled(false);
        // 设置是否可以触摸
        mCombinedChart.setTouchEnabled(false);
        // 是否可以拖拽
        mCombinedChart.setDragEnabled(false);
        // 是否可以缩放
        mCombinedChart.setScaleEnabled(false);
        // 隐藏右边的坐标轴
        mCombinedChart.getAxisRight().setEnabled(false);
        //显示边界
        mCombinedChart.setDrawBorders(true);
        leftAxis.setDrawGridLines(true);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setSpaceTop(20f);
        mCombinedChart.animateX(1000); // 立即执行的动画,x轴

    }

    /**
     * 设置X轴坐标值
     *
     * @param xAxisValues x轴坐标集合
     */
    public void setXAxis(final List<String> xAxisValues) {
        //设置X轴在底部
        XAxis xAxis = mCombinedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);

        xAxis.setLabelCount(xAxisValues.size() - 1,false);

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xAxisValues.get((int) value % xAxisValues.size());
            }
        });
    }

    /**
     * 得到柱状图(多条)
     *
     * @param barChartYs Y轴值
     * @param barNames   柱状图名字
     * @param barColors  柱状图颜色
     * @return
     */

    private BarData getBarData(List<List<Float>> barChartYs, List<String> barNames, List<Integer> barColors) {
        List<IBarDataSet> lists = new ArrayList<>();
        for (int i = 0; i < barChartYs.size(); i++) {
            ArrayList<BarEntry> entries = new ArrayList<>();

            for (int j = 0; j < barChartYs.get(i).size(); j++) {
                entries.add(new BarEntry(j, barChartYs.get(i).get(j)));
            }
            BarDataSet barDataSet = new BarDataSet(entries, barNames.get(i));
            barDataSet.setColor(barColors.get(i));
            barDataSet.setValueTextColor(Color.BLACK);
            barDataSet.setValueTextSize(10f);
            barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            lists.add(barDataSet);
        }
        BarData barData = new BarData(lists);

        //int amount = barChartYs.size(); //需要显示柱状图的类别 数量
        int amount = barChartYs.get(0).size();
        float groupSpace = 0.0f; //柱状图组之间的间距
        float barSpace = (1f / amount); // x4 DataSet
        float barWidth = (1f / amount * (amount-1)); // x4 DataSet
        //柱状图宽度
        //barData.setBarWidth(barWidth);
        //(起始点、柱状图组间距、柱状图之间间距)
        //barData.groupBars(0, groupSpace, barSpace);

        //以下是为了解决 柱状图 左右两边只显示了一半的问题 根据实际情况 而定
        xAxis.setAxisMinimum(-0.5f);
        xAxis.setAxisMaximum((float) (amount- 0.5));
        return barData;
    }

    /**
     * 显示混合图(柱状图+折线图)
     *
     * @param xAxisValues X轴坐标
     * @param barChartYs  柱状图Y轴值
     * @param barNames    柱状图名字
     * @param barColors   柱状图颜色
     */

    public void showCombinedChart(
            List<String> xAxisValues, List<List<Float>> barChartYs, List<String> barNames,
            List<Integer> barColors) {
        initChart();
        setXAxis(xAxisValues);

        CombinedData combinedData = new CombinedData();

        combinedData.setData(getBarData(barChartYs, barNames, barColors));
        mCombinedChart.setDrawValueAboveBar(true);
        mCombinedChart.setData(combinedData);
        mCombinedChart.invalidate();
    }

}
