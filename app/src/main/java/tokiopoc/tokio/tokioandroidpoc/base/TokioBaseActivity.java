package tokiopoc.tokio.tokioandroidpoc.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import java.util.HashMap;
import java.util.Map;

/**
 * BaseActivity of the app for different component
 *
 * @author Utkarsh Sundaram
 */
public abstract class TokioBaseActivity extends AppCompatActivity {

    private String TAG = "TokioBaseActivity";
    protected View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        setInitView();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    public static Map<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        return headers;
    }
    protected abstract int getLayout();

    public abstract void setInitView();

    protected void hideViews() {
    }

    protected void setView(View view) {
        this.view = view;
    }
}
