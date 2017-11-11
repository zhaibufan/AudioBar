package com.example.zhai.auduiobar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static android.view.View.MeasureSpec.getMode;

/**
 * Created by Mastra on 2017/11/10.
 * <p>
 * 音频条状图
 */

public class AudioBarView extends View {

    private static final String TAG = "AudioBarView";
    private int defauleHeight = 200;
    private int defaultWidth = 500;
    private float mWidth, mHeight;
    private float distanceY; // 单个音频条的宽度
    private Context mContext;
    private float spaceing; // 每个音频条之间的间距
    private Paint mPaint;
    private float random;

    public AudioBarView(Context context) {
        this(context, null);
    }

    public AudioBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public AudioBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 测量宽高
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int heightMeasureSpec) {
        int height;
        int spaceMode = MeasureSpec.getMode(heightMeasureSpec);
        int spaceHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (spaceMode == MeasureSpec.EXACTLY) {
            height = spaceHeight;
        } else {
            height = Math.min(spaceHeight, defauleHeight);
        }
        return height;
    }

    private int measureWidth(int widthMeasureSpec) {
        int width;
        int specMode = getMode(widthMeasureSpec);
        int spceSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            width = spceSize;
        } else {
            width = Math.min(spceSize, defaultWidth);
        }
        return width;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        computeViewSize();
        drawAudioView(canvas);
    }

    private void computeViewSize() {
        spaceing = DimenUtil.dip2px(mContext, 2);
        mWidth = getWidth();
        mHeight = getHeight();
        distanceY = (mWidth - 39 * spaceing) / 40;
    }

    private void drawAudioView(Canvas canvas) {
        for (int i = 0; i < 40; i++) {
            random = (float) Math.random(); //生成0-1之间的随机数
            canvas.drawRect(spaceing * i + distanceY * i, mHeight / 2 * random, distanceY * (i + 1) + spaceing * i, mHeight / 2, mPaint);
            //bottom = mHeight/2+(mHeight/2-mHeight/2*random) = mHeight - mHeight/2*random
            canvas.drawRect(spaceing * i + distanceY * i, mHeight / 2, distanceY * (i + 1) + spaceing * i, mHeight - mHeight / 2 * random, mPaint);
        }
    }

    // 调用该方法使其音频条的高度改变(本质是改变top值)，达到音频波动的效果
    public void changeAudioBarHeight(float db){
        random = db;
        invalidate();
    }
}
