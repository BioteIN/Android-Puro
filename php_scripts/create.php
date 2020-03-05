<?php

	include "conexao.php";

	$nome = $_POST['nome'];
	$telefone = $_POST['telefone'];
	$email = $_POST['email'];

	$sql_insert = "INSERT INTO contactos(nome, telefone , email) VALUES(:nome, :telefone, :email)";
	$stmt = $pdo->prepare($sql_insert);
	$stmt->bindParam(":nome", $nome);
	$stmt->bindParam(":telefone", $telefone);
	$stmt->bindParam(":email", $email);

	if($stmt->execute()){
		$id = $pdo->lastInsertId();

		$dados = array("CREATE" => "OK", "ID" => $id);
	}
	else
	{
		$dados = array("CREATE" => "ERROR");
	}

echo json_encode($dados);

?>