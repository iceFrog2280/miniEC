package icefrog.com.latte.core.net.intereceptors;

import android.support.annotation.RawRes;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

import icefrog.com.latte.core.util.file.FileUtil;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DebugQueryInterceptor extends BaseInterceptor {
    private static final String TAG = "DebugQueryInterceptor";

    private final String DEBUG_URL;
    private final int RES_1;
    private final int RES_2;
    private final String QUERY;

    public DebugQueryInterceptor(String DEBUG_URL, int RES_1, int RES_2, String Query) {
        this.DEBUG_URL = DEBUG_URL;
        this.RES_1 = RES_1;
        this.RES_2 = RES_2;
        QUERY = Query;
    }

    private Response getResponse(Chain chain, String json){
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rawId){
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        URL url = chain.request().url().url();
        Log.d(TAG, "intercept: "+chain.request().url());
        if( url.getHost().contains("127.0.0.1") && url.toString().contains(DEBUG_URL)){
            if( url.getQuery().contains(QUERY+"=1")){
                return debugResponse(chain, RES_1);
            } else if( url.getQuery().contains(QUERY+"=2")){
                return debugResponse(chain, RES_2);
            }else {
                Log.d(TAG, "intercept: getQuery() 失败");
            }
        }
        return chain.proceed( chain.request());
    }
}
