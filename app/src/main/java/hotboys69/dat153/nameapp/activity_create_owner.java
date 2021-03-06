package hotboys69.dat153.nameapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ConnectException;

import javax.xml.datatype.Duration;

public class activity_create_owner extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    public int PICK_IMAGE = 1;
    private Uri selectedImage = null;
    public ImageView imageView = null;
    private FileOutputStream fos = null;
    private FileInputStream fis = null;

    private final String FILENAME = "owner_image.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_owner);

        Button ownerBtn = findViewById(R.id.set_owner_btn);
        ImageButton addPictureButton = findViewById(R.id.imageButton);
        addPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_OPEN_DOCUMENT,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickPhoto.setType("image/*");
                startActivityForResult(pickPhoto, PICK_IMAGE);
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

        imageView = findViewById(R.id.imageOwner);


        final EditText nameText = findViewById(R.id.name);

        ownerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameText.getText().toString().length() > 0 &&
                        selectedImage != null) {
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    SharedPreferences.Editor prefEditor = pref.edit();
                    prefEditor.putString("name", nameText.getText().toString());
                    prefEditor.commit();

                    try {
                        Bitmap imgBm = HelperClass.decodeBitmap(getBaseContext(), selectedImage);

                        fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                        imgBm.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(),
                                e.toString(),
                                Toast.LENGTH_LONG).show();
                    }

                    setResult(RESULT_OK);
                    finish();

                } else {
                    Toast.makeText(activity_create_owner.this, "Must write name and select image", Toast.LENGTH_SHORT).show();
                }
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

            imageView = findViewById(R.id.imageOwner);
            imageView.setImageURI(selectedImage);

        } else if(requestCode == CAMERA_REQUEST) {
            Bitmap bilde = (Bitmap) data.getExtras().get("data");
            selectedImage = HelperClass.saveToInternalStorage(getBaseContext(), bilde, FILENAME);

            imageView = findViewById(R.id.imageOwner);

            imageView.setImageURI(selectedImage);
        }
    }

}
