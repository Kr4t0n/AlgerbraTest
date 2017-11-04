package yang.kyle.algerbratest;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class QuizResult extends Activity implements View.OnClickListener {
    TextView tv_studentName, tv_universityNo, tv_correctNum, tv_wrongNum, tv_skipNum;
    TextView tv_linearTime, tv_quadraticTime;
    int averageLinearTime, averageQuadraticTime;
    Button btn_showDetail, btn_restartQuiz, btn_quit;
    // String inside the intent
    String studentName, universityNo;
    // Integer inside the intent
    int correctNum, wrongNum, skipNum;
    // ArrayList inside the intent
    ArrayList<String> quizTimeList, quizResultList, quizOrderList, quizDurationList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        tv_studentName = (TextView)findViewById(R.id.tv_studentName);
        tv_universityNo = (TextView)findViewById(R.id.tv_universityNo);
        tv_correctNum = (TextView)findViewById(R.id.tv_correctNum);
        tv_wrongNum = (TextView)findViewById(R.id.tv_wrongNum);
        tv_skipNum = (TextView)findViewById(R.id.tv_skipNum);
        tv_linearTime = (TextView)findViewById(R.id.tv_linearTime);
        tv_quadraticTime = (TextView)findViewById(R.id.tv_quadraticTime);
        btn_showDetail = (Button)findViewById(R.id.btn_showDetail);
        btn_restartQuiz = (Button)findViewById(R.id.btn_restartQuiz);
        btn_quit = (Button)findViewById(R.id.btn_quit);

        Intent intent = this.getIntent();
        studentName = intent.getStringExtra("studentName");
        universityNo = intent.getStringExtra("universityNo");
        correctNum = intent.getIntExtra("correctNum", 0);
        wrongNum = intent.getIntExtra("wrongNum", 0);
        skipNum = intent.getIntExtra("skipNum", 0);
        quizOrderList = intent.getStringArrayListExtra("quizOrderList");
        quizTimeList = intent.getStringArrayListExtra("quizTimeList");
        quizResultList = intent.getStringArrayListExtra("quizResultList");
        quizDurationList = intent.getStringArrayListExtra("quizDurationList");

        averageLinearTime = calAverageDuration(quizResultList, quizDurationList, 0, 5);
        averageQuadraticTime = calAverageDuration(quizResultList, quizDurationList, 5, 10);
        tv_linearTime.setText("Average time spent on Linear equations: \n\t" + intToTime(averageLinearTime));
        tv_quadraticTime.setText("Average time spent on Quadratic equations: \n\t" + intToTime(averageQuadraticTime));

        tv_studentName.setText("Student Name: " + studentName);
        tv_universityNo.setText("University No: " + universityNo);
        tv_correctNum.setText("Correct Num: " + Integer.toString(correctNum));
        tv_wrongNum.setText("Wrong Num: " + Integer.toString(wrongNum));
        tv_skipNum.setText("Skipped Num: " + Integer.toString(skipNum));

        btn_showDetail.setOnClickListener(this);
        btn_restartQuiz.setOnClickListener(this);
        btn_quit.setOnClickListener(this);
    }

    public int calAverageDuration(ArrayList<String> quizResultList, ArrayList<String> quizDurationList, int startPoint, int endPoint) {
        int i;
        int sumCount = 0;
        int sumTime = 0;

        for (i = startPoint; i < endPoint; i++) {
            if (quizResultList.get(i) != "Not Answered") {
                sumTime += Integer.parseInt(quizDurationList.get(i));
                sumCount += 1;
            }
        }

        return sumTime / sumCount;
    }

    public String intToTime(int time) {
        return String.format("%02d min, %02d sec",
                TimeUnit.MILLISECONDS.toMinutes(time),
                TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_showDetail) {
            Intent intent = new Intent(getBaseContext(), DetailResult.class);
            intent.putStringArrayListExtra("quizOrderList", quizOrderList);
            intent.putStringArrayListExtra("quizTimeList", quizTimeList);
            intent.putStringArrayListExtra("quizResultList", quizResultList);

            startActivity(intent);
        }
        else if (v.getId() == R.id.btn_restartQuiz) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            finish();
            startActivity(intent);
        }
        else if (v.getId() == R.id.btn_quit) {
            finishAffinity();
        }
    }
}
