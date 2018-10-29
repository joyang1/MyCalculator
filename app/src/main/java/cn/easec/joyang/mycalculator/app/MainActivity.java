package cn.easec.joyang.mycalculator.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;//定义数字按钮

    Button btn_plus;
    Button btn_reduce;
    Button btn_multiply;
    Button btn_divide;//定义运算符按钮

    Button btn_clear;
    Button btn_del;
    Button btn_point;
    Button btn_equal;//定义操作按钮

    EditText et_input;//显示结果

    boolean needclear;//设置是否清空EditText的标志

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置没有title
        setContentView(R.layout.activity_main);
        initView();
    }

    //初始化配置
    private void initView() {
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_reduce = (Button) findViewById(R.id.btn_reduce);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        et_input = (EditText) findViewById(R.id.et_input);
        MyListener myListener = new MyListener();
        btn_0.setOnClickListener(myListener);
        btn_1.setOnClickListener(myListener);
        btn_2.setOnClickListener(myListener);
        btn_3.setOnClickListener(myListener);
        btn_4.setOnClickListener(myListener);
        btn_5.setOnClickListener(myListener);
        btn_6.setOnClickListener(myListener);
        btn_7.setOnClickListener(myListener);
        btn_8.setOnClickListener(myListener);
        btn_9.setOnClickListener(myListener);
        btn_plus.setOnClickListener(myListener);
        btn_reduce.setOnClickListener(myListener);
        btn_multiply.setOnClickListener(myListener);
        btn_divide.setOnClickListener(myListener);
        btn_clear.setOnClickListener(myListener);
        btn_del.setOnClickListener(myListener);
        btn_point.setOnClickListener(myListener);
        btn_equal.setOnClickListener(myListener);
        et_input.setOnClickListener(myListener);
    }



    //这是一个匿名内部类，实现每个按钮的功能
    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String str = et_input.getText().toString();
            switch (v.getId()) {
                case R.id.btn_0:
                case R.id.btn_1:
                case R.id.btn_2:
                case R.id.btn_3:
                case R.id.btn_4:
                case R.id.btn_5:
                case R.id.btn_6:
                case R.id.btn_7:
                case R.id.btn_8:
                case R.id.btn_9:
                case R.id.btn_point:
                    if (needclear) {
                        str = "";
                        et_input.setText(str);
                    }
                    et_input.setText(str + ((Button) v).getText().toString());
                    needclear = false;
                    break;
                case R.id.btn_plus:
                case R.id.btn_reduce:
                case R.id.btn_multiply:
                case R.id.btn_divide:
//                    if(str.contains("+") || str.contains("-") || str.contains("×")
//                            || str.contains("÷")){
//                        getResult();
//                        break;
//                    }
                    if (needclear) {
                        str = "";
                        et_input.setText(str);
                    }
                    et_input.setText(str + " " + ((Button) v).getText().toString() + " ");
                    needclear = false;
                    break;
                case R.id.btn_del:
                    if (str != null && !str.equals("")) {
                        str = str.substring(0, str.length() - 1);
                        et_input.setText(str);
                    }
                    //needclear = false;
                    break;
                case R.id.btn_clear:
                    et_input.setText("");
                    needclear = false;
                    break;
                case R.id.btn_equal:
                    getResult();
                    break;
            }
        }

        private void getResult() {
            needclear = true;
            String expression = et_input.getText().toString();
            if (!expression.contains("+") && !expression.contains("-") && !expression.contains("×")
                    && !expression.contains("÷")) {
                et_input.setText(expression);
                return;
            }
            double res = 0;
            int spacePosition = expression.indexOf(' ');//搜索空格的位置
            String str1 = expression.substring(0, spacePosition);//第一个操作数
            String option = expression.substring(spacePosition + 1, spacePosition + 2);
            String str2 = expression.substring(spacePosition + 3, spacePosition + 4);//第二个操作数
            if (str1.equals("")) {
                str1 = "0";
            }
            if (str2.equals("")) {
                str2 = "0";
            }
            double number1 = Double.parseDouble(str1);
            double number2 = Double.parseDouble(str2);
            if (option.equals("+")) {
                res = number1 + number2;
            } else if (option.equals("-")) {
                res = number1 - number2;
            } else if (option.equals("×")) {
                res = number1 * number2;
            } else if (option.equals("÷")) {
                if (number2 == 0) {
                    res = 0;
                } else {
                    res = number1 / number2;
                }
            }
            if (!str1.contains(".") && !str1.contains(".") && !(res+"").contains(".")) {
                int result = (int) res;
                et_input.setText(result + "");
            } else {
                et_input.setText(res + "");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
