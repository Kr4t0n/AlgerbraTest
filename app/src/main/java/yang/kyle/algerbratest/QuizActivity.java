package yang.kyle.algerbratest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class QuizActivity extends Activity implements View.OnClickListener {
    TextView tv_algQuiz, tv_checkResult;
    EditText txt_ans1, txt_ans2;
    Button btn_submitAns, btn_next;
    // The parameter of the equations
    int a, b, c;
    // To count the number of quiz completed
    int quizCount = 0;
    // Initialize the quiz order
    String[] quizOrder = new String[10];
    // Store the time each equation spent
    Long timeA, timeB, duration;
    String[] quizDuration = new String[10];
    String[] quizTime = new String[10];
    // Store the result of each equation
    String[] quizResult = new String[10];
    // String inside the intent
    String studentName, universityNo;
    // Conclude the correct, wrong and skipped number;
    int correctNum, wrongNum, skipNum;
    // ArrayList to transfer the data of quizTime and quizResult
    ArrayList<String> quizTimeList, quizResultList, quizOrderList, quizDurationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tv_algQuiz = (TextView)findViewById(R.id.tv_algQuiz);
        tv_checkResult = (TextView)findViewById(R.id.tv_checkResult);
        txt_ans1 = (EditText)findViewById(R.id.txt_ans1);
        txt_ans2 = (EditText)findViewById(R.id.txt_ans2);
        btn_submitAns = (Button)findViewById(R.id.btn_submitAns);
        btn_next = (Button)findViewById(R.id.btn_next);

        Intent intent = this.getIntent();
        studentName = intent.getStringExtra("studentName");
        universityNo = intent.getStringExtra("universityNo");

        btn_submitAns.setOnClickListener(this);
        btn_next.setOnClickListener(this);

        algebraTest();
        timeA = System.currentTimeMillis();
    }

    public int randGenerator() {
        Random rand = new Random();
        int value = rand.nextInt(199) - 99;

        return value;
    }

    public void algebraTest() {
        if (quizCount < 5) {
            String quiz;

            do {
                a = randGenerator();
                b = randGenerator();
            } while(a == 0);
            if (b > 0) {
                quiz = Integer.toString(a) + "x + " + Integer.toString(b) + " = 0, what is x?";
            }
            else if (b == 0) {
                quiz = Integer.toString(a) + "x = 0, what is x?";
            }
            else {
                quiz = Integer.toString(a) + "x - " + Integer.toString(-b) + " = 0, what is x?";
            }

            tv_algQuiz.setText(quiz);
            tv_checkResult.setText("Submit to reveal your answer!");
            txt_ans1.setEnabled(true);
            txt_ans1.setText("");
            txt_ans1.setHint("2 decimal allowed for non integer.");
            txt_ans2.setEnabled(false);
            quizCount ++;
        }
        else if (quizCount < 10) {
            String quiz;

            do {
                a = randGenerator();
                b = randGenerator();
                c = randGenerator();
            } while(a == 0 || (b * b - 4 * a * c) < 0);
            quiz = Integer.toString(a) + "x²";
            if (b > 0) {
                quiz += " + " + Integer.toString(b) + "x";
            }
            else {
                quiz += " - " + Integer.toString(-b) + "x";
            }
            if (c == 0) {
                quiz += " = 0, what is x?";
            }
            else if (c > 0) {
                quiz += " + " + Integer.toString(c) + " = 0, what is x?";
            }
            else {
                quiz += " - " + Integer.toString(-c) + " = 0, what is x?";
            }

            tv_algQuiz.setText(quiz);
            tv_checkResult.setText("Submit to reveal your answer!");
            txt_ans1.setEnabled(true);
            txt_ans1.setText("");
            txt_ans1.setHint("2 decimal allowed for non integer.");
            if ((b * b - 4 * a * c) > 0) {
                txt_ans2.setEnabled(true);
                txt_ans2.setText("");
                txt_ans2.setHint("2 decimal allowed for non integer.");
            }
            else {
                txt_ans2.setEnabled(false);
                txt_ans2.setText("");
            }
            quizCount++;
        }
        quizOrder[quizCount - 1] = Integer.toString(quizCount);
    }

    public void algebraVerify() {
        if (quizCount - 1 < 5) {
            Double ans;
            Double correctAns = (-b) / (double)a;
            String ansVerifyResult;

            try {
                ans = Double.parseDouble(txt_ans1.getText().toString());
                if (areEqual(ans, correctAns)) {
                    ansVerifyResult = "Correct!";
                    quizResult[quizCount - 1] = "Correct";
                }
                else {
                    ansVerifyResult = "Wrong! The correct result is " + Double.toString(Math.round(correctAns * 100) / 100.0d) + " !";
                    quizResult[quizCount - 1] = "Wrong";
                }
                tv_checkResult.setText(ansVerifyResult);
                btn_submitAns.setEnabled(false);
            }
            catch (NumberFormatException e) {
                showNumMessage();
            }
        }
        else if (quizCount - 1 < 10) {
            Double ans1, ans2;
            int delta = b * b - 4 * a * c;
            Double correctAns1 = (-b + Math.sqrt((double) delta)) / (2 * a);
            Double correctAns2 = (-b - Math.sqrt((double) delta)) / (2 * a);
            String ansVerifyResult;

            if (delta == 0) {
                try {
                    ans1 = Double.parseDouble(txt_ans1.getText().toString());
                    if (areEqual(ans1, correctAns1)) {
                        ansVerifyResult = "Correct!";
                        quizResult[quizCount - 1] = "Correct";
                    }
                    else {
                        ansVerifyResult = "Wrong! The correct result is " + Double.toString(Math.round(correctAns1 * 100) / 100.0d) + " !";
                        quizResult[quizCount - 1] = "Wrong";
                    }
                    tv_checkResult.setText(ansVerifyResult);
                    btn_submitAns.setEnabled(false);
                }
                catch (NumberFormatException e) {
                    showNumMessage();
                }
            }
            else {
                try {
                    ans1 = Double.parseDouble(txt_ans1.getText().toString());
                    ans2 = Double.parseDouble(txt_ans2.getText().toString());
                    if ((a > 0 && ans1 < ans2) || (a < 0 && ans1 > ans2)){
                        Double temp = ans1;
                        ans1 = ans2;
                        ans2 = temp;
                    }

                    if (areEqual(ans1, correctAns1) && areEqual(ans2, correctAns2)) {
                        ansVerifyResult = "Correct!";
                        quizResult[quizCount - 1] = "Correct";
                    }
                    else {
                        ansVerifyResult = "Wrong! The correct result is " + Double.toString(Math.round(correctAns1 * 100) / 100.0d) + " and " + Double.toString(Math.round(correctAns2 * 100) / 100.d) + " !";
                        quizResult[quizCount - 1] = "Wrong";
                    }
                    tv_checkResult.setText(ansVerifyResult);
                    btn_submitAns.setEnabled(false);
                }
                catch (NumberFormatException e) {
                    showNumMessage();
                }
            }
        }
    }

    public boolean areEqual(Double a, Double b) {
        if (Math.abs(a - b) < 1e-2) {
            return true;
        }
        else {
            return false;
        }
    }

    protected void showNumMessage() {
        new AlertDialog.Builder(this)
                .setMessage("Please answer with the correct format!")
                .setTitle("Format Error!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Just dismiss this message
                    }
                })
                .show();
    }

    protected void recordDuration() {
        timeB = System.currentTimeMillis();
        duration = timeB - timeA;
        quizDuration[quizCount - 1] = Long.toString(duration);
        quizTime[quizCount - 1] = String.format("%02d min, %02d sec",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }

    protected void checkAnsStatus() {
        if (btn_submitAns.isEnabled()) {
            quizResult[quizCount - 1] = "Not Answered";
        }
    }

    protected int checkStatus(String status) {
        int statusCount = 0;
        for (int i = 0; i < 10; i++) {
            if (quizResult[i] == status) {
                statusCount++;
            }
        }

        return statusCount;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submitAns) {
            algebraVerify();
        }
        if (v.getId() == R.id.btn_next) {
            if (quizCount < 10) {
                recordDuration();
                checkAnsStatus();
                btn_submitAns.setEnabled(true);
                algebraTest();
                timeA = System.currentTimeMillis();
            }
            else {
                recordDuration();
                checkAnsStatus();

                correctNum = checkStatus("Correct");
                wrongNum = checkStatus("Wrong");
                skipNum = checkStatus("Not Answered");

                quizTimeList = new ArrayList<String>(Arrays.asList(quizTime));
                quizResultList = new ArrayList<String>(Arrays.asList(quizResult));
                quizOrderList = new ArrayList<String>(Arrays.asList(quizOrder));
                quizDurationList = new ArrayList<String>(Arrays.asList(quizDuration));

                Intent intent = new Intent(getBaseContext(), QuizResult.class);
                intent.putExtra("studentName", studentName);
                intent.putExtra("universityNo", universityNo);
                intent.putExtra("correctNum", correctNum);
                intent.putExtra("wrongNum", wrongNum);
                intent.putExtra("skipNum", skipNum);
                intent.putStringArrayListExtra("quizOrderList", quizOrderList);
                intent.putStringArrayListExtra("quizTimeList", quizTimeList);
                intent.putStringArrayListExtra("quizResultList", quizResultList);
                intent.putStringArrayListExtra("quizDurationList", quizDurationList);

                startActivity(intent);
            }
        }
    }
}
