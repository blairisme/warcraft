package com.evilbird.engine.common.inject;

import javax.inject.Provider;

public interface AssetObjectProvider<T> extends Provider<T>
{
    void load();
}
