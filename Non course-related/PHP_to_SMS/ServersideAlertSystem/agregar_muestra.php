<?php 
/*
	require_once("Mail.php");
	include_once("Mail.php");

	function send_email( $id_camara, $temperatura )
	{
		$from = 'notificaciones.example@example.com';
		$to = 'notificaciones.example@example.com';
		$subject = '[EXAMPLE] Temperatura Anormal';
		$body = "Estimado, \n\n La cámara \"$nombre_camara\" ha registrado una temperatura anormal de \"$temperatura\"°C";

		$headers = array(
		    'From' => $from,
		    'To' => $to,
		    'Subject' => $subject
		);

		$smtp = Mail::factory('smtp', array(
		        'host' => 'ssl://smtp.example.com',
		        'port' => '465',
		        'auth' => true,
		        'username' => 'notificaciones.example@example.com',
		        'password' => 'notificaciones.2013'
		    ));

		$mail = $smtp->send($to, $headers, $body);

	}
*/
	
	if( $_POST )
	{
		/*$connection=mysqli_connect("db_host","db","db_user","db");

		$id_camara = $_POST['id_camara'];
		$temperatura = $_POST['temperatura'];

		$sql = "insert into medicion(id_camara,temperatura) values($id_camara,$temperatura)";
		mysqli_query($connection, $sql );
		mysqli_close($connection);
		*/
		echo "OK";

	}
	else
	{
		//echo "<html><head><title>Servicio no disponible</title></head><body><center><h1>Servicio no disponible</h1></center></body></html>";
		$connection=mysqli_connect("db_host","db","db_user","db");
		
		$id_camara = $_GET['id_camara'];
		$temperatura = $_GET['temperatura'];
		$id_evaporador = $_GET['id_evaporador'];
		$id_sensor = $_GET['id_sensor'];
		$status = $_GET['status'];

		if ($temperatura > (float) 50.1) {

			$sql = "INSERT INTO `alerts` (`message`, `target`, `read`) VALUES ('ALERTA EN CAMARA ".$id_camara."TEMPERATURA: ".$temperatura."', '77752972', '0')";
			mysqli_query($connection, $sql );
		}

		$sql = "insert into medicion(id_camara,id_evaporador,id_sensor,temperatura,status) values($id_camara,$id_evaporador,$id_sensor,$temperatura,$status)";
		//$sql = "insert into medicion(id_camara,id_evaporador,id_sensor,temperatura,status) values($id_camara,$id_evaporador,$id_sensor,$temperatura,$status)";
		//echo $sql."<br />";
		mysqli_query($connection, $sql );
		mysqli_close($connection);
		
		echo "OK";
	}


	




?>