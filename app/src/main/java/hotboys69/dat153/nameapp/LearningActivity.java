package hotboys69.dat153.nameapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class LearningActivity extends AppCompatActivity {

    int score;
    Person p;
    Data d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        p = d.getRandomPerson();

        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageURI(p.getPic());

        final TextView scoreView = (TextView) findViewById(R.id.score);
        score = 0;
        scoreView.setText("Score: " + score);


        Button guessButton = findViewById(R.id.guessButton);
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = (EditText) findViewById(R.id.nameGuess);
                String guess = et.getText().toString();

                if(guess.equals(p.getName())) {
                    Toast toast = Toast.makeText(view.getContext(), "Correct!", 1000);
                    toast.show();
                    score++;
                    scoreView.setText("Score: " + score);

                }else{
                    Toast toast = Toast.makeText(view.getContext(), "Wrong!", 1000);
                    toast.show();
                    score = 0;
                    scoreView.setText("Score: " + score);
                }

                p = d.getRandomPerson();

                ImageView img = (ImageView) findViewById(R.id.imageView);
                Bitmap bi = p.getBitmap();
                if(bi==null){
                    try {
                        bi = decodeBitmap(p.getPic());
                    } catch (Exception e) {}
                }
                img.setImageBitmap(bi);
                img.setImageURI(p.getPic());
                et.setText("");

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


    public Bitmap decodeBitmap(Uri selectedImage) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        final int REQUIRED_SIZE = 100;

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
    }
}
