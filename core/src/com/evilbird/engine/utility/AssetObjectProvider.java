package com.evilbird.engine.utility;

import javax.inject.Provider;

public interface AssetObjectProvider<T> extends Provider<T>
{
    void load();
}
