
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../css/styleLog.css"> 
	<script type = "text/javascript" src = "../JAVASCRIPT/login.js" ></script>
</head>
<body>
  <nav> <?php include ("../navigation/header2.php"); ?> </nav>
<?php include "../User/login.php"; ?>
	<div class="header" id = "form">
		<h2>Login</h2>
	</div>
	<form method="post" action="login.php" name = "loginform" onsubmit = "return(CheckInputs());">

		<?php echo $error;?>
		

		<div class="input-group">
			<label>Username</label>
			<input type="text" name="username" id = "username"> <small id = "usernameError">  </small>
		</div>
		<div class="input-group">
			<label>Password</label>
			<input type="password" name="password" id = "password"> <small id = "passwordError">  </small>
		</div>
		<div class="input-group">
			<button type="submit" class="btn" name="login_btn">Login</button> 
		</div>
		<p>
			Not yet a member? <a href="registration.php">Get Notified!</a>
		</p>
	</form>
</body>
</html>