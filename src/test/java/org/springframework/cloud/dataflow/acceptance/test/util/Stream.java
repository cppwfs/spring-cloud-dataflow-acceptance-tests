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

package org.springframework.cloud.dataflow.acceptance.test.util;

import org.springframework.cloud.dataflow.rest.client.AppRegistryOperations;
import org.springframework.util.Assert;

/**
 * Manages the apps of a stream during the execution of a acceptance test.
 * This is done establishing the configuration such that the actuator end points
 * for each app are accessible.
 *
 * @author Glenn Renfro
 */
public class Stream {

	private String streamName;

	private Application source;

	private Application sink;

	private String definition;

	private String appLogDir;

	/**
	 * Initialize the stream instance.
	 * @param streamName the name of the stream.
	 * @param appLogDir the directory where apps in the stream will write their
	 * logs.
	 */
	public Stream(String streamName,  String appLogDir){
		Assert.hasText(streamName, "streamName must not be empty nor null");
		Assert.hasText(appLogDir, "appLogDir must not be empty nor null");
		this.streamName = streamName;
		this.appLogDir = appLogDir;
	}

	/**
	 * Sets the registered app name and its properties for the sink.  The default
	 * log location will be added to the sink app properties.
	 * @param definition the registered app name and its properties for the sink.
	 */
	public void setSink(String definition) {
		String sinkLog = String.format("%s/%s-%s", appLogDir,
				streamName, "sink.txt");
		sink = new Application(0, sinkLog , definition);
	}

	/**
	 * Retrieve the current sink
	 * @return the current sink.
	 */
	public Application getSink() {
		return sink;
	}

	/**
	 * Sets the registered app name and its properties for the source.  The
	 * default log location will be added to the source app properties.
	 * @param definition the registered app name and its properties for the source.
	 */
	public void setSource(String definition) {
		String sourceLog = String.format("%s/%s-%s", appLogDir,
				streamName, "source.txt");
		source = new Application(0, sourceLog, definition);
	}

	/**
	 * Retrieve the current source.
	 * @return the current source.
	 */
	public Application getSource() {
		return source;
	}

	/**
	 * Establish the definition of the stream.
	 * @param definition stream definition.
	 */
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	/**
	 * Retrieve the current stream definition.
	 * @return the stream definition.
	 */
	public String getDefinition() {
		return definition;
	}

	/**
	 * Retrieve the current stream name.
	 * @return the current stream name.
	 */
	public String getStreamName() {
		return streamName;
	}
}
