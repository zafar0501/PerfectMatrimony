package in.microlan.www.perfectmatrimony.common.presenter;


import java.util.List;

public interface IResultView<T> {

    //onCreate
    void showResult(T listDO);

    //onMessage display
    void onDisplayMessage(String message);

    void showResultList(List<T> resultDOList);

    void displayErrorList(List<String> errorList);

    interface ILoadMoreResult<T> extends IResultView<T> {

        void showMoreResultList(List<T> resultDOList);
    }
   // void showResultUserInfo(T listDO);
}
