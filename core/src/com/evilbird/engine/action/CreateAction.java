package com.evilbird.engine.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.utility.Identifier;

public class CreateAction extends Action
{
    private Stage stage;
    private Identifier id;
    private Identifier type;
    private ItemFactory factory;
    private Vector2 position;

    public CreateAction(Stage stage, Identifier type, ItemFactory factory, Identifier id, Vector2 position)
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
        Item unit = factory.newItem(type);

        unit.setSize(72, 72);
        unit.setZIndex(10);
        unit.setPosition(position.x, position.y);
        unit.setProperty(new Identifier("Id"), id);

        stage.addActor(unit);
        return true;
    }
}
