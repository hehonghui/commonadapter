package simple.com.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.simple.commonadapter.viewholders.GodViewHolder;
import com.simple.commonadapter.ListViewAdapter;
import com.simple.commonadapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initListView();
        initRecyclerView();
    }

    private List<String> mockDatas() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            datas.add("user - " + i);
        }
        return datas;
    }


    /**
     *
     */
    private void initListView() {
        ListView listView = (ListView) findViewById(R.id.listview);
        final ListViewAdapter<String> adapter = new ListViewAdapter<String>(R.layout.list_item_type_1) {
            @Override
            protected void onBindData(GodViewHolder viewHolder, int position, String item) {
                // 设置文本内容
                viewHolder.setText(R.id.textview, item);
            }
        };
        // 添加数据
        adapter.addItems( mockDatas() );
        listView.setAdapter(adapter);
        // 设置ListView的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Click List : " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     *
     */
    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // 线性
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        // 初始化adapter
        final FeedRecyclerAdapter adapter = new FeedRecyclerAdapter(mockDatas());
        // 设置RecyclerView的点击事件
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "Click Recycler : "
                        + adapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
