package icefrog.com.latte.core.net;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.Map;

import icefrog.com.latte.core.net.callback.IError;
import icefrog.com.latte.core.net.callback.IFailure;
import icefrog.com.latte.core.net.callback.IRequest;
import icefrog.com.latte.core.net.callback.ISuccess;
import icefrog.com.latte.core.net.callback.RequestCallbacks;
import icefrog.com.latte.core.net.download.DownloadHandler;
import icefrog.com.latte.core.ui.loader.LatteLoader;
import icefrog.com.latte.core.ui.loader.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RestClient {
    private static final String TAG = "RestClient";
    private final String URL;
    private final Map<String, Object> PARAMS;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String PATH_AND_NAME;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody mBody;
    private final File mUploadFile;
    private final Context mContext;
    private final LoaderStyle mLoaderStyle;


    public RestClient(String URL, Map<String, Object> params,
                      String downloadDir, String extension, String filePath,
                      IRequest request, ISuccess success, IFailure failure, IError error,
                      RequestBody body,
                      File uploadFile,
                      Context context, LoaderStyle loaderStyle
    ) {
        this.URL = URL;

        DOWNLOAD_DIR = downloadDir;
        EXTENSION = extension;
        PATH_AND_NAME = filePath;

        PARAMS = params;
        REQUEST = request;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;

        mBody = body;
        mUploadFile = uploadFile;

        mContext = context;
        mLoaderStyle = loaderStyle;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMethod method){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if( REQUEST != null) REQUEST.onRequestStart();
        if( mLoaderStyle != null) LatteLoader.showLoading(mContext, mLoaderStyle);
        switch (method){
            case GET:
                //PARAMS为null会引发 Call.onFailure
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, mBody);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.postRaw(URL, mBody);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create( MediaType.parse( MultipartBody.FORM.toString()), mUploadFile);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", mUploadFile.getName(), requestBody);
                call = service.upload(URL, body);
                break;
            default:
                break;
        }
        Log.d(TAG, "request: HttpMethod = "+method.name()+" URL = "+URL+" call = "+call);
        if( call != null){
            call.enqueue( getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                mLoaderStyle
        );
    }

    public void get(){
        request( HttpMethod.GET);
    }
    public void post(){
        if( mBody == null){
            request( HttpMethod.POST);
        }else{
            if( !PARAMS.isEmpty()) throw new RuntimeException("params must be empty");
            request( HttpMethod.POST_RAW);
        }
    }
    public void put(){
        if( mBody == null){
            request( HttpMethod.PUT);
        }else{
            if( !PARAMS.isEmpty()) throw new RuntimeException("params must be empty");
            request( HttpMethod.PUT_RAW);
        }
    }
    public void delet(){
        request( HttpMethod.DELETE);
    }
    public void upload(){ request( HttpMethod.UPLOAD);}
    public void download(){
        new DownloadHandler(
                URL,PARAMS,
                DOWNLOAD_DIR,EXTENSION,PATH_AND_NAME,
                REQUEST,SUCCESS,FAILURE,ERROR
        ).handleDownload();
    }
}
