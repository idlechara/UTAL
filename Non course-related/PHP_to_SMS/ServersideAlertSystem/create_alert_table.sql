--This table must be created on the database, in case of edition, changes must be relfected on get.php and post.php files.

delimiter $$

CREATE TABLE `alerts` (
  --id
  `idalerts` int(11) NOT NULL AUTO_INCREMENT,
  
  --message to send
  `message` text NOT NULL,
  
  --bool if it's read
  `read` bit(1) NOT NULL DEFAULT b'0',
  
  --target number
  `target` text NOT NULL,
  
  PRIMARY KEY (`idalerts`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1$$


