package com.attendance.work_assistant.extra;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.attendance.work_assistant.R;

public class TipButton
        extends android.support.v7.widget.AppCompatRadioButton
{
    private Dot mDot;
    private boolean mTipOn = false;

    public TipButton(Context paramContext)
    {
        super(paramContext);
        init();
    }

    public TipButton(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        init();
    }

    public TipButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        init();
    }

    private void init()
    {
        this.mDot = new Dot();
    }

    public boolean isTipOn()
    {
        return this.mTipOn;
    }

    protected void onDraw(Canvas paramCanvas)
    {
        super.onDraw(paramCanvas);
        if (this.mTipOn)
        {
            float f2 = getWidth() - this.mDot.marginRight - this.mDot.radius;
            float f3 = this.mDot.marginTop + this.mDot.radius;
            Object localObject = getCompoundDrawables()[1];
            float f1 = f2;
            if (localObject != null)
            {
                int j = ((Drawable)localObject).getIntrinsicWidth();
                f1 = f2;
                if (j > 0)
                {
                    int i = getWidth() / 2;
                    j /= 2;
                    f1 = this.mDot.radius + (i + j);
                }
            }
            localObject = getPaint();
            int i = ((Paint)localObject).getColor();
            ((Paint)localObject).setColor(this.mDot.color);
            ((Paint)localObject).setStyle(Paint.Style.FILL);
            paramCanvas.drawCircle(f1, f3, this.mDot.radius, (Paint)localObject);
            ((Paint)localObject).setColor(i);
        }
    }

    public void setTipOn(boolean paramBoolean)
    {
        this.mTipOn = paramBoolean;
        invalidate();
    }

    private class Dot
    {
        int color;
        int marginRight;
        int marginTop;
        int radius;

        Dot()
        {
            float f = TipButton.this.getContext().getResources().getDisplayMetrics().density;
            this.radius = ((int)(5.0F * f));
            this.marginTop = ((int)(1.0F * f));
            this.marginRight = ((int)(3.0F * f));
            this.color = TipButton.this.getContext().getResources().getColor(R.color.red);
        }
    }
}
