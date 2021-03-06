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
package com.cloudera.labs.envelope.derive.dq;


import com.cloudera.labs.envelope.spark.RowWithSchema;
import com.google.common.collect.Lists;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestRangeRowRule {

  @Test
  public void testAgeRangeInt() {
    StructType schema = new StructType(new StructField[] {
        new StructField("name", DataTypes.StringType, false, Metadata.empty()),
        new StructField("nickname", DataTypes.StringType, false, Metadata.empty()),
        new StructField("age", DataTypes.IntegerType, false, Metadata.empty()),
        new StructField("candycrushscore", DataTypes.createDecimalType(), false, Metadata.empty())
    });

    Map<String, Object> configMap = new HashMap<>();
    configMap.put("fields", Lists.newArrayList("age"));
    configMap.put("fieldtype", "int");
    configMap.put("range", Lists.newArrayList(0,105));
    Config config = ConfigFactory.parseMap(configMap);

    RangeRowRule rule = new RangeRowRule();
    rule.configure("agerange", config);

    Row row1 = new RowWithSchema(schema, "Ian", "Ian", 34, new BigDecimal("0.00"));
    assertTrue("Row should pass rule", rule.check(row1));

    Row row2 = new RowWithSchema(schema, "Webster1", "Websta1", 110, new BigDecimal("450.10"));
    assertFalse("Row should not pass rule", rule.check(row2));

    Row row3 = new RowWithSchema(schema, "", "Ian1", 106, new BigDecimal("450.10"));
    assertFalse("Row should not pass rule", rule.check(row3));

    Row row4 = new RowWithSchema(schema, "First Last", "Ian Last", 105, new BigDecimal("450.10"));
    assertTrue("Row should pass rule", rule.check(row4));
  }

  @Test
  public void testAgeRangeLong() {
    StructType schema = new StructType(new StructField[] {
        new StructField("name", DataTypes.StringType, false, Metadata.empty()),
        new StructField("nickname", DataTypes.StringType, false, Metadata.empty()),
        new StructField("age", DataTypes.LongType, false, Metadata.empty()),
        new StructField("candycrushscore", DataTypes.createDecimalType(), false, Metadata.empty())
    });

    Map<String, Object> configMap = new HashMap<>();
    configMap.put("fields", Lists.newArrayList("age"));
    configMap.put("range", Lists.newArrayList(0l,105l));
    Config config = ConfigFactory.parseMap(configMap);

    RangeRowRule rule = new RangeRowRule();
    rule.configure("agerange", config);

    Row row1 = new RowWithSchema(schema, "Ian", "Ian", 34l, new BigDecimal("0.00"));
    assertTrue("Row should pass rule", rule.check(row1));

    Row row2 = new RowWithSchema(schema, "Webster1", "Websta1", 110l, new BigDecimal("450.10"));
    assertFalse("Row should not pass rule", rule.check(row2));

    Row row3 = new RowWithSchema(schema, "", "Ian1", 110l, new BigDecimal("450.10"));
    assertFalse("Row should not pass rule", rule.check(row3));

    Row row4 = new RowWithSchema(schema, "First Last", "Ian Last", 100l, new BigDecimal("450.10"));
    assertTrue("Row should pass rule", rule.check(row4));
  }

  @Test
  public void testAgeRangeFloat() {
    StructType schema = new StructType(new StructField[] {
        new StructField("name", DataTypes.StringType, false, Metadata.empty()),
        new StructField("nickname", DataTypes.StringType, false, Metadata.empty()),
        new StructField("age", DataTypes.FloatType, false, Metadata.empty()),
        new StructField("candycrushscore", DataTypes.createDecimalType(), false, Metadata.empty())
    });

    Map<String, Object> configMap = new HashMap<>();
    configMap.put("fields", Lists.newArrayList("age"));
    configMap.put("fieldtype", "float");
    configMap.put("range", Lists.newArrayList(0.1,105.0));
    Config config = ConfigFactory.parseMap(configMap);

    RangeRowRule rule = new RangeRowRule();
    rule.configure("agerange", config);

    Row row1 = new RowWithSchema(schema, "Ian", "Ian", 34.0f, new BigDecimal("0.00"));
    assertTrue("Row should pass rule", rule.check(row1));

    Row row2 = new RowWithSchema(schema, "Webster1", "Websta1", 110.0f, new BigDecimal("450.10"));
    assertFalse("Row should not pass rule", rule.check(row2));

    Row row3 = new RowWithSchema(schema, "", "Ian1", 110.0f, new BigDecimal("450.10"));
    assertFalse("Row should not pass rule", rule.check(row3));

    Row row4 = new RowWithSchema(schema, "First Last", "Ian Last", 100.0f, new BigDecimal("450.10"));
    assertTrue("Row should pass rule", rule.check(row4));
  }

  @Test
  public void testAgeRangeDouble() {
    StructType schema = new StructType(new StructField[] {
        new StructField("name", DataTypes.StringType, false, Metadata.empty()),
        new StructField("nickname", DataTypes.StringType, false, Metadata.empty()),
        new StructField("age", DataTypes.DoubleType, false, Metadata.empty()),
        new StructField("candycrushscore", DataTypes.createDecimalType(), false, Metadata.empty())
    });

    Map<String, Object> configMap = new HashMap<>();
    configMap.put("fields", Lists.newArrayList("age"));
    configMap.put("fieldtype", "float");
    configMap.put("range", Lists.newArrayList(0.1,105.0));
    Config config = ConfigFactory.parseMap(configMap);

    RangeRowRule rule = new RangeRowRule();
    rule.configure("agerange", config);

    Row row1 = new RowWithSchema(schema, "Ian", "Ian", 34.0, new BigDecimal("0.00"));
    assertTrue("Row should pass rule", rule.check(row1));

    Row row2 = new RowWithSchema(schema, "Webster1", "Websta1", 110.0, new BigDecimal("450.10"));
    assertFalse("Row should not pass rule", rule.check(row2));

    Row row3 = new RowWithSchema(schema, "", "Ian1", 110.0, new BigDecimal("450.10"));
    assertFalse("Row should not pass rule", rule.check(row3));

    Row row4 = new RowWithSchema(schema, "First Last", "Ian Last", 100.0, new BigDecimal("450.10"));
    assertTrue("Row should pass rule", rule.check(row4));
  }

  @Test
  public void testAgeRangeDecimal() {
    StructType schema = new StructType(new StructField[] {
        new StructField("name", DataTypes.StringType, false, Metadata.empty()),
        new StructField("nickname", DataTypes.StringType, false, Metadata.empty()),
        new StructField("age", DataTypes.DoubleType, false, Metadata.empty()),
        new StructField("candycrushscore", DataTypes.createDecimalType(), false, Metadata.empty())
    });

    Map<String, Object> configMap = new HashMap<>();
    configMap.put("fields", Lists.newArrayList("candycrushscore"));
    configMap.put("fieldtype", "decimal");
    configMap.put("range", Lists.newArrayList("-1.56","400.45"));
    Config config = ConfigFactory.parseMap(configMap);

    RangeRowRule rule = new RangeRowRule();
    rule.configure("agerange", config);

    Row row1 = new RowWithSchema(schema, "Ian", "Ian", 34.0, new BigDecimal("-1.00"));
    assertTrue("Row should pass rule", rule.check(row1));

    Row row2 = new RowWithSchema(schema, "Webster1", "Websta1", 110.0, new BigDecimal("-1.57"));
    assertFalse("Row should not pass rule", rule.check(row2));

    Row row3 = new RowWithSchema(schema, "", "Ian1", 110.0, new BigDecimal("450.10"));
    assertFalse("Row should not pass rule", rule.check(row3));

    Row row4 = new RowWithSchema(schema, "First Last", "Ian Last", 100.0, new BigDecimal("400.45"));
    assertTrue("Row should pass rule", rule.check(row4));
  }

}
