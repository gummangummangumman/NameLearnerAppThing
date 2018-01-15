package hotboys69.dat153.nameapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goToListPage();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goToGalleryPage();
            }
        });

        Button learnButton = findViewById(R.id.button3);
        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goToLearningPage();
            }
        });
    }


    public void goToListPage()
    {
        Intent listPageIntent = new Intent(this, ListActivity.class);
        startActivity(listPageIntent);
    }

    public void goToGalleryPage()
    {
        Intent galleryPageIntent = new Intent(this, GalleryActivity.class);
        startActivity(galleryPageIntent);
    }

    public void goToLearningPage()
    {
        Intent learningPageIntent = new Intent(this, LearningActivity.class);
        startActivity(learningPageIntent);
    }
}
