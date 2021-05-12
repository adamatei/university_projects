<?php 
  include("delete.php");
?>
<!DOCTYPE html>
<html>
<head>  
	<link rel="stylesheet" type="text/css" href="../css/styleHomePageUser.css">
	<link rel="stylesheet" type="text/css" href="../css/mediaqueries.css">
	<link rel="stylesheet" type="text/css" href="../css/adminHome.css">
	<link rel="stylesheet" type="text/css" href="../css/delete.css">
	<script type = "text/javascript" src = "../JAVASCRIPT/delete.js" ></script>

</head>
<body>
<?php include ('../User/userCheck.php');?>
	<div class="header">


<!-- Logo -->
	<?php include '../User/profileUpdate.php';?>

 
	</div>

	<div class="content">



<!-- Navigation bar-->
 <div id = navbarad>
 <nav><?php 
 include("../navigation/headerAdmin.php");
 ?> </nav>
 </div> 

 <br><br><br><br>

<div class = "requests">
<?php include('deleteRequests.php'); ?>
</div>
 <br><br><br><br>

<!-- Form for deleting users -->
	
<div class = "center"> 
<br><br>
<h1>Confirm username:</h1> 
<br><br>
    <form method="post" class ="formStyle" action="users.php" name = "deleteform" onsubmit = "return(CheckInputs());">

		<?php echo $error;?>
		

		
			<h3> Username</h3> 
			<br>
			<input type="text" name="username" id = "username"> <small id = "usernameError">  </small>
		

	<br><br>
		
			<button type="submit" class="btn" name="delete_btn">Delete</button> 
		
		
    </form>	
</div>

<!--View all account delete request-->
<br><br><br><br>
</div>		
 
</body>
</html>