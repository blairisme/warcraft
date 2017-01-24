package com.evilbird.warcraft;

import com.evilbird.warcraft.action.ActionFactory;
import com.evilbird.warcraft.level.LevelFactory;
import com.evilbird.warcraft.menu.MenuFactory;
import com.evilbird.warcraft.unit.UnitFactory;

public class GameService
{
    private ActionFactory actionFactory;
    private LevelFactory levelFactory;
    private MenuFactory menuFactory;
    private UnitFactory unitFactory;

    public GameService(
            ActionFactory actionFactory,
            LevelFactory levelFactory,
            MenuFactory menuFactory,
            UnitFactory unitFactory)
    {
        this.actionFactory = actionFactory;
        this.levelFactory = levelFactory;
        this.menuFactory = menuFactory;
        this.unitFactory = unitFactory;
    }

    public ActionFactory getActionFactory()
    {
        return actionFactory;
    }

    public MenuFactory getMenuFactory()
    {
        return menuFactory;
    }

    public LevelFactory getLevelFactory()
    {
        return levelFactory;
    }

    public UnitFactory getUnitFactory()
    {
        return unitFactory;
    }
}
