/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
