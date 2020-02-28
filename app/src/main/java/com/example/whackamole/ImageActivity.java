package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;



public class ImageActivity extends AppCompatActivity {

    private RadioButton molePic;
    private RadioButton rickPic;
    private RadioButton vaderPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        molePic = findViewById(R.id.molePic);
        rickPic = findViewById(R.id.rickPic);
        vaderPic = findViewById(R.id.vaderPic);
        Intent i = getIntent();
        int image = i.getIntExtra("IMAGE", 1);
        if(image == 1){
            molePic.setChecked(true);
        }else if(image == 2){
            rickPic.setChecked(true);
        }else if(image == 3){
            vaderPic.setChecked(true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int image;
        if (molePic.isChecked()) {
            image = 1;
        } else if (rickPic.isChecked()) {
            image = 2;
        } else {
            image = 3;
        }
        Intent i = new Intent();
        i.putExtra("IMAGE", image);
        setResult(RESULT_OK, i);
        finish();
    }
}
