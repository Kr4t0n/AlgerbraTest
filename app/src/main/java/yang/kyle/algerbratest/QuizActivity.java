package yang.kyle.algerbratest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class QuizActivity extends Activity implements View.OnClickListener {
    TextView tv_algQuiz, tv_checkResult;
    EditText txt_ans1, txt_ans2;
    Button btn_submitAns, btn_next;

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
    }

    @Override
    public void onClick(View v) {
        // TODO Quiz method sub
        if (v.getId() == R.id.btn_submitAns) {
            // Do something
        }
        if (v.getId() == R.id.btn_next) {
            // Do something
        }
    }
}
