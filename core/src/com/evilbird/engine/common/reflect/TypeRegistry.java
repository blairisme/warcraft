/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.reflect;

/**
 * Implementors of this interface allow types to be obtained by a short
 * monikers: aliases.
 *
 * @author Blair Butterworth
 */
public interface TypeRegistry
{
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
    String getName(Class<?> type);

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
    Class<?> getType(String name);
}
