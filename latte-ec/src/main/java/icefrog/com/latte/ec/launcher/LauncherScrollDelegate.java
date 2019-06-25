package icefrog.com.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

import icefrog.com.latte.core.app.AccountManager;
import icefrog.com.latte.core.app.IUserChecker;
import icefrog.com.latte.core.delegates.LatteDelegate;
import icefrog.com.latte.ui.launcher.ILauncherListener;
import icefrog.com.latte.ui.launcher.LauncherHolderCreator;
import icefrog.com.latte.ui.launcher.OnLauncherFinishTag;
import icefrog.com.latte.ui.launcher.ScrollLauncherTag;
import icefrog.com.latte.core.util.storage.LattePreference;
import icefrog.com.latte.ec.R;

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener{

    private ConvenientBanner<Integer> mConvenientBanner =  null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mLauncherListener = null;

    private void initBanner(View rootView){
        mConvenientBanner = rootView.findViewById( R.id.launcher_scroll_banner);
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator( new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign( ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
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
        return R.layout.delegate_laucher_scroll_banner;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {
        initBanner(rootView);
    }

    @Override
    public void onItemClick(int position) {
        if(  position >= INTEGERS.size()-1){
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检测用户是否登录
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
}