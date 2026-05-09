-- ----------------------------
-- Chat2DB export data , export time: 2026-05-06 11:05:35
-- ----------------------------
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for table department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `department_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `location` varchar(100) DEFAULT NULL COMMENT '地点',
  `manager` varchar(50) DEFAULT NULL COMMENT '管理者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for table employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '员工姓名',
  `hiredate` date DEFAULT NULL COMMENT '入职日期',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别:男/女/未知',
  `department` varchar(10) DEFAULT NULL COMMENT '部门',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1779724290 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS=1;
