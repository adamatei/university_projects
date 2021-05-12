<?php
require_once('../LoadingFiles/FileManager.php');
require_once('../LoadingFiles/Material.php');
?>


<!DOCTYPE html>

<html>

  <head>
<link rel = "stylesheet" href = "../css/userpage.css">
<link rel="stylesheet" type="text/css" href="../css/styleHomePageUser.css" >
<link rel = "stylesheet" href = "../css/extramaterials.css">
<link rel = "stylesheet" href = "../css/mediaquery/extramaterials.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  </head>

    <body>


 
 <!-- User navigation bar-->
 
<?php 
      include("../navigation/headerUser.php");
      ?>


<!-- Content for the materials -->

<br>
<br> 

<!-- Galary image-->
<div class = "data">

<div class="gallery" id = "guitar">
  
  <a target="_self" href="#guitartab">
    <img src="../image/guitar2.jpg" alt="Guitar" width="600" height="400">
  </a>
  <div class="desc">Go to guitar section! </div>
</div>



<div class="gallery"id = "piano">
  <a target="_self" href="#pianotab">
    <img src="../image/pianokeycopy2.jpg" alt="Piano" width="600" height="400">
  </a>
  <div class="desc">Go to piano section! </div>
</div>



<div class="gallery"id = "drums">
  <a target="_self" href="#drumstab">
    <img src="../image/drums2.jpg" alt="Drums" width="600" height="400">
  </a>
  <div class="desc">Go to drums section! </div>
</div>


<div class="gallery" id = "flute">
  <a target="_self" href="#flutetab">
    <img src="../image/flute.jpg" alt="Flute" width="600" height="400">
  </a>
  <div class="desc">Go to flute section! </div>
</div>


<br> <br> <br> <br>


<div class = "notes">
<!-- The notes from the left side of the page-->

<hr width=”200″ align=”left”>
<br>
<hr width=”200″ align=”left”>
<br>
<i class='fab fa-itunes-note' style='font-size:48px;color:red'></i>
<hr width=”200″ align=”left”>
<br>
<hr width=”200″ align=”left”>
<br>
<hr width=”200″ align=”left”>

<i class="material-icons">music_note</i>
<i class="material-icons" style="font-size:36px">music_note</i>
<i class="material-icons" style="font-size:48px;color:red">music_note</i>
<i class="material-icons" style="font-size:56px;color:red">music_note</i>
<i class="material-icons" style="font-size:65px;color:red">music_note</i>

<br> <br> <br>
</div>
</div>

<!-- CONTENT FOR EXTRA MATERIALS -->

<div class = "content">


  <!-- Guitar content -->


<div class="intro-guitar">
 <h2 id= "guitartab"> GUITAR </h2>
 <br>
    <div class = "subtitle"><p><i>"The guitar is a miniature orchestra in itself."</i></p>
    <p>     -Ludwig van Beethoven</p>

    </div>
    
    <br>
 


<?php

$manager = new FileManager();
$manager->AddGuitarMaterials(1);
$content = array();
$content = $manager->GetGuitarMaterials();

foreach ($content as $c) 
{ 
  
  $text = $c->GetText();
  $url = $c->GetUrl();
  
  echo "<p>". $text . "</p>";
  echo "<br> <br> <br> <br> <br> <br> <br>";
  echo "<img src='". $url. "''>";
  echo "<br><br><br><br><br>";
}

?>

<br> <br>


<!-- Piano content -->


<div class="intro-piano">
 <h2 id= "pianotab"> PIANO </h2>
 <br>
    <div class = "subtitle"><p><i>"The piano ain't got no wrong notes."</i></p>
    <p>     -Thelonious Monk</p>

    </div>
    
    <br>  


<?php

$manager = new FileManager();
$manager->AddPianoMaterials(2);
$content = array();
$content = $manager->GetPianoMaterials();

foreach ($content as $c) 
{ 
  
  $text = $c->GetText();
  $url = $c->GetUrl();
  
  echo "<p>". $text . "</p>";
  echo "<br> <br> <br> <br> <br> <br> <br>";
  echo "<img src='". $url. "''>";
  echo "<br><br><br><br><br>";
}

?>

<br> <br>


  <!-- Drums content-->


<div class="intro-drums">
 <h2 id= "drumstab"> DRUMS </h2>
 <br>
    <div class = "subtitle"><p><i>"March to the beat of your own drummer."</i></p>
    <p>     -Henry David Thoreau</p>

    </div>
    
    <br>  


<?php

$manager = new FileManager();
$manager->AddDrumsMaterials(3);
$content = array();
$content = $manager->GetDrumsMaterials();

foreach ($content as $c) 
{ 
  
  $text = $c->GetText();
  $url = $c->GetUrl();
  
  echo "<p>". $text . "</p>";
  echo "<br> <br> <br> <br> <br> <br> <br>";
  echo "<img src='". $url. "''>";
  echo "<br><br><br><br><br>";
}

?>

<br> <br>


    <!--Flute content-->


<div class="intro-flute">
 <h2 id= "flutetab"> FLUTE </h2>
 <br>
    <div class = "subtitle"><p><i>"Play the flute of felicity! You, yourself, are the melody."</i></p>
    <p>     -Rumi</p>

    </div>
    
    <br>  

<?php

$manager = new FileManager();
$manager->AddFluteMaterials(4);
$content = array();
$content = $manager->GetFluteMaterials();

foreach ($content as $c) 
{ 
  
  $text = $c->GetText();
  $url = $c->GetUrl();
  
  echo "<p>". $text . "</p>";
  echo "<br> <br> <br> <br> <br> <br> <br>";
  echo "<img src='". $url. "''>";
  echo "<br><br><br><br><br>";
}

?>





 </div>

 

  <footer> <?php include ("../navigation/footer2.php"); ?> </footer>
  
 </body>
	
</html>