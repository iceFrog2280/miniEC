package icefrog.com.latte.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import icefrog.com.latte.ui.R;

public class StarLayout extends LinearLayoutCompat implements View.OnClickListener {

    private static final CharSequence ICON_UN_SELECT = "{fa-star-o}";
    private static final CharSequence ICON_SELECTED = "{fa-star}";
    private static final int STAR_TOTAL_COUNT = 5;
    private int mCurrent = 0;
    private final ArrayList<IconTextView> STARS = new ArrayList<>();

    public StarLayout(Context context) {
        this(context, null);
    }

    public StarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStarIcon();
    }

    private void initStarIcon() {
//        LatteLogger.d("StarLayout.initStarIcon","第"+(++mC)+"次运行");
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            final IconTextView star = new IconTextView(getContext());
            star.setGravity(Gravity.CENTER);
            final LayoutParams lp =
                    new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            star.setLayoutParams(lp);
            star.setText(ICON_UN_SELECT);
            star.setTag(R.id.star_count, i);
            star.setTag(R.id.star_is_select, false);
            star.setOnClickListener(this);
            STARS.add(star);
            this.addView(star);
        }
    }

    public int getStarCount() {
        return mCurrent;
    }

    private void selectStar(int count){
        mCurrent = count + 1;
//        LatteLogger.d("StarLayout:"+mC,"mCurrent = "+mCurrent);
        for(int i = 0; i < mCurrent; i++){
            final IconTextView star = STARS.get(i);
            star.setText(ICON_SELECTED);
            star.setTextColor(Color.RED);
        }
        for(int i = mCurrent; i < STAR_TOTAL_COUNT; i++){
            final IconTextView star = STARS.get(i);
            star.setText(ICON_UN_SELECT);
            star.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        final IconTextView star = (IconTextView) v;
        //获取第几个星星
        final int count = (int) star.getTag(R.id.star_count);
        selectStar(count);
    }
}