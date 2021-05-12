<!DOCTYPE html>
<html>

<head>
  <link rel = "stylesheet" href = "../../css/layout.css">
  <link rel = "stylesheet" href = "../../css/gallery.css">
  <link rel = "stylesheet" href = "../../css/mediaqueries.css">
  <title>Strike a chord</title>
  <meta name = "viewport" content = "width = device - width, initial - scale = 1.0">
</head>

<body>
  <header> <img src = "../../image/logo.png" alt = "NOTIFY"> </header>
  <nav> <?php include ("../../navigation/header.php"); ?> </nav>
  

  <div class = "data">
    <div class = "heading"> <h2>notes:</h2> </div>
    <div class = "notes"> <?php include ("../../navigation/noteslist/flute.php"); ?> </div>
    <div class = "chord1"> <img src = "../../image/chords/flute/g.jpg" alt = "low g"> </div>
    <div class = "chord2"> <img src = "../../image/chords/flute/ghigh.jpg" alt = "high g"> </div>
  </div>
  
  <footer> <?php include ("../../navigation/footer.php"); ?> </footer>
</body>

</html>
