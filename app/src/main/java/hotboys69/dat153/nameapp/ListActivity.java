package hotboys69.dat153.nameapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

public class ListActivity extends AppCompatActivity {



    String[] strings = {"kunne ikke","laste inn fra","stringarray i xml"};
    HashMap<String, Integer> pictures;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        pictures = new HashMap<String, Integer>();
        pictures.put("Jonas", R.drawable.ekrof);
        pictures.put("Emil", R.drawable.emilracerbil);
        pictures.put("Trygve", R.drawable.gumman);

        strings = getResources().getStringArray(R.array.names);

        ListView la = (ListView) findViewById(R.id.listView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);

        la.setAdapter(adapter);

        la.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast toast = new Toast(view.getContext());
                ImageView img = new ImageView(view.getContext());
                img.setImageResource(pictures.get(strings[i]));
                toast.setView(img);
                toast.show();
            }
        });

    }
}
