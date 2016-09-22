package com.footballcitymobileandroid.BLL.Util.CustomView;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.R;


    public class CreateDialog extends Dialog {

        /**
         * 上下文对象 *
         */
        Activity context;

         Button btn_save;

         Button btn_fail;

        public EditText text_name;

         public RadioGroup choose_radio;

        public EditText text_mobile;

        public EditText text_info;


        private View.OnClickListener mClickListener;

        public CreateDialog(Activity context) {
            super(context);
            this.context = context;
        }

        public CreateDialog(Activity context, int theme, View.OnClickListener clickListener) {
            super(context, theme);
            this.context = context;
            this.mClickListener = clickListener;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // 指定布局
            this.setContentView(R.layout.dialog_name_layout);
            text_name = (EditText) findViewById(R.id.message);
            final TextView title=(TextView)findViewById(R.id.title);
             choose_radio=(RadioGroup)findViewById(R.id.choose_radio);

            if (MainApplication.dialog.equals("name"))
            {
                choose_radio.setVisibility(View.GONE);
                text_name.setVisibility(View.VISIBLE);
            }else if(MainApplication.dialog.equals("age"))
            {
                text_name.setVisibility(View.GONE);

                choose_radio.setVisibility(View.VISIBLE);
                title.setText("选择性别");
            }else if(MainApplication.dialog.equals("high"))
            {
                choose_radio.setVisibility(View.GONE);
                text_name.setVisibility(View.VISIBLE);
                //  message.setVisibility(View.GONE);
                //  choose_radio.setVisibility(View.GONE);
                // high.setVisibility(View.VISIBLE);
                //    data= CityInfo.CityInfoData();
                //cityAdapter=new CityAdapter(getActivity(),data);

            }else if(MainApplication.dialog.equals("brithday"))
            {
                text_name.setVisibility(View.VISIBLE);
                choose_radio.setVisibility(View.GONE);
                //message.setText("出生日期");

            }

  /*
   * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
   * 对象,这样这可以以同样的方式改变这个Activity的属性.
   */
            Window dialogWindow = this.getWindow();

            WindowManager m = context.getWindowManager();
            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
            WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
             p.height = (int) (d.getHeight() * 0.35); // 高度设置为屏幕的0.6
            p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.8
            dialogWindow.setAttributes(p);

            // 根据id在布局中找到控件对象
            btn_save = (Button) findViewById(R.id.btn_sure);//确定
            btn_fail= (Button) findViewById(R.id.btn_fail);

            // 为按钮绑定点击事件监听器
            btn_save.setOnClickListener(mClickListener);
            btn_fail.setOnClickListener(mClickListener);
            this.setCancelable(true);
        }
    }

