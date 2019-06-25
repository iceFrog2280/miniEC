package icefrog.com.miniec.generators;

import icefrog.com.latte.annotations.EntryGenerator;
import icefrog.com.latte.core.wechat.templates.WXEntryTemplate;

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "icefrog.com.miniec",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}