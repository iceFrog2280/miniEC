package icefrog.com.latte.core.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import icefrog.com.latte.core.delegates.LatteDelegate;
import icefrog.com.latte.core.delegates.web.WebDelegate;

public abstract class Event implements IEvent {
    private String mEventName = "";
    private Context mContent = null;
    private String mAction = null;
    private WebDelegate mDelegate = null;
    private String mUrl = null;
    private WebView mWebView = null;

    public Event(String name){
        mEventName = name;
    }

    public String getEventName(){
        return mEventName;
    }

    public Context getContext() {
        return mContent;
    }

    public WebView getWebView(){
        return mDelegate.getWebView();
    }

    public void setContext(Context mContent) {
        this.mContent = mContent;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public LatteDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
