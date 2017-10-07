package in.microlan.www.perfectmatrimony.authenticate.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xwray.passwordview.PasswordView;

import in.microlan.www.perfectmatrimony.R;
import in.microlan.www.perfectmatrimony.common.base.BaseActivity;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private TextView txt_register, txt_forgot_password;
    private EditText edt_username;

    //For the Validation we use Recycleview & ScrollView for UI Display
    private RecyclerView rcvValidationMessage;
    private ScrollView scvloginScroll;
    private CheckBox checkBox;
    private Context context = LoginActivity.this;
    private PasswordView password;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitView();
    }

    @SuppressLint("ServiceCast")
    @Override
    public void InitView() {
        context = this;
        txt_register = (TextView) findViewById(R.id.txt_register);
        txt_forgot_password = (TextView) findViewById(R.id.txt_forgot_password);
        password = (PasswordView) findViewById(R.id.pv_login_password);
        edt_username = (EditText) findViewById(R.id.edt_username);

        SpannableString content = new SpannableString("Registered");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        txt_register.setText(content);

        txt_register.setOnClickListener(this);
        txt_forgot_password.setOnClickListener(this);


    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.txt_register:
                startActivity(new Intent(context, SignUpActivity.class));
                break;

            case R.id.txt_forgot_password:
                startActivity(new Intent(context, ForgotPasswordActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
