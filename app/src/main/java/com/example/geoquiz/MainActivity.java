package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "Index";
    private static final String KEY_ANSWERED = "Answered";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true,0),
            new Question(R.string.question_oceans, true,0),
            new Question(R.string.question_mideast, false,0),
            new Question(R.string.question_africa, false,0),
            new Question(R.string.question_americas, true,0),
            new Question(R.string.question_asia, true,0)
    };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // log output method
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

//        if (savedInstanceState != null) {
//            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
//            int [] answeredList = savedInstanceState.getIntArray(KEY_ANSWERED);
//            for(int i = 0; i < mQuestionBank.length; i++)
//            {
//                mQuestionBank[i].setIsAnswered(answeredList[i]);
//            }
//        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        // challenge 2.7: set a Listener for questionTextView
        // go to the next question when clicked
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        // challenge 2.8 add a PREV button
        // go back last question when clicked
        mPrevButton = (Button) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex > 0){
                    mCurrentIndex--;
                }
                else {
                    mCurrentIndex = mQuestionBank.length - 1;
                }
                updateQuestion();
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        //setButtonState();
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            mQuestionBank[mCurrentIndex].setIsAnswered(1);
        } else {
            messageResId = R.string.incorrect_toast;
            mQuestionBank[mCurrentIndex].setIsAnswered(-1);
        }

        //setButtonState();

        Toast t = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        // challenge 1.11: show the toast message at top of screen.
        t.setGravity(Gravity.TOP, 0, 0);
        t.show();
    }

    // challenge 3.7: only can answer once
    public void setButtonState() {
        if(mQuestionBank[mCurrentIndex].getIsAnswered() == 0) {
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        }
        else
        {
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);

        // creates an arr to store the state of answering.
//        int [] answeredList = new int[mQuestionBank.length];
//        for(int i = 0; i < mQuestionBank.length; i++) {
//            answeredList[i] = mQuestionBank[i].getIsAnswered();
//        }
//        savedInstanceState.putIntArray(KEY_ANSWERED,answeredList);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

}


