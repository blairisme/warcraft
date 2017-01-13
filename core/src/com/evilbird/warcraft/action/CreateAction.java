package com.evilbird.warcraft.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.warcraft.unit.Unit;
import com.evilbird.warcraft.unit.UnitFactory;
import com.evilbird.warcraft.utility.Identifier;

public class CreateAction extends Action
{
    private Stage stage;
    private Identifier id;
    private Identifier type;
    private UnitFactory factory;
    private Vector2 position;

    public CreateAction(Stage stage, Identifier type, UnitFactory factory, Identifier id, Vector2 position)
    {
        this.stage = stage;
        this.id = id;
        this.type = type;
        this.factory = factory;
        this.position = position;
    }

    @Override
    public boolean act(float delta)
    {
        Unit unit = factory.newUnit(type, id);

        unit.setSize(72, 72);
        unit.setZIndex(10);
        unit.setPosition(position.x, position.y);

        stage.addActor(unit);
        return true;
    }
}
