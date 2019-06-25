package icefrog.com.latte.ui.recycle;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

public class MultipleViewHolder extends BaseViewHolder {
    private MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
