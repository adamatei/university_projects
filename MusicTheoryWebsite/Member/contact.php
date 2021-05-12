<?php
// Start the session
session_start();

?>
<!DOCTYPE html>
<html>

<head>

  <link rel = "stylesheet" href = "../css/contact.css">
  <link rel = "stylesheet" href = "../css/mediaquery/contact.css">
  <link rel="stylesheet" type="text/css" href="../css/styleLog.css"> 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
</head>


<body>

<?php 
      include("../navigation/headerUser.php");
      ?>

<div class="header">


<h1>CONTACT US</h1>
       <p>Swing by for a cup of coffee, or leave us a message: </p></div>
      
</div>

<br><br><br>




<div style="margin: auto;width: 60%;" class= "form">
  <div class="alert alert-success alert-dismissible" id="success" style="display:none;">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
  </div>
  <form id="fupForm" name="form1" method="post">
    <div class="input-group">

  <textarea class="form-control" id="comment" placeholder="Comment" name="comment" rows="10" cols="44"></textarea>

    </div>
    <input type="button" name="save" class="btn btn-primary" value="Notify us!" id="butsave">
  </form>
</div>

<script>
$(document).ready(function() {
  $('#butsave').on('click', function() {
    var name = "<?php  echo $_SESSION['user_session']['username']; ?>";
    var comment = $('#comment').val().trim();
    if(comment!="" ){
      $.ajax({
        url: "../Contact/send.php",
        type: "POST",
        data: {
          name: name,
          comment: comment       
        },
        cache: false,

        success: function(dataResult){
             alert("Comment submitted successfully !"); 
        }
      });
    }
    else{
      alert('Invisible comments will be quite hard to read! Say something!');
    }
  });
});
</script>







  <CENTER><footer> <?php include ("../navigation/footerUser2.php"); ?> </footer></CENTER>
</body>

</html>
