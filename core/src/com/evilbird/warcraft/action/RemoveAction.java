package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.warcraft.item.ItemUtils;
import com.evilbird.warcraft.utility.Identifier;

public class RemoveAction extends Action
{
    private Stage stage;
    private Actor actor;

    public RemoveAction(Stage stage, Actor actor)
    {
        this.stage = stage;
        this.actor = actor;
    }

    public RemoveAction(Stage stage, Identifier unitIdentifier)
    {
        this.stage = stage;
        this.actor = ItemUtils.findById(stage, unitIdentifier);
    }

    @Override
    public boolean act(float delta)
    {
        actor.clearActions();
        actor.remove();
        return true;
    }
}
