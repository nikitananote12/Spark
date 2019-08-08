package com

import java.nio.file.{Path, Paths}

import org.apache.hadoop.fs.LocalFileSystem
import org.apache.hadoop.hdfs.DistributedFileSystem
import org.apache.spark.sql.SparkSession



object dataFrame {
  def main(args: Array[String]): Unit = {
//    val path = System.getProperty("user.dir")
//    println(path)
//    val path1 = Paths.get(path+"/src/main/scala/com/table.csv").toString
    //println(args(0))
val spark = SparkSession.builder()
  .master("local[*]").appName("dataframe").getOrCreate()
    val sc = spark.sparkContext

    sc.hadoopConfiguration.set("fs.hdfs.impl", classOf[DistributedFileSystem].getName)
    sc.hadoopConfiguration.set("fs.file.impl", classOf[LocalFileSystem].getName)

    val file1 = spark.read.option("header","true")
      .option("inferschema","true").format("com.databricks.spark.csv")
      .load(args(0))
    file1.printSchema()
    file1.show()
  }
}
