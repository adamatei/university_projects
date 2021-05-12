
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="../css/progressbar.css">
  <link rel="stylesheet" type="text/css" href="../css/styleHomePageUser.css">
  <link rel="stylesheet" type="text/css" href="../css/mediaquery/memberHome.css">
  <link rel="stylesheet" type="text/css" href="../css/changePassword.css">
  <script type = "text/javascript" src = "../JAVASCRIPT/updateAccount.js" ></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<body>
<?php include '../User/profileUpdate.php';?>


  
  <div class="content">

      <div id = navbarad>
       <nav><?php 
      include("../navigation/headerUser.php");
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


<?php if (isset($_SESSION['update'])) : ?>
      <div class="error success" >
        <h3>
          <?php 
            echo $_SESSION['update']; 
            unset($_SESSION['update']);
          ?>
        </h3>
      </div>
    <?php endif ?>



		<!-- logged in user information -->

		<div class="profile_info">
			<img src="../../images/user_profile.png"  >

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


                      <br><br><br><br>



		  <!------------------ Progress bar ------------------------>


                      <div class = "progress" id = "progress">

                                     <br><br>

                                   <h1> Progress</h1>
                                  

                                     <br><br>
  
                                        <h2>  Here's your scoreboard</h2> 
                                       
                                     <br><br>

                                     <!-- Guitar progress bar-->

                                     <h4> Mastering guitar:</h4> <br>

                                              <?php require_once('Member.php'); 

                                                  $pd = new Member();

                                              
                                                   $gprogress = $pd->GetGuitarProgress();
                                                                   
                                                   ?> 

                                            

                                         <progress value = "<?php echo $gprogress; ?>" max="100"></progress> 

                                         <br><br><br>


                                     <!-- Piano progress bar-->

                                         <h4> Mastering piano:</h4> <br>

                                         <?php
                                                     
                                              
                                                   $pprogress = $pd->GetPianoProgress(); 
                                                 
                                                   ?>



                                         <progress value = "<?php  echo $pprogress; ?>" max="100"> </progress> 

                                         <br><br><br>


                                         <!-- Drums progress bar-->

                                     <h4> Mastering drums:</h4> <br>

                                                 <?php
                                                     
                                              
                                                   $dprogress = $pd->GetDrumsProgress();
 
                                                   ?>


                                         <progress value = "<?php echo $dprogress; ?>" max="100"></progress> 

                                         <br><br><br>


                                         <!-- Drums progress bar-->

                                     <h4> Mastering flute:</h4> <br>

                                                 <?php
                                                     
                                                   $dprogress = $pd->GetFluteProgress();
                                                   ?>


                                         <progress value = "<?php echo $dprogress; ?>" max="100"></progress> 

                                         <br><br><br>


                                         <br><br><br>
                                   
                                   
                                                                           
                      </div>     <!---------------------------Changing username----------------------------------->
      
      <br><br><br><br>
    <div class = "data">
    
    <br><br>
    <div id="changemail" class ="center" >
    <form method="post" action="home.php" align="center" class ="formStyle" name = "changeMailform" onsubmit="return(CheckNewMail());">
        <h3>New Email:</h3>
    <input type="mail" name="newMail"><span id="newMail" class="required"></span> <small id = "newMailError"> </small>
     <br><br>
     <button type="submit" class="btn" name="changemail_btn">Change email</button>
    </form>
  </div>
  <br><br>
  


    <!---------------------------Changing password----------------------------------->
      
      <br><br><br><br>
    
    <br><br>
    <div id="changepassword" class ="center" >
    <form method="post" action="home.php" align="center" class ="formStyle" name = "changePasswordform" onsubmit="return(CheckPassword());">
        <h3>Current Password:</h3>
    <input type="password" name="currentPassword"><span id="currentPassword" class="required"></span><br> <small id = "currentPasswordError"> </small>
     <br><br>
        <h3>New Password:</h3>
    <input type="password" name="newPassword"required="required"><span id="newPassword" class="required"></span><br> <small id = "newPasswordError"> </small>
     <br><br>
        <h3>Confirm Password:</h3>
    <input type="password" name="confirmPassword"required="required"><span id="confirmPassword" class="required"></span> <br><small id = "confirmPasswordError"> </small>
    <br><br>
     <button type="submit" class="btn" name="changepass_btn">Change password</button>
    </form>
  </div>
  <br><br>
  
</div>
<hr>
     


     <br><br><br><br><br>





  
<div style="margin: auto;width: 60%;">
  <div class="alert alert-success alert-dismissible" id="success" style="display:none;">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
  </div>
  <form id="fupForm" name="form1" method="post">
    <div class="input-group">

    </div>
    <input type="button" name="save" class="btn btn-primary" value="Delete account" id="butsave">
  </form>
</div>

<script>
$(document).ready(function() {
  $('#butsave').on('click', function() {
    if(confirm("Are you sure you want to delete your account?"))
     {
      var username = "<?php echo $_SESSION['user_session']['username']; ?>";
    var id = "<?php  echo $_SESSION['user_session']['id']; ?>";
    $.ajax({
        url: "../Contact/request.php",
        type: "POST",
        data: {
          username: username,
          id: id       
        },
        cache: false,

        success: function(dataResult){
             alert("Account will be deleted !"); 
        }
      });
     }
  });
});
</script>
</div>


     <br><br><br><br><br>
  <footer> <?php include ("../navigation/footer2.php"); ?> </footer>
</body>
</html>