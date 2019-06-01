/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.math;

import org.apache.commons.lang3.Validate;

import java.util.Random;

/**
 * Instances of this class are used to generate a stream of pseudorandom
 * numbers.
 *
 * @author Blair Butterworth
 */
public class RandomGenerator
{
    private Random random;

    public RandomGenerator() {
        random = new Random();
    }

    public int nextInt(int lowerBound, int upperBound) {
        Validate.isTrue(lowerBound < upperBound);
        return random.nextInt(upperBound - lowerBound) + lowerBound;
    }
}
