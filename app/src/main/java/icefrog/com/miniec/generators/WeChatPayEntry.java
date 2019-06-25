package icefrog.com.miniec.generators;

import icefrog.com.latte.annotations.PayEntryGenerator;
import icefrog.com.latte.core.wechat.templates.WXPayEntryTemplate;

@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "icefrog.com.miniec",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
