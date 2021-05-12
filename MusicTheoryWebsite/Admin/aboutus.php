<?php include('update.php');?>

<!DOCTYPE html>
<html>
<head> 
	<link rel="stylesheet" type="text/css" href="../css/styleHomePageUser.css">
	<link rel="stylesheet" type="text/css" href="../css/adminHome.css">
	<script type = "text/javascript" src = "../JAVASCRIPT/aboutUs.js" ></script>

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

 <br><br><br><br><br>



<!-- Form for adding new information to the About us page-->

<center><div id = "form">
  <div class="alert alert-success alert-dismissible" id="success" style="display:none;">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
  </div>
  <form id="updateform" name="updateform" method="post" onsubmit = "return(CheckInputs());">
    <div class="input-group">
    	<h2>Title for the new section: </h2><br>
      <input type="text" class="form-control" id="title" placeholder="Title" name="title">
    </div>
    <br>
    <div class="input-group"><br>
   <h2>Content for the new section: </h2>
  <small id = "textError"></small> <br>
  <textarea class="form-control" id="text" placeholder="Text" name="text" rows="10" cols="44"></textarea> 

    </div> <br>
    <button type="submit" name="update_btn" class="btn btn-primary" value="Update" id="butsave">Update</button>
  </form>
</div></center>		
		
		

	</div>
</body>
</html>