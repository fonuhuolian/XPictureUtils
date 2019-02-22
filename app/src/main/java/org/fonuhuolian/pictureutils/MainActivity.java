package org.fonuhuolian.pictureutils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.fonuhuolian.xpictureutils.XPicture;
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
                2, 1, 600, 600, "org.fonuhuolian.pictureutils.fileprovider");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == XPicture.REQUEST_CLIP && resultCode == RESULT_OK) {
            String s = XPictureClipUtils.obtainPathResult(data);
            Log.e("ddd", s + "---");
        }
    }
}

