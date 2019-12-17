/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.vorto.middleware.plugins;

import java.util.Map;

import org.eclipse.vorto.model.runtime.InfomodelValue;

/**
 * Plugin to be implemented to process the normalized device data.
 * Example plugins could be storing the data in a timeseries database
 *
 */
public interface IPlugin {

  /**
   * unique ID of the plugin
   * @return
   */
  String getId();
  
  /**
   * Descriptive name of the plugin, for displaying in UI for example
   * @return
   */
  String getName();
  
  /**
   * Description of what the plugin does
   * @return
   */
  String getDescription();
  
  /**
   * Image URL of the plugin
   * @return
   */
  String getImageUrl();
  
  /**
   * status whether the plugin is active or not
   * @return
   */
  boolean isStarted();
  
  /**
   * Handles the normalized device payload
   * @param value normalized/mapped device data complying to Vorto Information Model
   * @param context context information about the payload, such as device ID
   * 
   * @throws ExecutionProblem whenever something goes wrong during execution of the plugin
   */
  void execute(InfomodelValue value, ExecutionContext context) throws ExecutionProblem;
  
  /**
   * gets the configuration values for the plugin
   * @return
   */
  Map<String, TextConfigurationItem> getConfiguration();
  
  
  /**
   * 
   *
   */
  public static class ExecutionProblem extends RuntimeException {
	  
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExecutionProblem(String message, Throwable t) {
		  super(message,t);
	  }
  }
}
