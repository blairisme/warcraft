package com.evilbird.engine.common.inject;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public interface IdentifiedAssetProvider<T>
{
    void load();

    T get(Identifier identifier);
}
