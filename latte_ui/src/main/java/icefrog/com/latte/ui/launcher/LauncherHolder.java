package icefrog.com.latte.ui.launcher;

import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

import icefrog.com.latte.core.R;

public class LauncherHolder extends Holder<Integer> {
    private final String TAG = getClass().getName();
    private AppCompatImageView mImageView = null;

    public LauncherHolder(View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.launcher_scroll_image);
    }

    @Override
    protected void initView(View itemView) {
        //谜一样的bug,这里不会触发,极有可能是编译优化干掉了
        // 2019年1月26日16:11:37 并不是上面所说而是 itemView有时会爆出 itemView取得值为null 可能
    }

    @Override
    public void updateUI(Integer data) {
        mImageView.setImageResource( data);
    }
}
