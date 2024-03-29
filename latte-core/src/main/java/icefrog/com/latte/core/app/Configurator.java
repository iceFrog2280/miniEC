package icefrog.com.latte.core.app;

import com.blankj.utilcode.util.Utils;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;

import org.litepal.LitePal;

import icefrog.com.latte.core.delegates.web.event.Event;
import icefrog.com.latte.core.delegates.web.event.EventManager;
import okhttp3.Interceptor;

public class Configurator {
    private static final WeakHashMap<Object, Object> LATTE_CONFIGS = new WeakHashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private Configurator(){
        LATTE_CONFIGS.put( ConfigKeys.CONFIG_READY, false);
        LATTE_CONFIGS.put( ConfigKeys.HANDLER, HANDLER);
    }

    public static Configurator getInstance(){
        return Holder.sInstance;
    }

    private static class Holder{
        private static final Configurator sInstance = new Configurator();
    }

    public final void configure() {
        initIcons();
        Logger.addLogAdapter(new AndroidLogAdapter());
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
        Utils.init(Latte.getApplicationContext());
        LitePal.initialize(Latte.getApplicationContext());
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    final WeakHashMap<Object, Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put( ConfigKeys.API_HOST, host);
        return this;
    }


    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add( descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add( interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptor(List<Interceptor> interceptors){
        INTERCEPTORS.addAll( interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withWeChatAppId(String appId) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    public Configurator withJavascriptInterface(@NonNull String name) {
        LATTE_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    public Configurator withWebEvent(@NonNull List<Event> list) {
        for (Event event : list) {
            withWebEvent(event.getEventName(), event);
        }
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if( !isReady){
            throw new RuntimeException("Configuration is not read, call configure");
        }
    }

    final <T> T getConfiguration(Enum<ConfigKeys> key){
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }
}
