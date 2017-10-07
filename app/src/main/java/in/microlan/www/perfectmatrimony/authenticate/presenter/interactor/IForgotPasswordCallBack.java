package in.microlan.www.perfectmatrimony.authenticate.presenter.interactor;


import java.util.List;

public interface IForgotPasswordCallBack
{
    void onSuccessForgotPassword(String result);

    void onErrorForgotPassword(String result);

    void displayErrorList(List<String> errorList);
}
