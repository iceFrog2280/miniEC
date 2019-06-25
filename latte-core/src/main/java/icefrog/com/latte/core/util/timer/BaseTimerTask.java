package icefrog.com.latte.core.util.timer;

import java.util.TimerTask;

public class BaseTimerTask extends TimerTask{
    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if( mITimerListener != null){
            mITimerListener.onTimer();
        }
    }
}
