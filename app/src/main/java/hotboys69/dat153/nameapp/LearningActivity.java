package hotboys69.dat153.nameapp;

import android.content.Context;
import android.graphics.Bitmap;
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

public class LearningActivity extends AppCompatActivity {

    int score;
    Person p;
    Data d;
    ImageView img;
    Bitmap bi;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        final EditText et = findViewById(R.id.nameGuess);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        p = Data.getRandomPerson();

        img = findViewById(R.id.imageView);
        img.setImageURI(p.getPic());

        final TextView scoreView = findViewById(R.id.score);
        score = 0;
        scoreView.setText("Score: " + score);



        Button guessButton = findViewById(R.id.guessButton);
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guess = et.getText().toString();


                if(guess.equalsIgnoreCase(p.getName())) {
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

                p = Data.getRandomPerson();

                bi = p.getBitmap();
                if(bi==null){
                    try {
                        bi = HelperClass.decodeBitmap(context,p.getPic());
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


}
