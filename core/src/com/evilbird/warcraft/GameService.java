package com.evilbird.warcraft;

import com.evilbird.warcraft.action.ActionFactory;
import com.evilbird.warcraft.hud.HudFactory;
import com.evilbird.warcraft.interaction.BehaviourFactory;
import com.evilbird.warcraft.menu.MenuFactory;
import com.evilbird.warcraft.unit.UnitFactory;
import com.evilbird.warcraft.unit.WorldFactory;

public class GameService
{
    private ActionFactory actionFactory;
    private MenuFactory menuFactory;
    private UnitFactory unitFactory;
    private WorldFactory worldFactory;
    private HudFactory hudFactory;
    private BehaviourFactory behaviourFactory;

    public GameService(
            ActionFactory actionFactory,
            MenuFactory menuFactory,
            UnitFactory unitFactory,
            WorldFactory worldFactory,
            HudFactory hudFactory,
            BehaviourFactory behaviourFactory)
    {
        this.actionFactory = actionFactory;
        this.menuFactory = menuFactory;
        this.unitFactory = unitFactory;
        this.worldFactory = worldFactory;
        this.hudFactory = hudFactory;
        this.behaviourFactory = behaviourFactory;
    }

    public ActionFactory getActionFactory()
    {
        return actionFactory;
    }

    public MenuFactory getMenuFactory()
    {
        return menuFactory;
    }

    public UnitFactory getUnitFactory()
    {
        return unitFactory;
    }

    public WorldFactory getWorldFactory()
    {
        return worldFactory;
    }

    public HudFactory getHudFactory()
    {
        return hudFactory;
    }

    public BehaviourFactory getBehaviourFactory()
    {
        return behaviourFactory;
    }
}
