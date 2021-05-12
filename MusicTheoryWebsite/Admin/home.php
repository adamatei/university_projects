<!DOCTYPE html>
<html>
<head> 
	<link rel="stylesheet" type="text/css" href="../css/styleHomePageUser.css">
	<link rel="stylesheet" type="text/css" href="../css/adminHome.css">

</head>
<body>
<?php include '../User/userCheck.php';?>
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

 <br>

		<!-- notification message -->
		<?php if (isset($_SESSION['success'])) : ?>
			<div class="error success" >
				<h3>
					<?php 
						echo $_SESSION['success']; 
						unset($_SESSION['success']);
					?>
				</h3>
			</div>
		<?php endif ?>


		<!-- logged in user information -->
		<div class="profile_info">
			<img src="../images/admin_profile.png"  >


			<div>
				<?php  if (isset($_SESSION['user_session'])) : ?>
					<strong><?php echo $_SESSION['user_session']['username']; ?></strong>

					<small>
						<i  style="color: #888;">(<?php echo ucfirst($_SESSION['user_session']['user_type']); ?>)</i> 
						<br>
						<a href="../index.php?logout='true'" style="color: red;">logout</a> 
					</small>

        <?php endif ?>
			</div>

		</div>




<!-- View comments-->
<br><br><br>

<?php include('comments.php');?>





	</div>
</body>
</html>