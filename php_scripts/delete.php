<?php

	include "conexao.php";

	$id = $_POST['id'];

	$sql_insert = "DELETE FROM contactos  WHERE id=:id";
	$stmt = $pdo->prepare($sql_insert);
	$stmt->bindParam(":id", $id);

	if($stmt->execute()){

		$dados = array("DELETE" => "OK");
	}
	else
	{
		$dados = array("DELETE" => "ERROR");
	}

echo json_encode($dados);

