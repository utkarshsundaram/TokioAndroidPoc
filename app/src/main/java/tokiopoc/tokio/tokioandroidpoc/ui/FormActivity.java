package tokiopoc.tokio.tokioandroidpoc.ui;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import tokiopoc.tokio.tokioandroidpoc.R;
import tokiopoc.tokio.tokioandroidpoc.base.TokioBaseActivity;
import tokiopoc.tokio.tokioandroidpoc.network.ApiClientInterface;

public class FormActivity extends TokioBaseActivity implements View.OnClickListener {
    private int mYear, mMonth, mDay;
    private EditText editTextBirthDate;
    private Button buttonNext;
    private static final int MEDIA_RECORDER_REQUEST = 1;
    private final String[] requiredPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };
    private ApiClientInterface mNetworkApi;

    @Override
    protected int getLayout() {
        return R.layout.activity_form;
    }

    @Override
    public void setInitView() {
        if (areCameraPermissionGranted()) {

        } else {
            requestCameraPermissions();
        }

        editTextBirthDate = findViewById(R.id.edtBirthDate);
        buttonNext = findViewById(R.id.button_continue);
        editTextBirthDate.setOnClickListener(this);
        buttonNext.setOnClickListener(this);

    }

    private void requestCameraPermissions() {
        ActivityCompat.requestPermissions(
                this,
                requiredPermissions,
                MEDIA_RECORDER_REQUEST);
    }

    private boolean areCameraPermissionGranted() {
        for (String permission : requiredPermissions) {
            if (!(ActivityCompat.checkSelfPermission(this, permission) ==
                    PackageManager.PERMISSION_GRANTED)) {
                return false;
            }
        }
        return true;

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (MEDIA_RECORDER_REQUEST != requestCode) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }

        boolean areAllPermissionsGranted = true;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                areAllPermissionsGranted = false;
                break;
            }
        }

        if (areAllPermissionsGranted) {

        } else {
            // User denied one or more of the permissions, without these we cannot record
            // Show a toast to inform the user.
            Toast.makeText(getApplicationContext(),
                    "App need the camera permission",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edtBirthDate: {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                editTextBirthDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            }
            case R.id.button_continue: {
                Intent intent = new Intent(FormActivity.this, CameraActivity.class);
                startActivity(intent);
                break;

            }
        }

    }
}
