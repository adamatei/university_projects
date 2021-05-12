 


<!DOCTYPE html>
<html>

<head>
	<link rel = "stylesheet" href = "../css/layout.css">
  <link rel = "stylesheet" href = "../css/gallery.css">
  <link rel = "stylesheet" href = "../css/mediaqueries.css">
  <link rel="stylesheet" type="text/css" href="../css/style.css" />

<link rel = "stylesheet" href = "../css/grade.css">


    <link href="main.css"rel="stylesheet"type="text/css"/>
    <meta name=viewport content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<title>Strike a chord</title>
	<meta name = "viewport" content = "width = device - width, initial - scale = 1.0">
</head>

<body>
  <header> <img src = "../image/logo.png" alt = "NOTIFY"> </a> </header>
 

<a href='../member/home.php'>
  <button><- Home page</button>
</a>


	<div id="page-wrap">

		<h1>Here's your score!</h1>
		
        <?php
            
           require_once('../Member/Member.php'); 
            $pd = new Member();             
            
            $totalCorrect = $pd->GetFlutePoints();

              
          echo "<div id='results'>$totalCorrect / 5 correct</div>"; 

            
        ?>
	
	</div>

</body>

</html>