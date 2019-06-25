package icefrog.com.miniec;

import android.app.Application;
import android.support.annotation.Nullable;
import android.view.View;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;
import com.mob.MobSDK;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.litepal.LitePal;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import icefrog.com.latte.core.app.Latte;
import icefrog.com.latte.core.delegates.web.event.Event;
import icefrog.com.latte.core.net.intereceptors.DebugInterceptor;
import icefrog.com.latte.core.net.intereceptors.DebugQueryInterceptor;
import icefrog.com.latte.core.util.callback.CallbackManager;
import icefrog.com.latte.core.util.callback.CallbackType;
import icefrog.com.latte.core.util.callback.IGlobalCallback;
import icefrog.com.latte.ec.icon.FontEcModule;
import icefrog.com.miniec.event.ResetEvent;
import icefrog.com.miniec.event.ShareEvent;
import icefrog.com.miniec.event.TestEvent;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Source;

public class ExampleApp extends Application {

    private static String WeChatID = "a8eb7de5154e25aa1689p6c1d8f87dc5";

    @Override
    public void onCreate() {
        super.onCreate();
//配置Interceptor
        List<Interceptor> Interceptors = new ArrayList<>();
        Interceptors.add(new DebugInterceptor("/test", R.raw.test));
        Interceptors.add(new DebugInterceptor("/index", R.raw.index));
        Interceptors.add(new DebugInterceptor("/search", R.raw.search));
        Interceptors.add(new DebugQueryInterceptor("/refresh", R.raw.refresh, R.raw.refresh2,"index"));
        Interceptors.add(new DebugInterceptor("/sort_delegate", R.raw.sort_delegate));
        Interceptors.add(new DebugInterceptor( "/shop_cart_data",R.raw.shop_cart_data));
        Interceptors.add(new DebugInterceptor("/order_list", R.raw.order_list));
        Interceptors.add(new DebugInterceptor("/address", R.raw.address));
        Interceptors.add(new DebugInterceptor("/about", R.raw.about));
        Interceptors.add(new DebugQueryInterceptor("/sort_content_list", R.raw.sort_section_1, R.raw.sort_section_2,"contentId"));
//配置Event
        List<Event> webEvents = new ArrayList<>();
        webEvents.add(new TestEvent("test"));
        webEvents.add(new ShareEvent("share"));
        webEvents.add(new ResetEvent("reset"));

        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withApiHost("http://127.0.0.1")
                .withInterceptor(Interceptors)
                .withWeChatAppId(WeChatID)
                .withWeChatAppSecret("a0560f75335b06e3ebea70f29fp219ba")
                .withJavascriptInterface("latte")
                .withWebEvent(webEvents)
                .configure();
        Iconify.with(new IoniconsModule())
                .with(new FontAwesomeModule())
                .with(new FontEcModule());
        MobSDK.init(this);
        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Latte.getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            JPushInterface.stopPush(Latte.getApplicationContext());
                        }
                    }
                });

        Logger.addLogAdapter(new AndroidLogAdapter());
        LitePal.initialize(this);

/*        OkHttpClient client = new OkHttpClient.Builder()
                .cache(new Cache( new File( "cache"), 12*1024*1024))
                .build();
        Request request = new Request.Builder()
                .url("123")
                .get()
                .build();
        Call call = client.newCall(request);
        Response request1;
        try {
             request1 = call.execute();
             call.enqueue(new Callback(){
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            URL url = new URL("http://192.168.31.200:8080/HttpServer/MyServlet?name=孙群&age=27");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        View view = new View(this);
        view.animate().alpha(0.5f);*/
//        Request request;
//        RequestBody body;7

        // okkio
//        Source source;
/*        GifBitmapWrapperResourceDecoder decoder;
        Lifecycle lifecycle;
        ResourceDecoder decoder1;
        GenericRequest g;
        //GlideDrawbleImageViewTarget target;
        Glide.with(this)
                .load("http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg")
                .override(100,100)
                .into( new ImageView(this));*/

//        Context context; // ContextWrapper -> CSF7 -> ContextImpl
//        Service service; // Service -> attach() -> ActivityThread
//        ActivityThread -> CSF7 ->ActivityManagerService

        //Rxjava
//        Observable observable = Observable.create(new Observable.OnSub<>());
//        Observer<String> observer = Observable


    }
}

