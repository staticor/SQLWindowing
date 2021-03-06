CREATE TABLE lineitem (L_ORDERKEY      INT,
                                L_PARTKEY       INT,
                                L_SUPPKEY       INT,
                                L_LINENUMBER    INT,
                                L_QUANTITY      DOUBLE,
                                L_EXTENDEDPRICE DOUBLE,
                                L_DISCOUNT      DOUBLE,
                                L_TAX           DOUBLE,                                                                         
                                L_RETURNFLAG    STRING,                                                                         
                                L_LINESTATUS    STRING,                                                                         
                                l_shipdate      STRING,                                                                         
                                L_COMMITDATE    STRING,                                                                         
                                L_RECEIPTDATE   STRING,                                                                         
                                L_SHIPINSTRUCT  STRING,                                                                         
                                L_SHIPMODE      STRING,                                                                         
                                L_COMMENT       STRING)                                                                         
ROW FORMAT DELIMITED                                                                                                            
FIELDS TERMINATED BY '|';


LOAD DATA LOCAL INPATH '/home/pkalmegh/Projects/SQLWindowing/windowing/src/test/groovy/data/lineitem/lineitem.txt' OVERWRITE INTO TABLE lineitem;

CREATE TABLE sorted_lineitem
   ROW FORMAT DELIMITED                                                                                                            
FIELDS TERMINATED BY '|' AS
SELECT *
FROM lineitem
order BY l_extendedprice;

CREATE TABLE sorted_lineitem2
   ROW FORMAT DELIMITED                                                                                                            
FIELDS TERMINATED BY '|' AS
SELECT *
FROM lineitem
order BY l_quantity;
