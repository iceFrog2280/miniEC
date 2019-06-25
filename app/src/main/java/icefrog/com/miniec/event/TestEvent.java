package icefrog.com.miniec.event;

import android.webkit.WebView;
import android.widget.Toast;

import icefrog.com.latte.core.delegates.web.event.Event;

public class TestEvent extends Event {


    public TestEvent(String name) {
        super(name);
    }

    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_LONG).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
