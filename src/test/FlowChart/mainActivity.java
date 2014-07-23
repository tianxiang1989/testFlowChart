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
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paper_bucket);
		// 自定义的流量图
		final FlowChart fc = (FlowChart) findViewById(R.id.water);
		// 开启water动画
		fc.startAinm();
		// 给TextView增加点击事件
		TextView startAnim = (TextView) findViewById(R.id.start_anim);
		startAnim.setOnClickListener(new Button.OnClickListener() {// 创建监听
					public void onClick(View v) {
						if (fc.runNow) {// 如果动画正在运行，则返回
							return;
						} else {
							// 复位
							fc.resetAinm();
							// 开启动画
							fc.startAinm();
						}
					}
				});
	}
}
