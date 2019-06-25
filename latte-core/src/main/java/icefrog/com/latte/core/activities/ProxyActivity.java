package icefrog.com.latte.core.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;

import icefrog.com.latte.core.R;
import icefrog.com.latte.core.delegates.LatteDelegate;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class ProxyActivity extends SupportActivity {
    private String TAG = this.getClass().getName();
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer( savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }

    //@RestrictTo({RestrictTo.Scope.LIBRARY})
    private void initContainer(@Nullable Bundle savedInstancesState){
        Log.d(TAG, "initContainer: ");
        @SuppressLint("RestrictedApi") final ContentFrameLayout content = new ContentFrameLayout(this);
        content.setId(R.id.delegate_container);
        setContentView( content);
        if( savedInstancesState == null){
            loadRootFragment( R.id.delegate_container, setRootDelegate());
        }
    }
}
