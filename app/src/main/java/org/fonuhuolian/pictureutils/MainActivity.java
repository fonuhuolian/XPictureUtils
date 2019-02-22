package org.fonuhuolian.pictureutils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.fonuhuolian.xpictureutils.XPictureClipUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {

        XPictureClipUtils.startPhotoZoom(this,
                new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/1.jpg"),
                2, 1, 600, 600,getPackageName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String s = XPictureClipUtils.onActivityResultGetPath(requestCode, resultCode, data);

        Log.e("ddd", s + "---");

    }
}

