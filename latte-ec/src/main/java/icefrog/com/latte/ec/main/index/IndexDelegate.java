package icefrog.com.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;


import butterknife.BindView;
import butterknife.OnClick;
import icefrog.com.latte.core.delegates.bottom.BottomItemDelegate;
import icefrog.com.latte.core.util.callback.CallbackManager;
import icefrog.com.latte.core.util.callback.CallbackType;
import icefrog.com.latte.core.util.callback.IGlobalCallback;
import icefrog.com.latte.core.util.log.LatteLogger;
import icefrog.com.latte.ec.main.index.search.SearchDelegate;
import icefrog.com.latte.ui.refresh.RefreshHandler;
import icefrog.com.latte.ec.R;
import icefrog.com.latte.ec.R2;
import icefrog.com.latte.ec.main.EcBottomDelegate;

public class IndexDelegate extends BottomItemDelegate implements View.OnFocusChangeListener{
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mSwipeRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconTextView = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;

    @OnClick(R2.id.icon_index_scan)
    void onClickScanQrCode() {
        startScanWithCheck(this.getParentDelegate());
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create( mSwipeRefreshLayout, mRecyclerView, new IndexDataConverter());
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_SCAN, new IGlobalCallback<String>() {
                    @Override
                    public void executeCallback(@Nullable String args) {
                        Toast.makeText(getContext(), "得到的二维码是" + args, Toast.LENGTH_LONG).show();
                    }
                });
        mSearchView.setOnFocusChangeListener(this);
    }

    private void initRefreshLayout(){
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mSwipeRefreshLayout.setProgressViewOffset( true, 120,300);
    }

    private void initRecyclerView(){
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration( new HorizontalDividerItemDecoration.Builder(getContext()).build());
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create( ecBottomDelegate));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("http://127.0.0.1/index.php");
//        mRefreshHandler.firstPage("file:///android_res/index.json"); //?
//        mRefreshHandler.firstPage("file:///android_res//raw//index.json"); //?
//        mRefreshHandler.firstPage("file:///android_res/raw/index.json"); //无效
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
//            LatteLogger.d("onFocusChange.view === mSearchView -> "+mSearchView.equals(v));
            getParentDelegate().getSupportDelegate().start(new SearchDelegate());
        }
    }
}
