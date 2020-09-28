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
            new Question(R.string.question_australia, true,false),
            new Question(R.string.question_oceans, true,false),
            new Question(R.string.question_mideast, false,false),
            new Question(R.string.question_africa, false,false),
            new Question(R.string.question_americas, true,false),
            new Question(R.string.question_asia, true,false)
    };

    private int mCurrentIndex = 0;

    private int mCorrectCount = 0;
    private int mIncorrectCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // log output method
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            boolean [] answeredList = savedInstanceState.getBooleanArray(KEY_ANSWERED);
            for(int i = 0; i < mQuestionBank.length; i++)
            {
                mQuestionBank[i].setAnswered(answeredList[i]);
            }
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

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

        // place updateQuestion method here because waiting the mTrueButton and mFalseButton instantiation.
        updateQuestion();

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
        setButtonState();
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            mQuestionBank[mCurrentIndex].setAnswered(true);
            mCorrectCount++;
        } else {
            messageResId = R.string.incorrect_toast;
            mQuestionBank[mCurrentIndex].setAnswered(true);
            mIncorrectCount++;
        }
        setButtonState();

        Toast t = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        // challenge 1.11: show the toast message at top of screen.
        t.setGravity(Gravity.TOP, 0, 0);
        t.show();

        //TODO: The output result always display 0.0
        if((mCorrectCount + mIncorrectCount) >= mQuestionBank.length) {
            double correctRate = mCorrectCount / mQuestionBank.length * 100;
            Toast.makeText(this, "Correct rate: " + correctRate, Toast.LENGTH_SHORT)
                    .show();
        }
    }

    // challenge 3.7: only can answer once
    public void setButtonState() {
        if(mQuestionBank[mCurrentIndex].isAnswered() == false) {
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
        boolean [] answeredList = new boolean[mQuestionBank.length];
        for(int i = 0; i < mQuestionBank.length; i++) {
            answeredList[i] = mQuestionBank[i].isAnswered();
        }
        savedInstanceState.putBooleanArray(KEY_ANSWERED,answeredList);
    }

    //TODO: challenge 3.8： when user answered all question, show a toast message of correct percentage.

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


