package icefrog.com.latte.core.delegates.web.event;

import icefrog.com.latte.core.util.log.LatteLogger;

public class UndefineEvent extends Event {
    public UndefineEvent(String name) {
        super(name);
    }

    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
