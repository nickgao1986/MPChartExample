
package nickgao.com.viewpagerswitchexample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
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
import com.github.mikephil.charting.interfaces.datasets.IDataSet;

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

        setTitle("AnotherBarActivity");

        chart = (BarChart)findViewById(R.id.chart1);
        xLabels = chart.getXAxis();
        xLabels.setYOffset(6f);
        xLabels.setXOffset(30f);
        xLabels.setAxisLineColor(Color.parseColor("#DDDDDD"));
        xLabels.setAxisLineWidth(1);
        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);
        xLabels.setLabelCount(5, false);
        xLabels.setTextColor(Color.parseColor("#FF666666"));

        leftAxis = chart.getAxisLeft();
        leftAxis.setAxisLineColor(Color.parseColor("#DDDDDD"));
        leftAxis.setTextColor(Color.parseColor("#FF666666"));
        leftAxis.setAxisLineWidth(1);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setLabelCount(5, false);
        leftAxis.setAxisMaximum(43);

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        chart.getAxisLeft().setDrawGridLines(false);


        // add a nice and smooth animation
        //chart.animateY(1500);

//        chart.getLegend().setEnabled(false);
//        chart.setScaleYEnabled(false);
//        chart.setScaleXEnabled(false);
        initData();

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        //l.setLayout(true);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);
        l.setYEntrySpace(0.1F);
        l.setYOffset(5);
        float scale = 1.0f;
        Matrix m = new Matrix();

        m.postScale(scale, 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的3倍
        chart.getViewPortHandler().refresh(m, chart, false);//将图表动画显示之前进行缩放
        chart.setScaleYEnabled(false);
        chart.setScaleXEnabled(false);
    }

    private ArrayList<SalesBean> contructBeans() {
//        SalesBean salesBean1 = new SalesBean(3000-1000,1000);
//        SalesBean salesBean2 = new SalesBean(4000-500,500);
//        SalesBean salesBean3 = new SalesBean(2000-400,400);
//        SalesBean salesBean4 = new SalesBean(6000-800,800);
//        SalesBean salesBean5 = new SalesBean(7000-900,900);
//        SalesBean salesBean6 = new SalesBean(5000-300,300);

        SalesBean salesBean1 = new SalesBean(3000,1000);
        SalesBean salesBean2 = new SalesBean(4000,500);
        SalesBean salesBean3 = new SalesBean(2000,400);
        SalesBean salesBean4 = new SalesBean(6000,800);
        SalesBean salesBean5 = new SalesBean(7000,900);
        SalesBean salesBean6 = new SalesBean(5000,300);
        ArrayList<SalesBean> yValues = new ArrayList<>();
        yValues.add(salesBean1);
        yValues.add(salesBean2);
        yValues.add(salesBean3);
        yValues.add(salesBean4);
        yValues.add(salesBean5);
        yValues.add(salesBean6);
        return yValues;
    }

    private void initData() {

        ArrayList<BarEntry> values = new ArrayList<>();
        final ArrayList<String> xLabelValue = new ArrayList<>();

        ArrayList<SalesBean> yValues = contructBeans();

        for(int i=0;i<7;i++) {
            xLabelValue.add((i+1)+"月份");
        }

        for (int i = 0; i < yValues.size(); i++) {
            float val1 = yValues.get(i).steelSalesPrice;
            float val2 = yValues.get(i).totalPrice;

            values.add(new BarEntry(
                    i,
                    new float[]{val1, val2},
                    getResources().getDrawable(R.drawable.default_icon)));
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
            set1.setDrawIcons(false);
            set1.setColors(getColors());
            set1.setStackLabels(new String[]{"经期周期", "月经长度"});

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            //X轴自定义值
//            xLabels.setValueFormatter(new ValueFormatter() {
//                @Override
//                public String getAxisLabel(float value, AxisBase axis, int i) {
//                    return al.get((int) value);
//                }
//            });

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
                    return String.valueOf(v);
                }
            });
            BarData data = new BarData(dataSets);

            data.setBarWidth(0.66f);
            data.setHighlightEnabled(false);
            data.setValueFormatter(new StackedValueFormatter(true, "", 0) {

            });
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(9);
            chart.setData(data);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar, menu);
        menu.removeItem(R.id.actionToggleIcons);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.viewGithub: {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/AnotherBarActivity.java"));
                startActivity(i);
                break;
            }
            case R.id.actionToggleValues: {

                for (IDataSet set : chart.getData().getDataSets())
                    set.setDrawValues(!set.isDrawValuesEnabled());

                chart.invalidate();
                break;
            }
            /*
            case R.id.actionToggleIcons: { break; }
             */
            case R.id.actionToggleHighlight: {

                if(chart.getData() != null) {
                    chart.getData().setHighlightEnabled(!chart.getData().isHighlightEnabled());
                    chart.invalidate();
                }
                break;
            }
            case R.id.actionTogglePinch: {
                if (chart.isPinchZoomEnabled())
                    chart.setPinchZoom(false);
                else
                    chart.setPinchZoom(true);

                chart.invalidate();
                break;
            }
            case R.id.actionToggleAutoScaleMinMax: {
                chart.setAutoScaleMinMaxEnabled(!chart.isAutoScaleMinMaxEnabled());
                chart.notifyDataSetChanged();
                break;
            }
            case R.id.actionToggleBarBorders: {
                for (IBarDataSet set : chart.getData().getDataSets())
                    ((BarDataSet)set).setBarBorderWidth(set.getBarBorderWidth() == 1.f ? 0.f : 1.f);

                chart.invalidate();
                break;
            }
            case R.id.animateX: {
                chart.animateX(2000);
                break;
            }
            case R.id.animateY: {
                chart.animateY(2000);
                break;
            }
            case R.id.animateXY: {

                chart.animateXY(2000, 2000);
                break;
            }
            case R.id.actionSave: {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    saveToGallery();
                } else {
                    requestStoragePermission(chart);
                }
                break;
            }
        }
        return true;
    }

    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "AnotherBarActivity");
    }

}
