package icefrog.com.latte.ui.banner;

import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import icefrog.com.latte.core.R;
import icefrog.com.latte.core.app.Latte;

public class ImageHolder extends Holder<String> {
    private static final String TAG = ImageHolder.class.getName();
    private AppCompatImageView mImageView = null;

    public ImageHolder(View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.banner_scroll_image);
    }

    @Override
    protected void initView(View itemView) {
    }

    @Override
    public void updateUI(String data) {
//        Log.d(TAG, "mImageView = " + (mImageView == null));
        Glide.with(Latte.getApplicationContext())
                .load(data)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .centerCrop()
                .into(mImageView);
    }
}
