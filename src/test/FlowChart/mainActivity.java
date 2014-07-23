package test.FlowChart;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testflowchart.R;

/**
 * 主程序
 * @author liuxiuquan
 * 2014年7月22日
 */
public class mainActivity extends Activity {
	/**开动动画的按键*/
	TextView startAnim;
	/**设置的按键*/
	TextView settingTextView;
	/**自定义的流量图*/
	FlowChart fc;
	/**最大值TextView*/
	TextView maxTextView;
	/**实际值TextView*/
	TextView curTextView;
	/**确定按键*/
	Button confirm;
	/**取消按键*/
	Button cancle;
	/**对话框*/
	AlertDialog dialog;
	/**设置的输入框*/
	EditText editText;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paper_bucket);
		// 初始化组件Id
		InItObj();
		float maxVal = 100;// 最大值
		float curVal = 71;// 实际值
		setViewParm(maxVal,curVal);
	}
	
	/**
	 * 初始化view
	 * @param maxVal 最大值
	 * @param curVal 实际值
	 */
	public void setViewParm(float maxVal,float curVal){
		fc.setValues(maxVal, curVal);// 设置到view中
		fc.resetAinm();//复位
		fc.startAinm();// 开启water动画
		maxTextView.setText(maxVal + "");// 设置显示的最大值
		curTextView.setText(curVal + "");// 设置显示的实际值
	}

	/**
	 * 初始化组件
	 */
	private void InItObj() {
		// 初始化id
		fc = (FlowChart) findViewById(R.id.water);
		startAnim = (TextView) findViewById(R.id.start_anim);
		maxTextView = (TextView) findViewById(R.id.textview_max_val); // 最大值
		curTextView = (TextView) findViewById(R.id.textview_actual_val);// 实际值
		settingTextView = (TextView) findViewById(R.id.tv_setting_state);
		// 初始化对话框
		View layout = getLayoutInflater().inflate(R.layout.setting_dialog,
				(ViewGroup) findViewById(R.id.dialog));
		Builder alertDialog = new AlertDialog.Builder(this).setView(layout);
		confirm = (Button) layout.findViewById(R.id.bt_ok);
		cancle = (Button) layout.findViewById(R.id.bt_cancle);
		editText = (EditText) layout.findViewById(R.id.et_input);
		
		// 绑定点击事件
		startAnim.setOnClickListener(onClickListener);
		settingTextView.setOnClickListener(onClickListener);
		confirm.setOnClickListener(onClickListener);
		cancle.setOnClickListener(onClickListener);

		dialog = alertDialog.create();
	}

	/** 创建监听器 */
	private OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.start_anim: // 开启动画按键
				if (fc.getThreadRun()) {// 如果动画正在运行，则返回
					return;
				} else {
					// 复位
					fc.resetAinm();
					// 开启动画
					fc.startAinm();
				}
				break;
			case R.id.tv_setting_state: // 设置按键
				editText.setText("");
				dialog.show();
				break;
			case R.id.bt_ok:// 对话框的确定按键
				
				if(editText.getText().toString().length()>0){
					float curV=Float.parseFloat(editText.getText().toString());
					float maxV=fc.getHeightMax();
					if(curV>=0&&curV<=maxV){
//						Toast.makeText(mainActivity.this, "确定", Toast.LENGTH_SHORT)
//						.show();	
						setViewParm(maxV,curV);
						editText.setText("");
						dialog.dismiss();//隐藏对话框
					}else{
						Toast.makeText(mainActivity.this, "输入范围有误", Toast.LENGTH_SHORT)
						.show();
					}
				}else{
					Toast.makeText(mainActivity.this, "请输入数值", Toast.LENGTH_SHORT)
					.show();
				}
				
				break;
			case R.id.bt_cancle:// 对话框的取消按键
				dialog.hide();
				editText.setText("");
				break;
			default:
				break;
			}
		}
	};
}
