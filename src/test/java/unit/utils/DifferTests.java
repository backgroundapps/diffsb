/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package unit.utils;

import diff.Differ;
import diff.utils.FileUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DifferTests {

    @Test
	public void shouldCompareFileNotFound() throws Exception {
        String fileName = "z";

        assertEquals("FILE NOT FOUND", new Differ(fileName).invoke());

	}

    @Test
    public void shouldReturnEquals() throws Exception {
        String fileName = "file";

        FileUtils.createFile(fileName+"_left", "\"{\\\"name\\\": \\\"a\\\"}\"", ".json");
        FileUtils.createFile(fileName+"_right", "\"{\\\"name\\\": \\\"a\\\"}\"", ".json");

        assertEquals("EQUALS", new Differ(fileName).invoke());

    }

    @Test
	public void shouldReturnADDED() throws Exception {
        String fileName = "file";

        FileUtils.createFile(fileName+"_left", "{\"name\": \"a\"}", ".json");
        FileUtils.createFile(fileName+"_right", "{\"name\": \"a\", \"name\": \"b\", \"name\": \"c\"}", ".json");
        String result = "ADDED: \"name\": \"b\", \"name\": \"c\"}";

        assertEquals(result, new Differ(fileName).invoke());

	}

	@Test
	public void shouldReturnREMOVED() throws Exception {
        String fileName = "file";

        FileUtils.createFile(fileName+"_left", "{\"name\": \"a\", \"name\": \"b\", \"name\": \"c\"}", ".json");
        FileUtils.createFile(fileName+"_right", "{\"name\": \"a\"}", ".json");
        String result = "REMOVED: \"name\": \"b\", \"name\": \"c\"}";

        assertEquals(result, new Differ(fileName).invoke());

	}

}
