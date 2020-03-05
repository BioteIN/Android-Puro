<?php

	include "conexao.php";

	$id = $_POST['id'];
	$nome = $_POST['nome'];
	$telefone = $_POST['telefone'];
	$email = $_POST['email'];

	$sql_insert = "UPDATE contactos SET nome=:nome , telefone=:telefone , email=:email WHERE id=:id";
	$stmt = $pdo->prepare($sql_insert);
	$stmt->bindParam(":id", $id);
	$stmt->bindParam(":nome", $nome);
	$stmt->bindParam(":telefone", $telefone);
	$stmt->bindParam(":email", $email);

	if($stmt->execute()){

		$dados = array("UPDATE" => "OK");
	}
	else
	{
		$dados = array("UPDATE" => "ERROR");
	}

echo json_encode($dados);

