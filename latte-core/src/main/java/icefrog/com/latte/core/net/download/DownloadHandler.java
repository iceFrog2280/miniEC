package icefrog.com.latte.core.net.download;

import android.os.AsyncTask;

import java.util.Map;

import icefrog.com.latte.core.net.RestCreator;
import icefrog.com.latte.core.net.callback.IError;
import icefrog.com.latte.core.net.callback.IFailure;
import icefrog.com.latte.core.net.callback.IRequest;
import icefrog.com.latte.core.net.callback.ISuccess;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadHandler {

    private final String URL;
    private final Map<String, Object> PARAMS;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public DownloadHandler(String url, Map<String, Object> params,
                           String downloadDir, String extension, String name,
                           IRequest request, ISuccess success, IFailure failure, IError error) {
        this.URL = url;
        this.PARAMS = params;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    public final void handleDownload(){
        if(REQUEST != null){
            REQUEST.onRequestStart();
        }
        RestCreator.getRestService().download( URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if( response.isSuccessful()){
                            final ResponseBody responseBody = response.body();
                            final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                            task.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, response, NAME);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if( FAILURE != null){
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}
