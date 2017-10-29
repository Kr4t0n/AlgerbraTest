package yang.kyle.algerbratest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class QuizActivity extends Activity implements View.OnClickListener {
    TextView tv_algQuiz, tv_checkResult;
    EditText txt_ans1, txt_ans2;
    Button btn_submitAns, btn_next;
    // The parameter of the equations
    int a, b, c;
    // To count the number of quiz completed
    int quizCount = 0;

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

        btn_submitAns.setOnClickListener(this);
        btn_next.setOnClickListener(this);

        algebraTest();
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
    }

    public void algebraVerify() {
        if (quizCount < 5) {
            Double ans;
            Double correctAns = (-b) / (double)a;
            String ansVerifyResult;

            try {
                ans = Double.parseDouble(txt_ans1.getText().toString());
                if (areEqual(ans, correctAns)) {
                    ansVerifyResult = "Correct!";
                }
                else {
                    ansVerifyResult = "Wrong! The correct result is " + Double.toString(Math.round(correctAns * 100) / 100.0d) + " !";
                }
                tv_checkResult.setText(ansVerifyResult);
                btn_submitAns.setEnabled(false);
            }
            catch (NumberFormatException e) {
                showNumMessage();
            }
        }
        else if (quizCount < 10) {
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
                    }
                    else {
                        ansVerifyResult = "Wrong! The correct result is " + Double.toString(Math.round(correctAns1 * 100) / 100.0d) + " !";
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
                    if (ans1 < ans2) {
                        Double temp = ans1;
                        ans1 = ans2;
                        ans2 = temp;
                    }
                    if (areEqual(ans1, correctAns1) && areEqual(ans2, correctAns2)) {
                        ansVerifyResult = "Correct!";
                    }
                    else {
                        ansVerifyResult = "Wrong! The correct result is " + Double.toString(Math.round(correctAns1 * 100) / 100.0d) + " and " + Double.toString(Math.round(correctAns2 * 100) / 100.d) + " !";
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

    @Override
    public void onClick(View v) {
        // TODO Quiz method sub
        if (v.getId() == R.id.btn_submitAns) {
            algebraVerify();
        }
        if (v.getId() == R.id.btn_next) {
            if (quizCount < 10) {
                btn_submitAns.setEnabled(true);
                algebraTest();
            }
        }
    }
}
