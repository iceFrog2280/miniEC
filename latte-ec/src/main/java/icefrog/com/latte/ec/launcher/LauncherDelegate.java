package icefrog.com.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import icefrog.com.latte.core.app.AccountManager;
import icefrog.com.latte.core.app.IUserChecker;
import icefrog.com.latte.core.delegates.LatteDelegate;
import icefrog.com.latte.ui.launcher.ILauncherListener;
import icefrog.com.latte.ui.launcher.OnLauncherFinishTag;
import icefrog.com.latte.ui.launcher.ScrollLauncherTag;
import icefrog.com.latte.core.util.storage.LattePreference;
import icefrog.com.latte.core.util.timer.BaseTimerTask;
import icefrog.com.latte.core.util.timer.ITimerListener;
import icefrog.com.latte.ec.R;
import icefrog.com.latte.ec.R2;

public class LauncherDelegate extends LatteDelegate implements ITimerListener{
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 2;
    private ILauncherListener mLauncherListener = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        if( mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }

    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask( this);
        mTimer.schedule( task, 0, 1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if( activity instanceof ILauncherListener){
            mLauncherListener = (ILauncherListener)activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_laucher;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {
        initTimer();
    }

    private void checkIsShowScroll(){
        if( !LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start( new LauncherScrollDelegate(), SINGLETASK);
        }else {
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if(mLauncherListener != null) mLauncherListener.onLauncherFinish( OnLauncherFinishTag.SIGNED);
                }

                @Override
                public void onNotSignIn() {
                    if(mLauncherListener != null) mLauncherListener.onLauncherFinish( OnLauncherFinishTag.NOT_SIGNED);
                }
            });
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if( mTvTimer != null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if(mCount < 0){
                        if( mTimer != null){
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
