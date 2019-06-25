package icefrog.com.miniec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;
import icefrog.com.latte.core.activities.ProxyActivity;
import icefrog.com.latte.core.app.Latte;
import icefrog.com.latte.core.delegates.LatteDelegate;
import icefrog.com.latte.ui.launcher.ILauncherListener;
import icefrog.com.latte.ui.launcher.OnLauncherFinishTag;
import icefrog.com.latte.ec.launcher.LauncherDelegate;
import icefrog.com.latte.ec.main.EcBottomDelegate;
import icefrog.com.latte.ec.sign.ISignListener;
import icefrog.com.latte.ec.sign.SignInDelegate;
import qiu.niorgai.StatusBarCompat;

public class  ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener
{
    private static final String TAG = "ExampleActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if( actionBar != null) actionBar.hide();
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this,true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        startWithPop(new EcBottomDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        start(new SignInDelegate());
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED:
                Toast.makeText(this,"用户登陆成功",Toast.LENGTH_SHORT).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this,"用户未登陆成功",Toast.LENGTH_SHORT).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
