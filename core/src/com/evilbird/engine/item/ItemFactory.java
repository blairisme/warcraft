package com.evilbird.engine.item;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.graphics.DirectionalAnimation;
import com.evilbird.engine.utility.Identifier;

import java.util.Map;

public interface ItemFactory
{
    public void load(AssetManager assetManager);

    public Item newItem(Identifier type, Identifier id);

    public Item newItem(Identifier type, Identifier id, Map<Identifier, DirectionalAnimation> additionalAnimations);
}
