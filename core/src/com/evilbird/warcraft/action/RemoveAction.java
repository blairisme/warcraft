package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.warcraft.item.ItemUtils;
import com.evilbird.warcraft.utility.Identifier;

public class RemoveAction extends Action
{
    private Stage stage;
    private Identifier unitIdentifier;

    public RemoveAction(Stage stage, Identifier unitIdentifier)
    {
        this.stage = stage;
        this.unitIdentifier = unitIdentifier;
    }

    @Override
    public boolean act(float delta)
    {
        stage.addActor(ItemUtils.findById(stage, unitIdentifier));
        return true;
    }
}
