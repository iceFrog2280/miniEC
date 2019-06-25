package icefrog.com.miniec.event;

import android.widget.Toast;

import icefrog.com.latte.core.app.AccountManager;
import icefrog.com.latte.core.delegates.web.event.Event;

public class ResetEvent extends Event {
    public ResetEvent(String name) {
        super(name);
    }

    @Override
    public String execute(String params) {
        AccountManager.setSignState(false);
        Toast.makeText(getContext(),"用户信息已初始化",Toast.LENGTH_LONG).show();
        return null;
    }
}
