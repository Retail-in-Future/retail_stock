CREATE TABLE `stock` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `price` float DEFAULT NULL,
  `unit` varchar(25) DEFAULT NULL,
  `amount` bigint(10) DEFAULT NULL,
  `stock_out` bigint(10) DEFAULT NULL,
  `sku` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;