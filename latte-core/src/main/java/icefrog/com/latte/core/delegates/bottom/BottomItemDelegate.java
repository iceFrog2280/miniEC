package icefrog.com.latte.core.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import icefrog.com.latte.core.delegates.LatteDelegate;

public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener{
    private long mExitTime = 0;
    private static final int EXIT_TiME = 1000;

    @Override
    public void onResume() {
        super.onResume();
        final View rootView = getView();
        if( rootView != null){
            rootView.setFocusableInTouchMode( true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if( (System.currentTimeMillis() - mExitTime) > EXIT_TiME){
                Toast.makeText(getContext(), "双击退出"+getString(org.litepal.R.string.app_name), Toast.LENGTH_SHORT);
                mExitTime = System.currentTimeMillis();
            }else{
                _mActivity.fileList();
                if( mExitTime != 0){
                    mExitTime = 0;
                }
            }
        }
        return false;
    }
}
