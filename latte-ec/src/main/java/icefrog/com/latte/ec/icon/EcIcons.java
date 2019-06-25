package icefrog.com.latte.ec.icon;

import com.joanzapata.iconify.Icon;

public enum  EcIcons implements Icon {
    icon_scan('\ue606'),
    icon_ali_pay('\ue606');

    private char character;

    EcIcons(char c) {
        character = c;
    }

    @Override
    public String key() {
        return name().replace('_','-');
    }

    @Override
    public char character() {
        return character;
    }
}
