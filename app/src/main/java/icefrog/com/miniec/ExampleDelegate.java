package icefrog.com.miniec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import icefrog.com.latte.core.delegates.LatteDelegate;
import icefrog.com.latte.core.net.RestClient;
import icefrog.com.latte.core.net.callback.IError;
import icefrog.com.latte.core.net.callback.IFailure;
import icefrog.com.latte.core.net.callback.ISuccess;

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {
        //testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
                //.url("https://blog.csdn.net/carson_ho/article/details/73732076")
                .url("http://127.0.0.1/test")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText( getContext(),response ,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
