package com.evilbird.engine.state;

import com.evilbird.engine.item.ItemRoot;

/**
 * Implementors of this TODO:Finish
 *
 * @author Blair Butterworth
 */
public interface StateFactory
{
    void load();

    ItemRoot get(StateIdentifier identifier);
}
