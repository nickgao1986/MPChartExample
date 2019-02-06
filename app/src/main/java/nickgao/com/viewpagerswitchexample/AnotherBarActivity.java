
package nickgao.com.viewpagerswitchexample;

import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.StackedValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

import nickgao.com.viewpagerswitchexample.data.DemoBase;

public class AnotherBarActivity extends DemoBase {

    private BarChart chart;
    private static XAxis xLabels;
    private static YAxis leftAxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_barchart);

        setTitle("AnotherBarActivity11");

        chart = (BarChart)findViewById(R.id.chart1);

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        xLabels = chart.getXAxis();
        xLabels.setPosition(XAxisPosition.BOTTOM);
        xLabels.setDrawGridLines(false);

        leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);       //y轴的数值显示在外侧
        leftAxis.setAxisMinimum(0f); ////为这个轴设置一个自定义的最小值。如果设置,这个值不会自动根据所提供的数据计算
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setDrawGridLines(false);

        // add a nice and smooth animation
        //chart.animateY(1500);

        initData();
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        //l.setLayout(true);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);
        l.setYEntrySpace(0.1F);
        l.setYOffset(5);

        //chart.getLegend().setEnabled(false);
        chart.setScaleYEnabled(false);
        chart.setScaleXEnabled(false);
    }


    private void initData() {
        ArrayList<BarEntry> values = new ArrayList<>();
        final ArrayList<String> xLabelValue = new ArrayList<>();

        for(int i=0;i<7;i++) {
            xLabelValue.add((i+1)+"月份");
        }


        for(int i=0;i<7;i++) {
            float[] val = new float[2];
            if(i == 0) {
                val[1] = 2000;
                val[0] = 1000;
            }else if(i == 1) {
                val[1] = 1500;
                val[0] = 500;
            }else if(i == 2) {
                val[1] = 3000;
                val[0] = 2000;
            }else if(i == 3) {
                val[1] = 700;
                val[0] = 300;
            }else if(i == 4) {
                val[1] = 5100;
                val[0] = 900;
            }else if(i == 5) {
                val[1] = 1300;
                val[0] = 200;
            }
            values.add(new BarEntry(i, val));
        }
        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "");
            set1.setColors(getColors());
            set1.setDrawValues(false);
            set1.setStackLabels(new String[]{"钢销售额", "总销售额"});

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            xLabels.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float v, AxisBase axisBase) {
                    return xLabelValue.get((int) v);
                }
            });

            //右侧Y轴自定义值
            leftAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float v, AxisBase axisBase) {
                    return (int)v+"元";
                }
            });

            BarData data = new BarData(dataSets);
            data.setValueFormatter(new StackedValueFormatter(true, "", 0) {

            });
            chart.setData(data);
            chart.setFitBars(true);
        }
        chart.getData().setHighlightEnabled(true);

        chart.setFitBars(true);
        chart.invalidate();
    }

    private static int[] getColors() {
        // have as many colors as stack-values per entry
        int[] colors = new int[2];
        System.arraycopy(NewColorTemplate.TITILE_COLORS, 0, colors, 0, 2);

        return colors;
    }

    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "AnotherBarActivity");
    }

}
