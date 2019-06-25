package icefrog.com.latte.ec.main;

import android.graphics.Color;

import java.util.LinkedHashMap;

import icefrog.com.latte.core.delegates.bottom.BaseBottomDelegate;
import icefrog.com.latte.core.delegates.bottom.BottomItemDelegate;
import icefrog.com.latte.core.delegates.bottom.BottomTabBean;
import icefrog.com.latte.core.delegates.bottom.ItemBuilder;
import icefrog.com.latte.ec.main.cart.ShopCartDelegate;
import icefrog.com.latte.ec.main.discover.DiscoverDelegate;
import icefrog.com.latte.ec.main.index.IndexDelegate;
import icefrog.com.latte.ec.main.personal.PersonalDelegate;
import icefrog.com.latte.ec.main.sort.SortDelegate;

public class EcBottomDelegate extends BaseBottomDelegate{
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
