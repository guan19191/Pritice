package sgcf.zz.com.pritice.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import sgcf.zz.com.pritice.R;

/**
 * 折线图
 */
public class LineChart extends View {

    private Paint xPaint;
    private Paint yPaint;
    private Paint linePaint;
    private int xLineColor;
    private int yLineColor;
    private int textSize;
    private int bgColor;
    private int xPointSpace;
    private int yPointSpace;
    private Paint textPaint;
    private int lineStrokeWidth = 5;
    private int lineColor;
    private int width;
    private int height;

    public LineChart(Context context) {
        this(context, null);
    }

    public LineChart(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
        initPaint();
    }

    //初始化 获取参数
    void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LineChart, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.LineChart_xLineColor:
                    xLineColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.LineChart_yLineColor:
                    yLineColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.LineChart_textSize:
                    textSize = (int) typedArray.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, textSize, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.LineChart_yPointSpace:
                    yPointSpace = (int) typedArray.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, yPointSpace, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.LineChart_xPointSpace:
                    xPointSpace = (int) typedArray.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, xPointSpace, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.LineChart_bgColor:
                    bgColor = typedArray.getColor(attr, bgColor);
                    break;
            }
        }
        typedArray.recycle();
    }

    void initPaint() {
        xPaint = new Paint();
        xPaint.setAntiAlias(true);
        xPaint.setStrokeWidth(lineStrokeWidth);
        xPaint.setColor(xLineColor);


        yPaint = new Paint();
        yPaint.setAntiAlias(true);
        yPaint.setStrokeWidth(lineStrokeWidth);
        yPaint.setColor(xLineColor);


        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(lineColor);
        linePaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(textSize);
        textPaint.setStyle(Paint.Style.STROKE);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            //这里需要确定几个基本点，只有确定了xy轴原点坐标，第一个点的X坐标值及其最大最小值
            width = getWidth();
            height = getHeight();

        }
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
