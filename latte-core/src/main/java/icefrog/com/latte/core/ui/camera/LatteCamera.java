package icefrog.com.latte.core.ui.camera;

import android.net.Uri;

import icefrog.com.latte.core.delegates.PermissionCheckerDelegate;
import icefrog.com.latte.core.util.file.FileUtil;

public class LatteCamera {

    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
