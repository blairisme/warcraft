package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.utility.Identifier;

import java.util.Collection;
import java.util.Objects;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ActionPanel extends Item
{
    private Table table;

    public ActionPanel()
    {
        table = new Table();
        table.setBounds(0, 300, 176, 176); //TODO
    }

    public void setBackground(TextureRegion texture)
    {
        Drawable drawable = new TextureRegionDrawable(texture);
        table.setBackground(drawable);
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        table.draw(batch, alpha);
    }

    @Override
    public void act(float delta)
    {
        table.act(delta);
    }

    @Override
    public void setProperty(Identifier property, Object value)
    {
        super.setProperty(property, value);

        if (Objects.equals(property, new Identifier("Selection"))){
            setSelection((Collection<Item>)value);
        }
    }

    private void setSelection(Collection<Item> selection)
    {
    }
}
