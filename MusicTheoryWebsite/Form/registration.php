<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../css/styleLog.css">
	<script type = "text/javascript" src = "../JAVASCRIPT/register.js" ></script>
</head>
<body>

  <nav> <?php include ("../navigation/header2.php"); ?> </nav>
<?php include "../User/registration.php"; ?>

<div class="header" id = "form">
	<h2>Sign up</h2>
</div>
<form method="post" action="registration.php" name = "registerform" onsubmit = "return(Check());">
<?php 
	for($i=0; $i<count($error); $i++){
     echo $error[$i];
    }
	?>
	<div class="input-group">
		<label>Username</label>
		<input type="text" name="username" id = "username"> <small id = "usernameError">  </small>
	</div>
	<div class="input-group">
		<label>Email</label>
		<input type="email" name="email" id = "email" > <small id = "emailError">  </small>
	</div>
	<div class="input-group">
		<label>Password</label>
		<input type="password" name="password_1" id ="password_1" > <small id = "passwordError1">  </small>
	</div>
	<div class="input-group">
		<label>Confirm password</label>
		<input type="password" name="password_2" id = "password_2"> <small id = "passwordError2">  </small>
	</div>
	<div class="input-group">
		<button type="submit" class="btn" name="register_btn">Sign up</button>
	</div> 
	<p>
		Already a member? <a href="login.php">Tune in!</a>
	</p>
</form>
</body>
</html>