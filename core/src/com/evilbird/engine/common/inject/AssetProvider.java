package com.evilbird.engine.common.inject;

import com.evilbird.engine.common.lang.Persisted;

import javax.inject.Provider;

public interface AssetProvider<T> extends Provider<T>, Persisted
{
}
