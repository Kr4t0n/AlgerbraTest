package yang.kyle.algerbratest;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
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
        if (v.getId() == R.id.btn_startQuiz) {
            String studentName = txt_studentName.getText().toString();
            String universityNo = txt_universityNo.getText().toString();
            
            if (studentName.matches("") || universityNo.matches("")) {
                new AlertDialog.Builder(this)
                        .setMessage("Please fill in the personal information!")
                        .setTitle("Personal Information Error!")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Just dismiss this message
                            }
                        })
                        .show();
            }
            else {
                Intent intent = new Intent(getBaseContext(), QuizActivity.class);
                intent.putExtra("studentName", studentName);
                intent.putExtra("universityNo", universityNo);
                startActivity(intent);
            }
        }
    }
}
