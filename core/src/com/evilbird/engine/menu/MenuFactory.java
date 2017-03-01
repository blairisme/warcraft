package com.evilbird.engine.menu;

import com.evilbird.engine.common.lang.Identifier;

public interface MenuFactory
{
    public void load();

    public Menu newMenu(Identifier id);
}
