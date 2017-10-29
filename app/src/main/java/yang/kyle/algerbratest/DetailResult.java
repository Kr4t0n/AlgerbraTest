package yang.kyle.algerbratest;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailResult extends ListActivity {
    ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = this.getIntent();
        ArrayList<String> quizOrderList = intent.getStringArrayListExtra("quizOrderList");
        ArrayList<String> quizTimeList = intent.getStringArrayListExtra("quizTimeList");
        ArrayList<String> quizResultList = intent.getStringArrayListExtra("quizResultList");

        for (int i = 0; i < quizOrderList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("QuizOrder", quizOrderList.get(i));
            map.put("QuizResult", quizResultList.get(i));
            map.put("QuizTime", quizTimeList.get(i));

            list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.activity_detail_result,
                new String[]{"QuizOrder", "QuizResult", "QuizTime"},
                new int[]{R.id.tv_quizOrder, R.id.tv_quizResult, R.id.tv_quizTime});
        setListAdapter(adapter);
    }
}
