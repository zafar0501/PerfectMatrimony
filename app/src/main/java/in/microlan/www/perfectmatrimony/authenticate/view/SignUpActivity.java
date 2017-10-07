package in.microlan.www.perfectmatrimony.authenticate.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import in.microlan.www.perfectmatrimony.R;
import in.microlan.www.perfectmatrimony.common.base.BaseActivity;


public class SignUpActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private TextView tool_title;
    private BottomNavigationView bnvFooter;
    private Button btn_signup;
    private Context context = SignUpActivity.this;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitView();
    }

    @Override
    public void InitView() {

        btn_signup = (Button) findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_signup;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {


        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signup: {
                startActivity(new Intent(context, LoginActivity.class));
            }
        }
    }
}
