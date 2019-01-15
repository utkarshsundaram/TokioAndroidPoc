package tokiopoc.tokio.tokioandroidpoc.ui;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;
import tokiopoc.tokio.tokioandroidpoc.R;
import tokiopoc.tokio.tokioandroidpoc.base.TokioBaseActivity;

public class FormActivity extends TokioBaseActivity implements View.OnClickListener {
    private int mYear, mMonth, mDay;
    private EditText editTextBirthDate;
    private Button buttonNext;

    @Override
    protected int getLayout() {
        return R.layout.activity_form;
    }

    @Override
    public void setInitView() {
        editTextBirthDate = findViewById(R.id.edtBirthDate);
        buttonNext = findViewById(R.id.button_continue);
        editTextBirthDate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (view == editTextBirthDate) {
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
        }

        if(view == buttonNext){

        }
    }
}
