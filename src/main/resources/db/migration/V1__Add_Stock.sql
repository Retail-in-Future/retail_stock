CREATE TABLE `product` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `code` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `stock` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `price` float DEFAULT NULL,
  `amount` bigint(10) DEFAULT NULL,
  `stock_out` bigint(10) DEFAULT NULL,
  `product_id` bigint(10),
    INDEX pro_ind (product_id),
    FOREIGN KEY (product_id)
        REFERENCES product(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;