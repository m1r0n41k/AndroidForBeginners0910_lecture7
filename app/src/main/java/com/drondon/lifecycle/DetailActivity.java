package com.drondon.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetailActivity extends Activity {

    private static final String TAG = "DetailActivity_";

    public static final String MY_REQUEST_KEY = "com.drondon.lifecycle.request.screen.name";

    public static final int REQUEST_CODE = 1234;
    public static final String MY_EXTRA_KEY_NAME = "com.drondon.lifecycle.result.name";

    boolean launchFromMainActivity = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d(TAG, "onCreate: " + this);

        //Получение данных с вызывающего активити
        Intent intent = getIntent();
        String requestScreen = intent.getStringExtra("MY_REQUEST_KEY");
        if (requestScreen != null) {
            String formattedString = getString(R.string.app_toast_request_screen_message, requestScreen);
            String appName = /*context.*/getString(R.string.app_name);
            Toast.makeText(this, formattedString, Toast.LENGTH_LONG).show();
        }

        if ("MainActivity".equals(requestScreen)) {
            launchFromMainActivity = true;
        }

        // Инициализируем View
        final EditText etName = findViewById(R.id.et_name);
        final Button btnSave = findViewById(R.id.button_save);

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged() called with: s = [" + s + "], start = [" + start + "], count = [" + count + "], after = [" + after + "]");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged() called with: s = [" + s + "], start = [" + start + "], before = [" + before + "], count = [" + count + "]");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged() called with: s = [" + s + "]");
                String name = s.toString();
                if (name.length() > 9) {
                    etName.setError("ERROR!!!! Слишком длинное");
                    btnSave.setVisibility(View.GONE);
                } else {
                    etName.setError(null);
                    btnSave.setVisibility(View.VISIBLE);
                }
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();

                //Помещаем данные на отправку
                Intent resultIntent = new Intent();
                resultIntent.putExtra(MY_EXTRA_KEY_NAME, name);
                setResult(Activity.RESULT_OK, resultIntent);

                //Завершает работу DetailActivity
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    // Доступно пользователю

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
