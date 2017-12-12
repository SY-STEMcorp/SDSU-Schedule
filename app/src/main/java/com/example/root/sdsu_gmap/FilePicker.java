package com.example.root.sdsu_gmap;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.io.File;

/**
 * Created by root on 12/13/17.
 */

public class FilePicker extends Activity {

    private static final int FILE_PICKER_CODE = 0;

    private void showFilePicker(Context context)
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);


        try {
            startActivityForResult(Intent.createChooser(intent, "Upload a file"), FILE_PICKER_CODE);
        }
        catch (ActivityNotFoundException e)
        {
            Toast.makeText(this, "Please install a file manager", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode)
        {
            case FILE_PICKER_CODE:
                if(resultCode == RESULT_OK)
                {
                    Uri uri = data.getData();
                    String path = uri.toString();
                }
                break;
        }
    }
}
