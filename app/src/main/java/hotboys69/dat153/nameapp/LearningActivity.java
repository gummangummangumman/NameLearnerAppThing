package hotboys69.dat153.nameapp;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LearningActivity extends AppCompatActivity {

    int score;
   // TextView scoreView = (TextView) findViewById(R.id.score);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView scoreView = (TextView) findViewById(R.id.score);
        score = 0;
        scoreView.setText("Score: " + score);


        Button guessButton = findViewById(R.id.guessButton);
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(view.getContext(), "yo this aint implemented", 1000);
                toast.show();

                //Om det är rätt svar ökas score med 1
                score++;
                scoreView.setText("Score: "+ score);
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
