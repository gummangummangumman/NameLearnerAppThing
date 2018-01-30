package hotboys69.dat153.nameapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class activity_create_owner extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    public int PICK_IMAGE = 1;
    private Uri selectedImage = null;
    public ImageView imageView = null;
    private FileOutputStream fos = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_owner);

        Button ownerBtn = findViewById(R.id.set_owner_btn);
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

        final EditText nameText = findViewById(R.id.name);

        ownerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameText.getText().toString().length() > 0) {
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    SharedPreferences.Editor prefEditor = pref.edit();
                    prefEditor.putString("name", nameText.getText().toString());
                    prefEditor.commit();

                    setResult(RESULT_OK);
                    finish();
                    String ownerString = selectedImage.toString();
                    String FILENAME = "owner_image";

                    try {
                        fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                        fos.write(ownerString.getBytes());
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(activity_create_owner.this, "Must write name", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE | requestCode == CAMERA_REQUEST) {
            if (data == null) {
                Toast.makeText(this, "You need to select a picture",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            selectedImage = data.getData();
            imageView = findViewById(R.id.imageOwner);
            imageView.setImageURI(selectedImage);

        }
    }

}
