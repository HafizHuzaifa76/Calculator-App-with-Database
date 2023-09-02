package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button5, button6, button7, button8, button11, button12, button13, button14, button15
            , button16,button17, button18, button19, button20, button21, button22, button23, button24 ;
    ImageButton button10;
    TextView textView,textView2;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    int deleteRecord = 1;
    private String result = "0",originalText = "0";
    Boolean flag1 = true,originalTextFlag = false, resultFlag = false;
    int count;
    public float calculate;
    ArrayList<CalculationRecord> calculationRecordArrayList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);
        button13 = findViewById(R.id.button13);
        button14 = findViewById(R.id.button14);
        button15 = findViewById(R.id.button15);
        button16 = findViewById(R.id.button16);
        button17 = findViewById(R.id.button17);
        button18 = findViewById(R.id.button18);
        button19 = findViewById(R.id.button19);
        button20 = findViewById(R.id.button20);
        button21 = findViewById(R.id.button21);
        button22 = findViewById(R.id.button22);
        button23 = findViewById(R.id.button23);
        button24 = findViewById(R.id.button24);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DatabaseHelper databaseHelper = DatabaseHelper.getDatabase(this);
        setRecyclerViewAdapter(databaseHelper);
    }

    public void calculation(View view)
    {
        resultFlag = false;
        DatabaseHelper databaseHelper = DatabaseHelper.getDatabase(this);
        try {
            String operator;
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            Button currentButton = (Button) view;
            char buttonText = currentButton.getText().charAt(0);

            currentButton.setBackgroundResource(R.drawable.darkorange_circiular);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    currentButton.setBackgroundResource(R.drawable.submol_circular);
                }
            }, 200);

            if ((buttonText >= '0' && buttonText <= '9') || buttonText == '.') {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentButton.setBackgroundResource(R.drawable.circular);
                    }
                }, 200);
            }

            if ((result.charAt(result.length() - 1) == '/' || result.charAt(result.length() - 1) == '+' ||
                    result.charAt(result.length() - 1) == '-' || result.charAt(result.length() - 1) == '*')
                    && (buttonText == '/' || buttonText == '+' || buttonText == '-' || buttonText == '*')) {
                result = result.substring(0, result.length() - 1);
                originalText = originalText.substring(0, originalText.length() - 1);
            }
            if (result.equals("0")) {
                if (buttonText == '/' || buttonText == '*') {
                    result = "0" + buttonText;
                    originalText = "0" + buttonText;
                }
                else if (buttonText == '=') {
                    result = "0";
                    originalText = "0";
                }
                else if (buttonText == '-' || buttonText == '+') {
                    result = buttonText + "";
                    originalText = buttonText + "";
                }
                else {
                    result = buttonText + "";
                    originalText = buttonText + "";
                }
            } else {
                if (buttonText == '=') {
                    result = result;
                    originalText = originalText;
                }
                else {
                    result = result + buttonText;
                    originalText = originalText + buttonText;
                }
            }
            float val1, val2;
            if (buttonText == 'C') {
                result = "0";
                originalText = "0";
                textView.setText(result);
                textView2.setText(originalText);
                originalTextFlag = false;
            }
            String[] perform = result.split("[-+/*=%]");
            if (buttonText == '/' || buttonText == '+' || buttonText == '-' || buttonText == '*') {
                count++;
                Log.d("COUNT", "count:" + count);
            }
            if (result.charAt(0) == '-' && count > 2) {
                count = 1;
                operator = String.valueOf(result.charAt(perform[1].length() + 1));
                val1 = Float.parseFloat(perform[1]);
                if (perform[2].length() == 0)
                    val2 = 0;
                else
                    val2 = Float.parseFloat(perform[2]);
                calculate = equals(val1, val2, operator, '-');
                Log.d("VALUE", "v1:" + val1 + " v2:" + val2 + "oper:" + operator);
                Log.d("Result", result);
                if (buttonText == '=' && perform.length >= 2) {
                    calculate = equals(val1, val2, operator);
                    result = decimalFormat.format(calculate) + "";
                    databaseHelper.getCalculationRecordDao().insert(new CalculationRecord(originalText,result));
                    setRecyclerViewAdapter(databaseHelper);
                    resultFlag = true;
                }
                result = decimalFormat.format(calculate) + buttonText;
            } else if (perform.length >= 2 && result.charAt(0) != '-') {
                count = 1;
                if (perform[0].length() == 0) {
                    val1 = 0;
                } else {
                    val1 = Float.parseFloat(perform[0]);
                }
                if (perform[1].length() == 0) {
                    val2 = 0;
                } else {
                    val2 = Float.parseFloat(perform[1]);
                }
                operator = String.valueOf(result.charAt(perform[0].length()));
                Log.d("VALUE1", "v1:" + val1 + " v2:" + val2 + "oper:" + operator);
                Log.d("Result1", result);
                switch (buttonText) {
                    case '-': {
                        calculate = equals(val1, val2, operator);
                        result = decimalFormat.format(calculate) + "-";
                        flag1 = false;
                        break;
                    }
                    case '+': {
                        calculate = equals(val1, val2, operator);
                        result = decimalFormat.format(calculate) + "+";
                        flag1 = false;
                        break;
                    }
                    case '*': {
                        calculate = equals(val1, val2, operator);
                        result = decimalFormat.format(calculate) + "*";
                        flag1 = false;
                        break;
                    }
                    case '/': {
                        calculate = equals(val1, val2, operator);
                        result = decimalFormat.format(calculate) + "/";
                        flag1 = false;
                        break;
                    }
                    case '%': {
                        calculate = equals(val1, val2, operator);
                        if (calculate > 0.1)
                            calculate = calculate / 100;
                        else
                            calculate = 0;
                        result = decimalFormat.format(calculate) + "";
                        flag1 = false;
                        break;
                    }
                }
                if (buttonText == '=') {
                    calculate = equals(val1, val2, operator);
                    result = decimalFormat.format(calculate) + "";
                    databaseHelper.getCalculationRecordDao().insert(new CalculationRecord(originalText, result));
                    setRecyclerViewAdapter(databaseHelper);
                    resultFlag = true;
                }
            }
