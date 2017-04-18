<?php header('Content-type: application/json');





//database info goes here
$username = "db_username";
$password = "db_password";
$hostname = "db_hostname"; 






$dbhandle = mysql_connect($hostname, $username, $password) 
 or die("Unable to connect to MySQL"); 
$selected = mysql_select_db("frucol",$dbhandle) or die("Could not select alerts");
$result = mysql_query("UPDATE alerts SET `read` = '1' WHERE `idalerts`='".htmlspecialchars($_POST['alert_to_cancel'])."';");
mysql_close($dbhandle);

?>

