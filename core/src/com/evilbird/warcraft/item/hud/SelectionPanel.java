package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.utility.Identifier;

import java.util.Collection;
import java.util.Objects;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SelectionPanel extends Item
{
    private Table table;
    private ActionFactory actionFactory;

    public SelectionPanel()
    {
        table = new Table();
        table.setBounds(0, 476, 176, 176); //TODO
        table.align(Align.topLeft);
    }

    public void setActionFactory(ActionFactory actionFactory)
    {
        this.actionFactory = actionFactory;
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
        table.clear();
        int count = 0;

        for (Item selected: selection)
        {
            Drawable icon = (Drawable)selected.getProperty(new Identifier("Icon"));
            if (icon != null)
            {
                Button button = getButton(icon);
                button.addListener(new ButtonHandler(selected));

                Cell<Button> cell = table.add(button);
                cell.padLeft(10);
                cell.padTop(10);

                if (++count % 3 == 0){
                    table.row();
                }
            }
        }
    }

    //TODO: Add outline
    private Button getButton(Drawable icon)
    {
        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.up = icon;
        buttonStyle.down = icon;
        buttonStyle.over = icon;
        return new ImageButton(buttonStyle);
    }

    private class ButtonHandler extends ChangeListener
    {
        private Item target;

        public ButtonHandler(Item target)
        {
            this.target = target;
        }

        @Override
        public void changed(ChangeEvent event, Actor actor)
        {
            //Action action = actionFactory.newAction(new Identifier("Select"), target, false);
            //target.addAction(action);
        }
    }
}
