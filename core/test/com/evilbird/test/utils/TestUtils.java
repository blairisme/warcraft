/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.utils;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Contains common utility functions for use in unit tests.
 *
 * @author Blair Butterworth
 */
public class TestUtils
{
    private TestUtils() {
    }

    public static String readResource(String path) throws IOException {
        try(InputStream input = TestUtils.class.getResourceAsStream(path);
            ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            IOUtils.copy(input, output);
            return output.toString(StandardCharsets.UTF_8.name());
        }
    }
}
