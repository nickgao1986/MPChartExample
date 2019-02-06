package nickgao.com.viewpagerswitchexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import nickgao.com.viewpagerswitchexample.adapter.FeedsMainAdapter;

public class TestListActivity extends Activity implements AdapterView.OnItemClickListener {

    public static final String TAG = TestListActivity.class.getSimpleName();
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feeds_main);
        mListView = (ListView) findViewById(R.id.news_home_listview);
        mListView.setAdapter(new FeedsMainAdapter(this,getList()));
        mListView.setOnItemClickListener(this);

    }



    public ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("chat图片");
        list.add("chat图片11");
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Intent intent = new Intent(this,AnotherBarActivity.class);
                startActivity(intent);
                break;

            case 1:
                intent = new Intent(this,AnotherBarActivity11.class);
                startActivity(intent);
                break;
        }
    }
}

