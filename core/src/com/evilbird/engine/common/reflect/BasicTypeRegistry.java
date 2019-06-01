/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.reflect;

import com.evilbird.engine.common.error.ReflectionException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A {@link TypeRegistry} implementation, where type bindings are made by
 * external clients and stored in a bi-directional map.
 *
 * @author Blair Butterworth
 */
public class BasicTypeRegistry implements TypeRegistry
{
    private Map<Class<?>, String> aliases;
    private Map<String, Class<?>> types;

    public BasicTypeRegistry() {
        aliases = new HashMap<>();
        types = new HashMap<>();
    }

    /**
     * Obtains the moniker assigned to the given type if its available,
     * otherwise returning the types full name and package.
     *
     * @param type  the class whose moniker is desired. This parameter cannot
     *              be <code>null</code>.
     * @return      the moniker assigned to the class or the full class and
     *              package of the class. This method will not return
     *              <code>null</code>.
     */
    @Override
    public String getName(Class<?> type) {
        Objects.requireNonNull(type);
        if (aliases.containsKey(type)) {
            return aliases.get(type);
        }
        return type.getName();
    }

    /**
     * Obtains the class assigned to the given moniker if its available. If not
     * then it will assumed that the given name is the full class and package
     * and an attempt will be made to create a class instance based on this.
     *
     * @param name  the moniker whose class is desired. This parameter cannot
     *              be <code>null</code>.
     * @return      the class assigned to the moniker or class whose name and
     *              package matched the given name. This method will not return
     *              <code>null</code>.
     */
    @Override
    public Class<?> getType(String name) {
        Objects.requireNonNull(name);
        if (types.containsKey(name)) {
            return types.get(name);
        }
        return getTypeClassloader(name);
    }

    /**
     * Associates the given type with the given alias.
     *
     * @param alias a moniker that will refer to the given type. This
     *              parameter cannot be <code>null</code>.
     * @param type  the type that the moniker will be associated with. This
     *              parameter cannot be <code>null</code>.
     */
    protected void registerType(String alias, Class<?> type) {
        Objects.requireNonNull(alias);
        Objects.requireNonNull(type);
        aliases.put(type, alias);
        types.put(alias, type);
    }

    private Class<?> getTypeClassloader(String name) {
        try {
            return Class.forName(name);
        }
        catch (ClassNotFoundException error) {
            throw new ReflectionException(error);
        }
    }
}
