<?php

	include "conexao.php";

	$sql_read = "SELECT * FROM contactos";
	$dados = $pdo->query($sql_read);

	$resultado = array();

	while($cont = $dados-> fetch(PDO::FETCH_OBJ)){
		$resultado[] = array("id"=>$cont->id,"nome"=>$cont->nome, "telefone"=>$cont->telefone, "email"=>$cont->email);
	}

	echo json_encode($resultado); 

?>