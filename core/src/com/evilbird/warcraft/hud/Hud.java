package com.evilbird.warcraft.hud;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.warcraft.item.Item;
import com.evilbird.warcraft.utility.Identifier;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Hud extends Stage
{
    private Map<Identifier, Item> items;

    public Hud()
    {
        this.items = new HashMap<Identifier, Item>();
    }

    @Override
    public void addActor(Actor actor)
    {
        addItem((Item)actor);
    }

    public void addItem(Item item)
    {
        Identifier id = (Identifier)item.getProperty(new Identifier("Id"));
        items.put(id, item);
        super.addActor(item);
    }

    public void removeItem(Item item)
    {
        Identifier id = (Identifier)item.getProperty(new Identifier("Id"));
        items.remove(id);
        item.remove();
    }

    public Item getItem(Identifier id)
    {
        return items.get(id);
    }

    public Collection<Item> getItems()
    {
        return Collections.unmodifiableCollection(items.values());
    }
}
