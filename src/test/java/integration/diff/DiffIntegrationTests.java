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
package integration.diff;

import diff.DiffConfiguration;
import diff.utils.JSONUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

/**
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DiffConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
public class DiffIntegrationTests {

	@LocalServerPort
	private int port;

	@Value("${local.management.port}")
	private int mgt;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void shouldReturnLeftNotFound() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
				"http://localhost:" + this.port + "/v1/diff/z/right/eyJhIjoiYSJ9", Map.class);
		then(entity.getBody().get("content")).isEqualTo("LEFT FILE NOT FOUND");
	}

    @Test
    public void shouldCompareFileNotFound() throws Exception {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/v1/diff/z", Map.class);
        then(entity.getBody().get("content")).isEqualTo("FILE NOT FOUND");
    }

    @Test
    public void shouldCompareEQUALS() throws Exception {
        String left   = JSONUtils.encode("{\"name\": \"a\"}");
        String right  = JSONUtils.encode("{\"name\": \"a\"}");
        String result = "EQUALS";

        checkComparison(left, right, result);
    }

    @Test
    @Deprecated
    public void shouldReturnEQUALS() throws Exception {
        String left   = JSONUtils.encode("{\"name\": \"a\"}");
        String right  = JSONUtils.encode("{\"name\": \"a\"}");
        String result = "EQUALS";

        checkComparison(left, right, result);
    }

	@Test
	public void shouldReturnADDED() throws Exception {
        String left  = JSONUtils.encode("{\"name\": \"a\"}");
        String right = JSONUtils.encode("{\"name\": \"a\", \"name\": \"b\", \"name\": \"c\"}");
        String result = "ADDED: \"name\": \"b\", \"name\": \"c\"}";

        checkComparison(left, right, result);
	}

    @Test
    public void shouldReturnREMOVED() throws Exception {
        String left = JSONUtils.encode("{\"name\": \"a\", \"name\": \"b\", \"name\": \"c\"}");
        String right  = JSONUtils.encode("{\"name\": \"a\"}");
        String result = "REMOVED: \"name\": \"b\", \"name\": \"c\"}";

        checkComparison(left, right, result);
    }

    private void checkComparison(String left, String right, String result) {
        //First JSON included
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> leftEntity = this.testRestTemplate.getForEntity("http://localhost:" + this.port + "/v1/diff/file/left/" + left, Map.class);
        then(leftEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assert.assertTrue(new File("file_left.json").exists());

        //Second JSON included
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> rigthEntity = this.testRestTemplate.getForEntity("http://localhost:" + this.port + "/v1/diff/file/right/" + right, Map.class);
        Assert.assertTrue(new File("file_right.json").exists());

        //Comparing files
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity("http://localhost:" + this.port + "/v1/diff/file", Map.class);
        then(entity.getBody().get("content")).isEqualTo(result);

        //Check if file was removed
        Assert.assertFalse(new File("file_left.json").exists());
        Assert.assertFalse(new File("right_left.json").exists());
    }
}
