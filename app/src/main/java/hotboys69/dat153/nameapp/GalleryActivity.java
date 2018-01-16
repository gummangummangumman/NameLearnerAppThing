package hotboys69.dat153.nameapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.HashMap;

public class GalleryActivity extends AppCompatActivity {

/*
    String[] strings = {"kunne ikke","laste inn fra","stringarray i xml"};
    HashMap<Integer, String> pic;

*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        /*
        pic = new HashMap<String, Integer>();
        pic.put(R.drawable.ekrof, "Jonas");
        pic.put(R.drawable.emilracerbil, "Emil");
        pic.put(R.drawable.gumman, "Trygve");

        strings = getResources().getStringArray(R.array.names);
*/
        GridView gw = (GridView) findViewById(R.id.grid);
        gw.setAdapter(new ImageAdapter(this));

        gw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(GalleryActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });



    }
}
