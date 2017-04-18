<?php






//database info goes here.
$username = "db_username";
$password = "db_password";
$hostname = "db_hostname"; 








$dbhandle = mysql_connect($hostname, $username, $password) or die("Unable to connect to MySQL");
$selected = mysql_select_db("frucol",$dbhandle) or die("Could not select alerts");
$result = mysql_query("SELECT idalerts, message, target FROM alerts WHERE alerts.read='0' ORDER BY idalerts DESC LIMIT 1;");
mysql_close($dbhandle);
while ($row = mysql_fetch_array($result)) {
	$arr = array('id' => $row{'idalerts'}, 'msg'=>$row{'message'}, 'tgt'=>$row{'target'});
	print (json_encode($arr)); 
}

?>
