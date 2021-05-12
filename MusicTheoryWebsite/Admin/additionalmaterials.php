<?php include('update.php');?>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../css/styleHomePageUser.css">
	<link rel="stylesheet" type="text/css" href="../css/adminHome.css">
    <script type = "text/javascript" src = "../JAVASCRIPT/additionalMaterial.js" ></script>
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
    

<!--Checkboxes for instruments-->
<div>
	<h2>Select instrument: </h2>
	<br>
	 <small id = "instrumentError"></small>
	<br><br>
<div id = "radioButtons">
  <div>
    <input type="radio" name="instrument" id="guitar" value="1" />
    <label for="guitar">Guitar</label>
  </div> <br>

  <div>
    <input type="radio" name="instrument" id="piano" value="2" />
    <label for="piano">Piano</label>
  </div> <br>

  <div>
    <input type="radio" name="instrument" id="drums" value="3" />
    <label for="drums">Drums</label>
  </div> <br>

  <div>
    <input type="radio" name="instrument" id="flute" value="4" />
    <label for="flute">Flute</label>
  </div><br>
    
</div>
</div>

<!--Error message from javascript validation-->
    <small id = "textError"></small> <br>

<!--Form inputs-->
    <div class="input-group"><br>
   <h2>Content for the new section: </h2><br>
 
  <textarea class="form-control" id="text" placeholder="Text" name="text" rows="10" cols="44"></textarea> 

    </div> <br>

    <div class="input-group">
    	<h2>URL of the new image: </h2><br>
      <input type="text" class="form-control" id="title" placeholder="URL" name="url">
    </div>
    <br>
   

    <button type="submit" name="add_btn" class="btn btn-primary" value="Update" id="butsave">Update</button>
  </form>
</div></center>				
		

	</div>
</body>
</html>