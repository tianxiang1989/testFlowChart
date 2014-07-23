package test.FlowChart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testflowchart.R;

/**
 * 主程序
 * @author liuxiuquan
 * 2014年7月22日
 */
public class mainActivity extends Activity {
	/**开动动画的按键*/
	TextView startAnim;
	/**自定义的流量图*/
	FlowChart fc;
	/**最大值TextView*/
	TextView maxTextView;
	/**实际值TextView*/
	TextView curTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paper_bucket);
		fc = (FlowChart) findViewById(R.id.water);
		float maxVal = 100;//最大值
		float curVal = 71;//实际值
		fc.setValues(maxVal, curVal);// 设置到view中
		fc.startAinm();// 开启water动画
		// 给TextView增加点击事件
		startAnim = (TextView) findViewById(R.id.start_anim);
		startAnim.setOnClickListener(new Button.OnClickListener() {// 绑定监听事件
					public void onClick(View v) {
						if (fc.getThreadRun()) {// 如果动画正在运行，则返回
							return;
						} else {
							// 复位
							fc.resetAinm();
							// 开启动画
							fc.startAinm();
						}
					}
				});
		maxTextView = (TextView) findViewById(R.id.textview_max_val); //最大值
		maxTextView.setText(maxVal + "");
		curTextView = (TextView) findViewById(R.id.textview_actual_val);//实际值
		curTextView.setText(curVal + "");
	}
}
