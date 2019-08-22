
-- 创建数据库
CREATE DATABASE springboot DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE `girl` (
  `id` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;