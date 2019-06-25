package icefrog.com.miniec.generators;

import icefrog.com.latte.annotations.AppRegisterGenerator;
import icefrog.com.latte.core.wechat.templates.AppRegisterTemplate;

@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "icefrog.com.miniec",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
