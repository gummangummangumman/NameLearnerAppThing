package hotboys69.dat153.nameapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import java.io.*;
import android.graphics.*;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.EditText;


public class AddPersonActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    public int PICK_IMAGE = 1;
    public ImageView imageView = null;
    public EditText nameText = null;
    private Bitmap bitmap = null;
    private Uri selectedImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        ImageButton addPictureButton = findViewById(R.id.imageButton);
        addPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "Select Picture"), PICK_IMAGE);
            }

        });



        ImageButton addPictureFromCameraButton = findViewById(R.id.cameraButton);
        addPictureFromCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

            }

        });


        nameText = findViewById(R.id.nameToAdd);
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();

                //Person newPerson = new Person(name, bitmap);
                Person newPerson = new Person(name,selectedImage);
                Data.persons.add(newPerson);

                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.addLayout),
                        name+" added", Snackbar.LENGTH_SHORT);
                mySnackbar.show();
            }

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (data == null) {

                Toast.makeText(this, "You need to select a picture",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            selectedImage = data.getData();
           // data.putExtra("URI", selectedImage.toString());


                // Show the Selected Image on ImageView
                imageView = findViewById(R.id.imageView2);
                imageView.setImageURI(selectedImage);


        }
        else {
            selectedImage = data.getData();
            imageView = findViewById(R.id.imageView2);
            //Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageURI(selectedImage);
        } {

        }

    }



}

