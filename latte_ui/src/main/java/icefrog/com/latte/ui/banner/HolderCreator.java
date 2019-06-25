package icefrog.com.latte.ui.banner;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import icefrog.com.latte.core.R;

public class HolderCreator implements CBViewHolderCreator {

    @Override
    public Holder createHolder(View itemView) {
        return new ImageHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.banner_scroll_image;
    }
}
