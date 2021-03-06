package tech.blur.eventhub.features.auth.signin.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tech.blur.eventhub.features.BaseActivity;
import tech.blur.eventhub.features.MvpPresenter;
import tech.blur.eventhub.features.auth.signup.presenter.SignUpActivity;
import tech.blur.eventhub.features.core.DefaultTextWatcher;
import tech.blur.eventhub.R;

import tech.blur.eventhub.features.auth.signup.presenter.SignUpActivity;
import tech.blur.eventhub.features.core.DefaultTextWatcher;


public class SignInActivity extends BaseActivity implements SignInView{

    EditText login;
    EditText pass;
    Button signIn;
    Button signUp;
    Boolean isAuth = false;

    //private static final boolean isAuthAc = false;

    SignInPresenter presenter;

    public static void start(Context context){
        final Intent intent = new Intent(context, SignInActivity.class);
        //intent.putExtra("isAuth", isAuthAc);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(tech.blur.eventhub.R.layout.activity_authorization);

        login = findViewById(tech.blur.eventhub.R.id.edit_signin_login);
        pass = findViewById(tech.blur.eventhub.R.id.edit_signin_password);
        signIn = findViewById(tech.blur.eventhub.R.id.signIn);
        signUp = findViewById(tech.blur.eventhub.R.id.signUp);


        login.addTextChangedListener(new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.onLoginChanged(s);
            }
        });

        pass.addTextChangedListener(new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.onPassChanged(s);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSignInClicked();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpActivity.start(SignInActivity.this);
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (isAuth) super.onBackPressed();
    }

    @Override
    public void authOk() {
        //EventsListActivity.start(SignInActivity.this, true);
        isAuth = true;
        onBackPressed();

    }

    //@SuppressLint("ShowToast")
    @Override
    public void authFailed() {
        Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected MvpPresenter<SignInView> getPresenter() {
        presenter = SignInPresenterFactory.createPresenter(this);
        return presenter;
    }

    @Override
    protected SignInView getMvpView() {
        return this;
    }

}
