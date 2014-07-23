package test.FlowChart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testflowchart.R;

/**
 * @author liuxiuquan
 * 2014年7月22日
 */
public class mainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paper_bucket);

		final FlowChart fc = (FlowChart) findViewById(R.id.water);
		fc.startAinm();
		TextView startAnim = (TextView) findViewById(R.id.start_anim);
		startAnim.setOnClickListener(new Button.OnClickListener() {// 创建监听
					public void onClick(View v) {
						if (fc.runNow) {
							return;
						} else {
							// 复位
							fc.resetAinm();
						}
					}
				});
	}
}
