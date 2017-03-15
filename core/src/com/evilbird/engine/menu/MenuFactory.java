package com.evilbird.engine.menu;

public interface MenuFactory
{
    public void load();

    public Menu rootMenu();

    public Menu newMenu(MenuIdentifier menuIdentifier);
}
