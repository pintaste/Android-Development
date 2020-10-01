package com.example.singletonmodeldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetupAddBubblesButton();
        SetupPopActivityButton();
        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void SetupAddBubblesButton() {
        Button btn = (Button) findViewById(R.id.add_bubble_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BubbleWrap.getInstance().addMoreBubbles();
                updateUI();
            }
        });
    }

    private void SetupPopActivityButton() {
        Button btn = (Button) findViewById(R.id.pop_activity_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PopActivity.class));
            }
        });
    }

    private void updateUI() {
        TextView tv = findViewById(R.id.main_bubble_text_view);
        String msg = String.format(Locale.getDefault(),
                "%d bubbles left!",
                BubbleWrap.getInstance().getBubbles());
        tv.setText(msg);
    }
}
