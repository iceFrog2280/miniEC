package icefrog.com.latte.core.app;

import android.content.Context;
import android.os.Handler;

//工具类
public final class Latte {
    public static Configurator init(Context context){
        getConfigurator().getLatteConfigs().put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Enum<ConfigKeys> key) {
        return getConfigurator().getConfiguration( key);
    }

    public static Context getApplicationContext(){
        return (Context) getConfiguration( ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler(){
        return (Handler) getConfiguration( ConfigKeys.HANDLER);
    }
}
