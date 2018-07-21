package com.syberianguys.srggrch.eventsgaring.features.auth.signup.presenter;

import com.syberianguys.srggrch.eventsgaring.features.MvpPresenter;
import com.syberianguys.srggrch.eventsgaring.features.core.events.model.User;
import com.syberianguys.srggrch.eventsgaring.features.event.add.presentation.UserGender;
import com.syberianguys.srggrch.eventsgaring.features.auth.signup.domain.SignUpInteractor;
import com.syberianguys.srggrch.eventsgaring.network.Carry;

public class SignUpPresenter extends MvpPresenter<SignUpView>{
    private SignUpInteractor interactor;
    private String login;
    private String nameUser;
    private String birthday;
    private String password;
    private String repeatPassword;
    private UserGender userGender;


    public SignUpPresenter(SignUpInteractor signUpInteractor) {
        this.interactor = signUpInteractor;
    }
    public void onRegButClicked(){
        if(view.checker(nameUser,login,password,repeatPassword)) {
            User user = new User(login, password, nameUser, birthday, userGender.getId());
            interactor.addUser(user, new Carry<User>() {
                @Override
                public void onSuccess(User result) {

                }

                @Override
                public void onFailure(Throwable throwable) {
                    view.showError(throwable.getMessage());
                }
            });
        }

    }

    public void onLoginChanged(CharSequence login){
        this.login = login.toString();
    }
    public void onNameUserChanged(CharSequence nameUser){
        this.nameUser = nameUser.toString();
    }
    public void onBirthdayChanged(CharSequence birthday){
        this.birthday = birthday.toString();
    }
    public void onPasswordChanged(CharSequence password){
        this.password = password.toString();
    }
    public void onRepeatPasswordChanged(CharSequence repeatPassword){
        this.repeatPassword = repeatPassword.toString();
        view.confirmPasswordChecker(password,repeatPassword.toString());
    }

    public void setUserGender(UserGender userGender) {
        this.userGender = userGender;
    }
}
