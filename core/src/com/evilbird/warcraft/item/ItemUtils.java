package com.evilbird.warcraft.item;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.warcraft.utility.Identifier;

import java.util.Collection;
import java.util.Objects;

public class ItemUtils
{
    private static final Identifier ID_PROPERTY = new Identifier("Id");
    private static final Identifier TYPE_PROPERTY = new Identifier("Type");

    public static Item findById(Stage stage, Identifier id)
    {
        return find(stage, ID_PROPERTY, id);
    }

    public static Item findById(Collection<Item> items, Identifier id)
    {
        return find(items, ID_PROPERTY, id);
    }

    public static Item findByType(Stage stage, Identifier type)
    {
        return find(stage, TYPE_PROPERTY, type);
    }

    public static Item findByType(Collection<Item> items, Identifier type)
    {
        return find(items, TYPE_PROPERTY, type);
    }

    public static Item find(Stage stage, Identifier property, Object value)
    {
        for (Actor actor: stage.getActors())
        {
            if (actor instanceof Item)
            {
                Item item = (Item)actor;

                if (Objects.equals(item.getProperty(property), value))
                {
                    return item;
                }
            }
        }
        throw new IllegalStateException();
    }

    public static Item find(Collection<Item> items, Identifier property, Object value)
    {
        for (Item item: items)
        {
            if (Objects.equals(item.getProperty(property), value))
            {
                return item;
            }
        }
        throw new IllegalStateException();
    }
}
