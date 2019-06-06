DROP TABLE if EXISTS probe;
create table probe(
id BIGINT,
mac VARCHAR(32),
db INT,
begin_time datetime,
end_time datetime,
assets_id INT,
time timestamp,
is_old SMALLINT,
PRIMARY key(id,end_time)
)ENGINE=INNODB
PARTITION BY RANGE(TO_DAYS(end_time))(
PARTITION p20190419 VALUES LESS THAN (TO_DAYS('2019-04-19')) ENGINE = InnoDB,
 PARTITION p20190420 VALUES LESS THAN (TO_DAYS('2019-04-20')) ENGINE = InnoDB,
 PARTITION p20190421 VALUES LESS THAN (TO_DAYS('2019-04-21')) ENGINE = InnoDB,
 PARTITION p20190422 VALUES LESS THAN (TO_DAYS('2019-04-22')) ENGINE = InnoDB,
 PARTITION p20190423 VALUES LESS THAN (TO_DAYS('2019-04-23')) ENGINE = InnoDB
);


DELIMITER $$
#该表所在数据库名称
USE `visit`$$
DROP PROCEDURE IF EXISTS `create_partition_by_day`$$
CREATE PROCEDURE `create_partition_by_day`(IN_SCHEMANAME VARCHAR(64), IN_TABLENAME VARCHAR(64))
BEGIN
    #当前日期存在的分区的个数
    DECLARE ROWS_CNT INT UNSIGNED;
    #目前日期，为当前日期的后一天
    DECLARE TARGET_DATE TIMESTAMP;
    #分区的名称，格式为p20180620
    DECLARE PARTITIONNAME VARCHAR(9);
    #当前分区名称的分区值上限，即为 PARTITIONNAME + 1
    DECLARE PARTITION_ADD_DAY VARCHAR(11);
    SET TARGET_DATE = NOW() + INTERVAL 1 DAY;
    SET PARTITIONNAME = DATE_FORMAT( TARGET_DATE, 'p%Y%m%d' );
    SET TARGET_DATE = TARGET_DATE + INTERVAL 1 DAY;
    SET PARTITION_ADD_DAY = TO_DAYS(TARGET_DATE);
    SELECT COUNT(*) INTO ROWS_CNT FROM information_schema.partitions
    WHERE table_schema = IN_SCHEMANAME AND table_name = IN_TABLENAME AND partition_name = PARTITIONNAME;
    IF ROWS_CNT = 0 THEN
        SET @SQL = CONCAT( 'ALTER TABLE `', IN_SCHEMANAME, '`.`', IN_TABLENAME, '`',
        ' ADD PARTITION (PARTITION ', PARTITIONNAME, " VALUES LESS THAN (",
            PARTITION_ADD_DAY ,") ENGINE = InnoDB);" );
        PREPARE STMT FROM @SQL;
        EXECUTE STMT;
        DEALLOCATE PREPARE STMT;
     ELSE
       SELECT CONCAT("partition `", PARTITIONNAME, "` for table `",IN_SCHEMANAME, ".", IN_TABLENAME, "` already exists") AS result;
     END IF;
END$$
DELIMITER ;


DELIMITER $$
#该表所在的数据库名称
USE `visit`$$
CREATE EVENT IF NOT EXISTS `daily_generate_partition`
ON SCHEDULE EVERY 1 hour   #执行周期，还有天、月等等
STARTS '2019-04-24 00:00:00'
ON COMPLETION PRESERVE
ENABLE
COMMENT 'Creating partitions'
DO BEGIN
    #调用刚才创建的存储过程，第一个参数是数据库名称，第二个参数是表名称
    CALL datacollectcenter.create_partition_by_day('visit','probe');
END$$
DELIMITER ;