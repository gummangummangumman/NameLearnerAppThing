package hotboys69.dat153.nameapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_create_owner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_owner);

        Button ownerBtn = findViewById(R.id.set_owner_btn);
        final EditText nameText = findViewById(R.id.name);

        ownerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameText.getText().toString().length() > 0) {
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    SharedPreferences.Editor prefEditor = pref.edit();
                    prefEditor.putString("name", nameText.getText().toString());
                    prefEditor.commit();

                    finish();
                } else {
                    Toast.makeText(activity_create_owner.this, "Must write name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
