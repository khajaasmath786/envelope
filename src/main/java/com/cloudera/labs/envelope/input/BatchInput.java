/**
 * Copyright © 2016-2017 Cloudera, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cloudera.labs.envelope.input;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

/**
 * Batch inputs read in a DataFrame from a static external source.
 * Custom inputs that point to a static data source should implement BatchInput.
 */
public interface BatchInput extends Input {

  /**
   * Read the external source data into the Spark application.
   * @return The DataFrame of the input.
   * @throws Exception
   */
  Dataset<Row> read() throws Exception;

}
