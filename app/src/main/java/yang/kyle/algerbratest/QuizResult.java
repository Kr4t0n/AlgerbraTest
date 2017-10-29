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

public class QuizResult extends Activity implements View.OnClickListener {
    TextView tv_studentName, tv_universityNo, tv_correctNum, tv_wrongNum, tv_skipNum;
    Button btn_showDetail;
    // String inside the intent
    String studentName, universityNo;
    // Integer inside the intent
    int correctNum, wrongNum, skipNum;
    // ArrayList inside the intent
    ArrayList<String> quizTimeList, quizResultList, quizOrderList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        tv_studentName = (TextView)findViewById(R.id.tv_studentName);
        tv_universityNo = (TextView)findViewById(R.id.tv_universityNo);
        tv_correctNum = (TextView)findViewById(R.id.tv_correctNum);
        tv_wrongNum = (TextView)findViewById(R.id.tv_wrongNum);
        tv_skipNum = (TextView)findViewById(R.id.tv_skipNum);
        btn_showDetail = (Button)findViewById(R.id.btn_showDetail);

        Intent intent = this.getIntent();
        studentName = intent.getStringExtra("studentName");
        universityNo = intent.getStringExtra("universityNo");
        correctNum = intent.getIntExtra("correctNum", 0);
        wrongNum = intent.getIntExtra("wrongNum", 0);
        skipNum = intent.getIntExtra("skipNum", 0);
        quizOrderList = intent.getStringArrayListExtra("quizOrderList");
        quizTimeList = intent.getStringArrayListExtra("quizTimeList");
        quizResultList = intent.getStringArrayListExtra("quizResultList");


        tv_studentName.setText("Student Name: " + studentName);
        tv_universityNo.setText("University No: " + universityNo);
        tv_correctNum.setText("Correct Num: " + Integer.toString(correctNum));
        tv_wrongNum.setText("Wrong Num: " + Integer.toString(wrongNum));
        tv_skipNum.setText("Skipped Num: " + Integer.toString(skipNum));

        btn_showDetail.setOnClickListener(this);
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
    }
}
