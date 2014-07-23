package test.FlowChart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.testflowchart.R;

/**
 * 自定义的流量图view
 * @author liuxiuquan
 * 2014年7月23日
 */
public class FlowChart extends View {
	/**定义的最大总高度*/
	private final int HEIGHTMAX = 600;
	/**需要绘制的值 [和height1的值成比例]*/
	private int waterHeight;
	/**取water图片的矩形范围*/
	private Rect sourceRect;
	/**画water图片的矩形范围*/
	private Rect drawRect;
	/**画笔：写文字用*/
	private Paint paint1;
	/**画笔：画线用*/
	private Paint paint2;
	/**water图数组*/
	private Bitmap[] bitmapArray;
	/**water图高度*/
	private int bitmapHeight;
	/**water图宽度*/
	private int bitmapWidth;
	/**water图有效部分[不包含透明]和总高度的比[注：更换图片需要修改此参数]*/
	private float ratioBitmap = (float) (7.55 / 8.36);
	/**实际绘制的矩形的高度*/
	private float drawHeight;
	/**控制同一时间只执行一次:false执行完成;true为正在执行*/
	public boolean runNow = false;
	/**每一次显示的water图像宽度*/
	public float showRadioWaterWidth = 3F / 4;
	/**thread中显示的偏移量*/
	public int offset = 0;

	/**
	 * 构造方法
	 */
	public FlowChart(Context context) {
		this(context, null);
	}

	/**
	 * 构造方法
	 */
	public FlowChart(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * 构造方法
	 */
	public FlowChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.waterHeight = 283;
		init(context);
	}

	/**
	 * 数据初始化
	 * @param paramContext
	 */
	public void init(Context paramContext) {
		// 加载5张water图
		Bitmap[] arrayOfBitmap = new Bitmap[5];
		arrayOfBitmap[0] = BitmapFactory.decodeResource(getResources(),
				R.drawable.water_1);
		arrayOfBitmap[1] = BitmapFactory.decodeResource(getResources(),
				R.drawable.water_2);
		arrayOfBitmap[2] = BitmapFactory.decodeResource(getResources(),
				R.drawable.water_3);
		arrayOfBitmap[3] = BitmapFactory.decodeResource(getResources(),
				R.drawable.water_4);
		arrayOfBitmap[4] = BitmapFactory.decodeResource(getResources(),
				R.drawable.water_5);
		this.bitmapArray = arrayOfBitmap;
		// 获取在当前手机下water图的宽高
		this.bitmapWidth = this.bitmapArray[0].getWidth();
		this.bitmapHeight = this.bitmapArray[0].getHeight();
		// 设置初始读取water图的位置
		this.sourceRect = new Rect((int) (bitmapWidth * showRadioWaterWidth), 0,
				this.bitmapWidth, bitmapHeight);
		// 背景图的宽高转为pix
		int widthBackgroud = changeDp(200);
		int heightBackgroud = changeDp(300);
		// 计算实际绘制的矩形的高度
		this.drawHeight = (ratioBitmap * heightBackgroud
				* (this.HEIGHTMAX - this.waterHeight) / this.HEIGHTMAX);
		// 设置实际绘制的矩形位置
		this.drawRect = new Rect(0, (int) drawHeight, widthBackgroud,
				heightBackgroud);
		// 画笔初始化
		this.paint1 = new Paint();
		this.paint1.setAntiAlias(true);
		this.paint1.setTextSize(20f); // 字体大小
		this.paint2 = new Paint();
		this.paint2.setAntiAlias(true);
		this.paint2.setStyle(Paint.Style.FILL);
		paint2.setStrokeWidth(5f); // 线宽
		paint2.setColor(Color.parseColor("#000000")); // 黑色
	}

	/**
	 * dp转化pix像素--工具方法
	 * @param dp dp的值
	 * @return pix的值
	 */
	private int changeDp(int dp) {
		int pix = Math.round(TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, dp, getResources()
						.getDisplayMetrics()));
		return pix;
	}

	/**开启动画*/
	public void startAinm() {
		DrawThread dr = new DrawThread();
		dr.start();
	}

	/**复位动画*/
	public void resetAinm() {
		offset = 0; // 偏移归0
	}

	@Override
	protected void onDraw(Canvas paramCanvas) {
		paramCanvas.drawBitmap(this.bitmapArray[drawWhich()], this.sourceRect,
				this.drawRect, null);
		int i4 = (int) (drawHeight / ratioBitmap);
		int width4 = changeDp(220);
		// 绘制当前数值的横线
		paramCanvas
				.drawLine(width4, i4, changeDp(10) + width4, i4, this.paint2);
		Object[] arrayOfObject = new Object[1];
		arrayOfObject[0] = Float.valueOf(100F * waterHeight / HEIGHTMAX);
		paramCanvas.drawText(String.format("%.2f", arrayOfObject) + "%",
				changeDp(12) + width4, i4, paint1);// 数值
		this.paint2.setColor(Color.parseColor("#C5C9CE"));
		paint2.setStrokeWidth(2.0f); // 线宽
		int heightTotal = changeDp(300);
		for (int i = 0; i <= 20; i++) {
			paramCanvas.drawLine(width4, heightTotal / 20F * i, changeDp(5)
					+ width4, heightTotal / 20F * i, paint2);
		}

		paramCanvas.drawLine(width4, 0, width4, heightTotal, paint2); // 竖线

	}

	/**
	 * 判断当前的数值调用哪一张water图[颜色不同]
	 * @return 调用的water图序号
	 */
	private int drawWhich() {
		if (this.waterHeight > 4.5D * this.HEIGHTMAX / 5)
			return 4;
		if (this.waterHeight > 4D * this.HEIGHTMAX / 5)
			return 3;
		if (this.waterHeight > 3D * this.HEIGHTMAX / 5)
			return 2;
		if (this.waterHeight > 2D * this.HEIGHTMAX / 5)
			return 1;
		return 0;
	}

	/**water动画的线程类*/
	public class DrawThread extends Thread {
		@Override
		public void run() {
			runNow = true; // 标识当前正在运行
			while (true) {
				if (bitmapWidth * showRadioWaterWidth - offset > 0) {
					offset = offset + 3;// 宽度每次移动3像素
					sourceRect.set((int) (bitmapWidth * showRadioWaterWidth) - offset, 0,
							bitmapWidth - offset, bitmapHeight);
					postInvalidate();// 刷新
				} else {
					runNow = false;// 标识线程执行完毕
					postInvalidate();
					break;
				}
				try {
					// 刷新间隔
					Thread.sleep(6);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
