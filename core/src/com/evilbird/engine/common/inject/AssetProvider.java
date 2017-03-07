package com.evilbird.engine.common.inject;

import javax.inject.Provider;

public interface AssetProvider<T> extends Provider<T>
{
    void load();
}
