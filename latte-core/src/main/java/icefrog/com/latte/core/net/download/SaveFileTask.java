package icefrog.com.latte.core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.File;
import java.io.InputStream;

import icefrog.com.latte.core.app.Latte;
import icefrog.com.latte.core.net.callback.IRequest;
import icefrog.com.latte.core.net.callback.ISuccess;
import icefrog.com.latte.core.util.file.FileUtil;
import okhttp3.ResponseBody;

public class SaveFileTask extends AsyncTask<Object, Void, File>{
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... objects) {
        String downloadDir = (String) objects[0];
        String extension = (String) objects[1];
        ResponseBody body = (ResponseBody) objects[2];
        String name = (String) objects[3];
        final InputStream in = body.byteStream();
        if( downloadDir == null || downloadDir.equals("")) downloadDir = "downloads";
        if( extension == null || extension.equals("")) extension = "bin";
        if(name == null ){
            return FileUtil.writeToDisk(in, downloadDir,extension.toUpperCase(),extension);
        }else {
            return FileUtil.writeToDisk(in, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if(SUCCESS != null){
            SUCCESS.onSuccess(file.getPath());
        }
        if(REQUEST != null){
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file){
        if( FileUtil.getExtension( file.getPath()).equals("apk")){
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }
    }
}
