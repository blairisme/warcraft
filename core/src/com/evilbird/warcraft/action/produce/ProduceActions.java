/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.ActionIdentifier;

/**
 * Defines options of specifying training action varieties.
 *
 * @author Blair Butterworth
 */
public enum ProduceActions implements ActionIdentifier
{
    TrainFootman,
    TrainPeasant,

    UpgradeArrowDamage,

    TrainFootmanCancel,
    TrainPeasantCancel,

    UpgradeArrowDamageCancel
}