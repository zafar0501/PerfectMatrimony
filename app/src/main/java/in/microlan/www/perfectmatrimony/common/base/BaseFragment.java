package in.microlan.www.perfectmatrimony.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.microlan.www.perfectmatrimony.common.presenter.IBaseFragment;


public abstract class BaseFragment extends Fragment implements IBaseFragment {

    private View layoutView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(), container, false);

        setLayoutView(view);
        return view;
    }

    protected abstract int getLayoutResourceId();

    public View getLayoutView() {
        return layoutView;
    }

    public void setLayoutView(View layoutView) {
        this.layoutView = layoutView;
    }

}
