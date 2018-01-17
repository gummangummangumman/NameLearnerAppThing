package hotboys69.dat153.nameapp;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ListActivity extends AppCompatActivity {



    //String[] strings = {"kunne ikke","laste inn fra","stringarray i xml"};
    public HashMap<String, Integer> pictures;
    public ArrayList<String> names;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        pictures = new HashMap<String, Integer>();
        pictures.put("Jonas", R.drawable.ekrof);
        pictures.put("Emil", R.drawable.emilracerbil);
        pictures.put("Trygve", R.drawable.gumman);

        names = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.names)));

        //strings = getResources().getStringArray(R.array.names);

        ListView la = (ListView) findViewById(R.id.listView);

        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);

        la.setAdapter(adapter);

        la.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast toast = new Toast(view.getContext());
                ImageView img = new ImageView(view.getContext());
                img.setImageResource(pictures.get(names.get(i)));
                //img.setImageResource(pictures.get(strings[i]));
                toast.setView(img);
                toast.show();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
