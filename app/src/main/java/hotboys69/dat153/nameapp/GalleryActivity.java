package hotboys69.dat153.nameapp;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.HashMap;

public class GalleryActivity extends AppCompatActivity {


    Integer[] images = {R.drawable.emilracerbil, R.drawable.ekrof, R.drawable.gumman};

    HashMap<Integer, String> pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        pic = new HashMap<Integer, String>();
        pic.put(R.drawable.ekrof, "Jonas");
        pic.put(R.drawable.emilracerbil, "Emil");
        pic.put(R.drawable.gumman, "Trygve");

        GridView gw = (GridView) findViewById(R.id.grid);
        ImageAdapter adapter = new ImageAdapter(this);
        adapter.map = pic;
        gw.setAdapter(adapter);



        gw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(GalleryActivity.this, (int) position+"",
                        Toast.LENGTH_SHORT).show();
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
