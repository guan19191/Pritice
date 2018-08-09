package sgcf.zz.com.pritice.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import sgcf.zz.com.pritice.R;
import sgcf.zz.com.pritice.util.MyStatusBarUtil;
import sgcf.zz.com.pritice.widget.LineView;
import sgcf.zz.com.pritice.widget.MyTextView;

public class TestActivity extends AppCompatActivity implements  View.OnClickListener {

    private static final String TAG = TestActivity.class.getSimpleName();
    private LineView lineView;
    ArrayList<Double> yList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        lineView = findViewById(R.id.line_view);

        yList = new ArrayList<Double>();
        yList.add((double) 2.103);
        yList.add(4.05);
        yList.add(6.60);
        yList.add(3.08);
        yList.add(4.32);
        yList.add(2.0);
        yList.add(5.0);

        ArrayList<String> xRawDatas = new ArrayList<String>();
        xRawDatas.add("05-19");
        xRawDatas.add("05-20");
        xRawDatas.add("05-21");
        xRawDatas.add("05-22");
        xRawDatas.add("05-23");
        xRawDatas.add("05-24");
        xRawDatas.add("05-25");
        xRawDatas.add("05-26");
        lineView.setData(yList, xRawDatas, 8, 2);
        lineView.setMstyle(LineView.Linestyle.Curve);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}
