package com

import java.nio.file.{Path, Paths}

import org.apache.spark.sql.SparkSession



object dataFrame {
  def main(args: Array[String]): Unit = {
    val path = System.getProperty("user.dir")
    val path1 = Paths.get(path+"/src/main/scala/com/table.csv").toString
val spark = SparkSession.builder()
  .master("local[*]").appName("dataframe").getOrCreate()
    val sc = spark.sparkContext
    val file1 = spark.read.option("header","true")
      .option("inferschema","true")
      .csv(path1)
    file1.printSchema()
    file1.show()
  }
}
