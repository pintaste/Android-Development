package com.example.singletonmodeldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class PopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        SetupPopBubbleButton();
        updateUI();
    }

    private void SetupPopBubbleButton() {
        Button btn = findViewById(R.id.pop_bubble_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BubbleWrap.getInstance().popBubble();
                Toast.makeText(PopActivity.this,"POP",Toast.LENGTH_SHORT)
                        .show();
                updateUI();
            }
        });
    }

    private void updateUI() {
        TextView tv = findViewById(R.id.pop_bubble_text_view);
        tv.setText(String.format(Locale.getDefault(),
                "%d bubbles left!",
                BubbleWrap.getInstance().getBubbles()));
    }




}


