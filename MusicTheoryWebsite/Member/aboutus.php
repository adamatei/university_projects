<?php
require_once('../LoadingFiles/FileManager.php');
require_once('../LoadingFiles/Section.php');
?>

<!DOCTYPE html>
<html>

<head>
   <link rel = "stylesheet" href = "../css/contact.css">
</head>


<body>

<?php 
      include("../navigation/headerUser.php");
      ?>


<div class="header">
 

    <div class="pic">
      
      <img src="../image/concert2.1.jpg" style = " width: 100% ">
      <div class = "imageContainer"><h1>ABOUT US</h1>

     <p>“Music is the strongest form of magic.” ― Marilyn Manson</p></div>
    </div>

</div>

<br><br><br>

<div class="content">

  

<?php

$manager = new FileManager();
$manager->AddSections();
$content = array();
$content = $manager->GetSections();

foreach ($content as $c) 
{ 
  $title = $c->GetTitle();
  $text = $c->GetText();
  echo "<h1>". $title. "</h1>";
  echo "<p>". $text . "</p>";
}

?>


</div>


<a href='home.php'>
<button>START HERE</button>
</a>




  <CENTER><footer> <?php include ("../navigation/footerUser2.php"); ?> </footer></CENTER>
</body>

</html>