//            if (buttonText == '=' && perform.length < 2) {
//                result = result.substring(0, result.length() - 1);
//            }
            if (buttonText == '%' && perform.length < 2) {
                val1 = Float.parseFloat(perform[0]);
                if (val1 > 0.1)
                    val1 = val1 / 100;
                else
                    val1 = 0;
                result = decimalFormat.format(val1) + "";
            }
            if(!resultFlag)
                textView.setText(result);
            else {
                textView.setText(result);
                originalText = "";
                result = "0";
            }
            if (originalTextFlag)
                textView2.setText(originalText);
            else
                textView2.setText("");
        }
        catch (Exception exception){
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
            exception.getMessage();
            exception.printStackTrace();
        }
    }

    public void remove(View view){
        ImageButton imageButton = (ImageButton) view;
        if(view.getId() == R.id.button10) {
            if(result.length()<2 && originalText.length()<2) {
                result = "0";
                originalText = "0";
            }
            else {
                    result = result.substring(0, result.length() - 1);
                    originalText = originalText.substring(0, originalText.length() - 1);
            }
        }
        textView.setText(result);
        if (originalTextFlag)
            textView2.setText(originalText);
        else
            textView2.setText("");
    }

    public float equals(float val1, float val2, String opr) {
        originalTextFlag = true;
        switch (opr) {
            case "+":
                return val1 + val2;
            case "-":
                return val1 - val2;
            case "*":
                return val1 * val2;
            case "/":
                return val1 / val2;
            default:
                return 0;
        }
    }
    public float equals(float val1, float val2, String opr1,char opr2) {
        originalTextFlag = true;
        switch (opr1) {
            case "+":
                return -val1 + val2;
            case "-":
                return -val1 - val2;
            case "*":
                return -val1 * val2;
            case "/":
                return -val1 / val2;
            default:
                return 0;
        }
    }
    private void setRecyclerViewAdapter(DatabaseHelper databaseHelper){
        calculationRecordArrayList = (ArrayList<CalculationRecord>) databaseHelper.getCalculationRecordDao().getRecords();
        recyclerViewAdapter = new RecyclerViewAdapter(this,calculationRecordArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.scrollToPosition(calculationRecordArrayList.size()-1);
    }
}