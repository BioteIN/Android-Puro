<?php 
$dns = "mysql:host=localhost; dbname=CrudAndroidPhp";
$usuario = "root";
$password = "";

try{

	$pdo = new PDO($dns,$usuario,$password);

}catch(PDOException $e){
	echo $e->getMessage();
}

?>