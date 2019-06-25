package icefrog.com.latte.core.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import icefrog.com.latte.core.activities.ProxyActivity;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

public abstract class BaseDelegate extends SwipeBackFragment{
    protected String TAG = this.getClass().getName();
    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;

    public abstract Object setLayout();
    public abstract void onBindView(@Nullable Bundle saveInstanceState, View rootView);

    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if( setLayout() instanceof  Integer){
            Log.d(TAG, "onCreateView: "+getClass().getName());
            rootView = inflater.inflate( (Integer) setLayout(), container, false);
        }else if( setLayout() instanceof  View){
            rootView = (View) setLayout();
        }else {
            throw new ClassCastException("setLayout() type must be int or View");
        }
        if( rootView != null){
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView( savedInstanceState, rootView);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if( mUnbinder != null) mUnbinder.unbind();
    }
}
