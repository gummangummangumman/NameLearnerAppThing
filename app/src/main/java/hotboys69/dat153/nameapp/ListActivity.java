package hotboys69.dat153.nameapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

        private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView la = findViewById(R.id.listView);

        ArrayList<String> names = new ArrayList<String>();
        for(Person p : Data.persons){
            names.add(p.getName());
        }

        final AnimationSet mAnimationSet = new AnimationSet(false);
        final Animation fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        final Animation fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        fadeout.setStartOffset(1500);
        mAnimationSet.addAnimation(fadein);
        mAnimationSet.addAnimation(fadeout);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);

        la.setAdapter(adapter);

        la.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ImageView img = (ImageView) findViewById(R.id.listPicture);

                img.setImageURI(Data.persons.get(i).getPic());
                img.startAnimation(mAnimationSet);
                img.setVisibility(View.GONE);

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
