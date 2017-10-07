package in.microlan.www.perfectmatrimony.authenticate.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import in.microlan.www.perfectmatrimony.R;
import in.microlan.www.perfectmatrimony.common.base.BaseActivity;



public class LoginActivity extends BaseActivity implements  View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    public EditText edtCompany, edtUsername, edtPassword;
    private int mCurrentState;
    private Context context;
    private String strCompanyId, strUsername, strPassword;
    private View viewProgressBar;
    private Button btnSignIn;
   //For the Validation we use Recycleview & ScrollView for UI Display
    private RecyclerView rcvValidationMessage;
    private ScrollView scvloginScroll;
    private TextView textView_signwithfingerID,textView_forgot_login;
    private CheckBox checkBox;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitView();
    }

    @SuppressLint("ServiceCast")
    @Override
    public void InitView() {
        context = this;

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
