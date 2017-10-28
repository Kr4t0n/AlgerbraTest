package yang.kyle.algerbratest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {
    EditText txt_studentName, txt_universityNo;
    Button btn_startQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_startQuiz = (Button)findViewById(R.id.btn_startQuiz);
        txt_studentName = (EditText)findViewById(R.id.txt_studentName);
        txt_universityNo = (EditText)findViewById(R.id.txt_universityNo);

        // Register the startQuiz button to click listener
        // Whenever the button is clicked, onClick is called
        btn_startQuiz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.btn_startQuiz) {
            // Do Something
        }
    }
}
