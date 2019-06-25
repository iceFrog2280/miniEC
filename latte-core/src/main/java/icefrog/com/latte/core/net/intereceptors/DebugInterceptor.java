package icefrog.com.latte.core.net.intereceptors;

import android.support.annotation.RawRes;
import android.util.Log;

import java.io.IOException;

import icefrog.com.latte.core.util.file.FileUtil;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DebugInterceptor extends BaseInterceptor {
    private static final String TAG = "DebugInterceptor";

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, @RawRes int debugRawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = debugRawId;
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
      final HttpUrl url = chain.request().url();
        if( url.host().equals("127.0.0.1") && url.toString().contains( DEBUG_URL)){
            Log.d(TAG, "intercept: 回传URL"+DEBUG_URL);
            return debugResponse( chain, DEBUG_RAW_ID);
        }
        return chain.proceed( chain.request());
    }
}
