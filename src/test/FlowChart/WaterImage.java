package test.FlowChart;

//import com.talicai.timiclient.c;
import java.io.BufferedInputStream;
import java.util.Timer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

@SuppressLint({"HandlerLeak", "DrawAllocation"})
public class WaterImage extends View
{
  Timer time;
  int drawWhich;
  private Rect curDrawRect;
  private Rect destRec;
  private int imageWidth;
  private int width1;
  private int height1;
  private int h;
  private int i;
  private int j;
  private int k;
  private int l;
  private float m;
  private Paint n;
  private Paint paint1;
  private Bitmap[] bitmapArray;
  private int q;

  public WaterImage(Context paramContext)
  {
    this(paramContext, null);
  }

  public WaterImage(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public WaterImage(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.width1 = 300;
    this.height1 = 600;
    this.h = 0;
    this.time = new Timer();
    this.drawWhich = 0;
    init(paramContext);
  }

  public  int a(Context paramContext, float paramFloat)
  {
//    return (int)(0.5F + paramFloat * c.a);
	  return 10;
  }

  private int drawWhich()
  {
    if (this.h > 4.5D * this.height1 / 5.0D)
      return 4;
    if (this.h > 4 * this.height1 / 5)
      return 3;
    if (this.h > 3 * this.height1 / 5)
      return 2;
    if (this.h > 2 * this.height1 / 5)
      return 1;
    return 0;
  }

  public void a()
  {
    this.time.cancel();
    this.time = null;
  }

  public void a(float paramFloat)
  {
    this.m = paramFloat;
//    this.h = TimiApplication.c("waterHeight");
//    this.a.schedule(new o(this, paramFloat), 0L, 5L);
  }

  public void a(int paramInt, float paramFloat)
  {
    if (this.q == this.h)
    {
      if (this.imageWidth - (this.width1 / 4) - paramInt >= 0)
//        break label100;
      this.curDrawRect.set(0, 0, this.width1 / 4, 200);
    }
//    label45: this.h = (int)paramFloat;
    if (this.height1 == this.h)
      this.destRec.set(0, -30 + this.height1 - this.h, this.width1 - this.l, this.height1);
    while (true)
    {
      postInvalidate();
      return;
//      label100: this.c.set(this.e - (this.f / 4) - paramInt, 0, this.e - paramInt, 200);
//      break label45:
//      this.d.set(0, this.g - this.h, this.f - this.l, this.g);
    }
  }

  public void init(Context paramContext)
  {
    Resources localResources = getResources();
    Bitmap[] arrayOfBitmap = new Bitmap[5];
    arrayOfBitmap[0] = BitmapFactory.decodeStream(new BufferedInputStream(localResources.openRawResource(2130837767)));
    arrayOfBitmap[1] = BitmapFactory.decodeStream(new BufferedInputStream(localResources.openRawResource(2130837768)));
    arrayOfBitmap[2] = BitmapFactory.decodeStream(new BufferedInputStream(localResources.openRawResource(2130837769)));
    arrayOfBitmap[3] = BitmapFactory.decodeStream(new BufferedInputStream(localResources.openRawResource(2130837770)));
    arrayOfBitmap[4] = BitmapFactory.decodeStream(new BufferedInputStream(localResources.openRawResource(2130837771)));
    this.bitmapArray = arrayOfBitmap;
    this.imageWidth = this.bitmapArray[0].getWidth();
    this.width1 = a(paramContext, 200.0F);
    this.i = a(paramContext, 7.0F);
    this.curDrawRect = new Rect(0, 0, this.width1 / 4, 200);
    this.destRec = new Rect(0, this.height1 - this.h, this.width1 - this.l, this.height1);
    this.n = new Paint();
    this.n.setAntiAlias(true);
    this.n.setStrokeWidth(2.0F);
    this.n.setStyle(Paint.Style.FILL);
    this.j = (20 + this.width1);
    this.k = (30 + this.width1);
    this.l = a(paramContext, 60.0F);
    this.paint1 = new Paint();
    this.paint1.setAntiAlias(true);
    this.paint1.setTextSize(20.0F);
    this.paint1.setColor(-7829368);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.drawBitmap(this.bitmapArray[drawWhich()], this.curDrawRect, this.destRec, null);
    int i1 = this.height1 + this.i;
    int i2 = 0;
    if (i2 >= 41)
    {
      label34: this.n.setColor(-65536);
      int i4 = this.height1 - this.h + (1 + drawWhich()) * this.i;
      paramCanvas.drawLine(this.j, i4, 15 + this.k, i4, this.n);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Float.valueOf(100.0F * this.m);
      paramCanvas.drawText(String.format("%.2f", arrayOfObject) + "%", this.j + 2 * this.i, i4, this.paint1);
      return;
    }
    int i3 = i1 - this.i;
    this.n.setColor(Color.parseColor("#C5C9CE"));
    if (i2 % 5 == 0)
      paramCanvas.drawLine(this.j, i3, 15 + this.k, i3, this.n);
    while (true)
    {
      ++i2;
      i1 = i3;
//      break label34:
      paramCanvas.drawLine(this.j, i3, this.k, i3, this.n);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i1 = View.MeasureSpec.getSize(paramInt1);
    int i2 = View.MeasureSpec.getSize(paramInt2);
    this.width1 = (i1 - 30);
    this.height1 = i2;
  }
}

