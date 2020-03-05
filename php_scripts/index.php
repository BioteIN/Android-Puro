<?php 




?>
<h2>Create text</h2>
<form action="create.php" method="post">
	<input type="text" name="nome" placeholder="Nome">
	<input type="text" name="telefone" placeholder="Telefone">
	<input type="text" name="email" placeholder="Email">
	<input type="submit" name="salvar">
</form>

<h2>Update text</h2>
<form action="update.php" method="post">
	<input type="text" name="id" placeholder="Id">
	<input type="text" name="nome" placeholder="Nome">
	<input type="text" name="telefone" placeholder="Telefone">
	<input type="text" name="email" placeholder="Email">
	<input type="submit" name="actualizar">
</form>