/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.forest;

import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.warcraft.item.unit.resource.tree.TreeProvider;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Forest Forests}.
 *
 * @author Blair Butterworth
 */
public class ForestProvider implements AssetProvider<Forest>
{
    private TreeProvider treeProvider;

    @Inject
    public ForestProvider(TreeProvider treeProvider)
    {
        this.treeProvider = treeProvider;
    }

    @Override
    public void load()
    {
        treeProvider.load();
    }

    @Override
    public Forest get()
    {
        return new Forest(treeProvider);
    }
}
