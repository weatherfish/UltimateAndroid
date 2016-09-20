package com.marshalchen.common.uimodule.passcodelock;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.marshalchen.common.uimodule.R;


public abstract class AbstractPasscodeKeyboardActivity extends Activity {

    protected EditText pinCodeField1 = null;
    protected EditText pinCodeField2 = null;
    protected EditText pinCodeField3 = null;
    protected EditText pinCodeField4 = null;
    protected InputFilter[] filters = null;
    protected TextView topMessage = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passlock_passcode_keyboard);
        
        topMessage = (TextView) findViewById(R.id.top_message);
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String message = extras.getString("message");
            if (message != null) {
                topMessage.setText(message);
            }
        }
        
        filters = new InputFilter[2];
        filters[0]= new InputFilter.LengthFilter(1);
        filters[1] = onlyNumber;
        
        //Setup the pin fields row
        pinCodeField1 = (EditText) findViewById(R.id.pincode_1);
        setupPinItem(pinCodeField1);
        
        pinCodeField2 = (EditText) findViewById(R.id.pincode_2);
        setupPinItem(pinCodeField2);
        
        pinCodeField3 = (EditText) findViewById(R.id.pincode_3);
        setupPinItem(pinCodeField3);
        
        pinCodeField4 = (EditText) findViewById(R.id.pincode_4);
        setupPinItem(pinCodeField4);
        
        //setup the keyboard
        ((Button) findViewById(R.id.button0)).setOnClickListener(defaultButtonListener);
        ((Button) findViewById(R.id.button1)).setOnClickListener(defaultButtonListener);
        ((Button) findViewById(R.id.button2)).setOnClickListener(defaultButtonListener);
        ((Button) findViewById(R.id.button3)).setOnClickListener(defaultButtonListener);
        ((Button) findViewById(R.id.button4)).setOnClickListener(defaultButtonListener);
        ((Button) findViewById(R.id.button5)).setOnClickListener(defaultButtonListener);
        ((Button) findViewById(R.id.button6)).setOnClickListener(defaultButtonListener);
        ((Button) findViewById(R.id.button7)).setOnClickListener(defaultButtonListener);
        ((Button) findViewById(R.id.button8)).setOnClickListener(defaultButtonListener);
        ((Button) findViewById(R.id.button9)).setOnClickListener(defaultButtonListener);
        ((Button) findViewById(R.id.button_erase)).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        if( pinCodeField1.isFocused() ) {

                        }
                        else if( pinCodeField2.isFocused() ) {
                            pinCodeField1.requestFocus();
                            pinCodeField1.setText("");
                        }
                        else if( pinCodeField3.isFocused() ) {
                            pinCodeField2.requestFocus();
                            pinCodeField2.setText("");
                        }
                        else if( pinCodeField4.isFocused() ) {
                            pinCodeField3.requestFocus();
                            pinCodeField3.setText("");
                        }
                    }
                });
    }
    
    
    protected void setupPinItem(EditText item){
        item.setInputType(InputType.TYPE_NULL); 
        item.setFilters(filters); 
        item.setOnTouchListener(otl);
        item.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }
    
    private OnClickListener defaultButtonListener = new OnClickListener() {
        @Override
        public void onClick(View arg0) {
            int currentValue = -1;
            int id = arg0.getId();
			if (id == R.id.button0) {
				currentValue = 0;
			} else if (id == R.id.button1) {
				currentValue = 1;
			} else if (id == R.id.button2) {
				currentValue = 2;
			} else if (id == R.id.button3) {
				currentValue = 3;
			} else if (id == R.id.button4) {
				currentValue = 4;
			} else if (id == R.id.button5) {
				currentValue = 5;
			} else if (id == R.id.button6) {
				currentValue = 6;
			} else if (id == R.id.button7) {
				currentValue = 7;
			} else if (id == R.id.button8) {
				currentValue = 8;
			} else if (id == R.id.button9) {
				currentValue = 9;
			} else {
			}
            
            //set the value and move the focus
            String currentValueString = String.valueOf(currentValue);
            if( pinCodeField1.isFocused() ) {
                pinCodeField1.setText(currentValueString);
                pinCodeField2.requestFocus();
                pinCodeField2.setText("");
            }
            else if( pinCodeField2.isFocused() ) {
                pinCodeField2.setText(currentValueString);
                pinCodeField3.requestFocus();
                pinCodeField3.setText("");
            }
            else if( pinCodeField3.isFocused() ) {
                pinCodeField3.setText(currentValueString);
                pinCodeField4.requestFocus();
                pinCodeField4.setText("");
            }
            else if( pinCodeField4.isFocused() ) {
                pinCodeField4.setText(currentValueString);
            }

            if(pinCodeField4.getText().toString().length() > 0 &&
                    pinCodeField3.getText().toString().length() > 0 &&
                    pinCodeField2.getText().toString().length() > 0 &&
                    pinCodeField1.getText().toString().length() > 0
                    ) {
                onPinLockInserted();
            }
        }
    };

    protected void showPasswordError(){
        Toast toast = Toast.makeText(AbstractPasscodeKeyboardActivity.this, "passcode_wrong_passcode", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 30);
        toast.show();
    }
    
    protected abstract void onPinLockInserted();

    private InputFilter onlyNumber = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            
            if( source.length() > 1 )
                return "";

            if( source.length() == 0 ) //erase
                return null;

            try {
                int number = Integer.parseInt(source.toString());
                if( ( number >= 0 ) && ( number <= 9 ) )
                    return String.valueOf(number);
                else
                    return "";
            } catch (NumberFormatException e) {
                return "";
            }
        }
    };
    
    private OnTouchListener otl = new OnTouchListener() {
        @Override
        public boolean onTouch (View v, MotionEvent event) {
            if( v instanceof EditText ) {
                ((EditText)v).setText("");
            }
            return false;
        }
    };
    
}