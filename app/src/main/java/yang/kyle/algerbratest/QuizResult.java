package yang.kyle.algerbratest;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuizResult extends ListActivity {
    ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = this.getIntent();
        ArrayList<String> quizOrder = intent.getStringArrayListExtra("quizOrder");
        ArrayList<String> quizStauts = intent.getStringArrayListExtra("quizStatus");
        ArrayList<String> quizDuration = intent.getStringArrayListExtra("quizDuration");

        for (int i = 0; i < quizOrder.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("QuizOrder", quizOrder.get(i));
            map.put("QuizStatus", quizOrder.get(i));
            map.put("QuizDuration", quizDuration.get(i));

            list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.activity_quiz_result,
                                                  new String[]{"QuizOrder", "QuizStatus", "QuizDuration"},
                                                  new int[]{R.id.tv_quizOrder, R.id.tv_quizStatus, R.id.tv_quizDuration});
        setListAdapter(adapter);
    }
}
