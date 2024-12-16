package com.mwampulule.kikokotozi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private double firstNum = 0;
    private String operation = null;
    private boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Number buttons
        Button num0 = findViewById(R.id.num0);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);

        // Operation buttons
        Button add = findViewById(R.id.add);
        Button subs = findViewById(R.id.subtract);
        Button divide = findViewById(R.id.divide);
        Button multiply = findViewById(R.id.multiply);
        Button equals = findViewById(R.id.equals);
        Button ac = findViewById(R.id.clear);
        Button del = findViewById(R.id.del);
        Button point = findViewById(R.id.decimal);
        Button on = findViewById(R.id.on);
        Button off = findViewById(R.id.off);

        TextView screen = findViewById(R.id.screen);

        // Clear Screen
        ac.setOnClickListener(view -> {
            firstNum = 0;
            operation = null;
            screen.setText("0");
            isNewInput = true;
        });

        // Turn OFF and ON
        off.setOnClickListener(view -> screen.setVisibility(View.GONE));
        on.setOnClickListener(view -> {
            screen.setVisibility(View.VISIBLE);
            screen.setText("0");
            isNewInput = true;
        });

        // Numeric Buttons
        View.OnClickListener numberClickListener = view -> {
            String currentText = screen.getText().toString();
            String number = ((Button) view).getText().toString();

            if (isNewInput || currentText.equals("0")) {
                screen.setText(number);
            } else {
                screen.setText(currentText + number);
            }
            isNewInput = false;
        };

        num0.setOnClickListener(numberClickListener);
        num1.setOnClickListener(numberClickListener);
        num2.setOnClickListener(numberClickListener);
        num3.setOnClickListener(numberClickListener);
        num4.setOnClickListener(numberClickListener);
        num5.setOnClickListener(numberClickListener);
        num6.setOnClickListener(numberClickListener);
        num7.setOnClickListener(numberClickListener);
        num8.setOnClickListener(numberClickListener);
        num9.setOnClickListener(numberClickListener);

        // Decimal Point
        point.setOnClickListener(view -> {
            String currentText = screen.getText().toString();
            if (!currentText.contains(".")) {
                screen.setText(currentText + ".");
            }
        });

        // Delete Last Character
        del.setOnClickListener(view -> {
            String currentText = screen.getText().toString();
            if (currentText.length() > 1) {
                screen.setText(currentText.substring(0, currentText.length() - 1));
            } else {
                screen.setText("0");
                isNewInput = true;
            }
        });

        // Operation Buttons
        View.OnClickListener operationClickListener = view -> {
            firstNum = Double.parseDouble(screen.getText().toString());
            operation = ((Button) view).getText().toString();
            isNewInput = true;
        };

        add.setOnClickListener(operationClickListener);
        subs.setOnClickListener(operationClickListener);
        divide.setOnClickListener(operationClickListener);
        multiply.setOnClickListener(operationClickListener);

        // Equals Button
        equals.setOnClickListener(view -> {
            if (operation == null) return;

            double secondNum = Double.parseDouble(screen.getText().toString());
            double result = 0;

            switch (operation) {
                case "+":
                    result = firstNum + secondNum;
                    break;
                case "-":
                    result = firstNum - secondNum;
                    break;
                case "/":

                        result = firstNum / secondNum;

                    break;
                case "x":
                    result = firstNum * secondNum;
                    break;
            }

            screen.setText(String.valueOf(result));
            firstNum = result; // Save result for further calculations
            isNewInput = true;
            operation = null;
        });
    }
}
