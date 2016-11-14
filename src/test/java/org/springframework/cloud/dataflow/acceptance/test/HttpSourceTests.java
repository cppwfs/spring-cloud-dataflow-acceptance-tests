/*
 * Copyright 2016 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.springframework.cloud.dataflow.acceptance.test;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Glenn Renfro
 */

public class HttpSourceTests extends AbstractStreamTests {

	@Test
	public void logTests() {
		Stream stream = getStream("http-test");
		stream.setSink("log");
		stream.setSource("http --port=9000");
		stream.setDefinition(stream.getSource() + " | " +stream.getSink());
		deployStream(stream);
		httpPostData(stream.getSource(), 9000, "1341241234");
		waitForMillis(2000);
		String result = getLog(stream.getSink());
		assertTrue(result.contains("1341241234"));
	}
}