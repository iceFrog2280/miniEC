package icefrog.com.latte.ui.launcher;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import icefrog.com.latte.core.R;

public class LauncherHolderCreator implements CBViewHolderCreator {
    @Override
    public Holder createHolder(View itemView) {
        return new LauncherHolder( itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.launcher_scroll_image;
    }

}
