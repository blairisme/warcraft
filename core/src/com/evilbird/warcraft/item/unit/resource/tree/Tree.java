package com.evilbird.warcraft.item.unit.resource.tree;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.warcraft.item.common.animation.AnimationUtils;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.resource.Resource;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Tree extends Resource
{
    private Cell cell;

    public Tree()
    {
    }

    public void setCell(Cell cell)
    {
        this.cell = cell;
        setItemTexture();
    }

    @Override
    public void setAnimation(AnimationIdentifier animationId)
    {
        super.setAnimation(animationId);
        super.updateAnimation();
        setCellTexture();
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
    }

    private void setCellTexture()
    {
        if (cell != null){
            cell.setTile(new StaticTiledMapTile(getAnimationFrame()));
        }
    }

    private void setItemTexture()
    {
        TiledMapTile tile = cell.getTile();
        TextureRegion texture = tile.getTextureRegion();
        DirectionalAnimation animation = AnimationUtils.getAnimation(texture); //TODO: replace use of deprecated method
        setAvailableAnimation(UnitAnimation.Idle, animation);
        setAvailableAnimation(UnitAnimation.GatherWood, animation);
    }
}