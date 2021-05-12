<?php
// Start the session
//session_start();
require_once('correctPiano.php');
?>

<!DOCTYPE html>
<html>

<head>
	<link rel = "stylesheet" href = "../css/layout.css">
  <link rel = "stylesheet" href = "../css/gallery.css">
  <link rel = "stylesheet" href = "../css/mediaqueries.css">
  <link rel="stylesheet" type="text/css" href="../css/style.css" />
  <script type = "text/javascript" src = "../JAVASCRIPT/checkAnswers.js" ></script>
   <link rel="stylesheet"  href="../css/grade.css" >
  

    <link href="main.css"rel="stylesheet"type="text/css"/>
    <meta name=viewport content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<title>Strike a chord</title>
	<meta name = "viewport" content = "width = device - width, initial - scale = 1.0">
</head>

<body>


  <!--Logo  -->

   <div id = logo>
              <img src = "../image/logo.png" alt = "NOTIFY"> 
   </div> -->
  

<!-- Button that returns the home  of the user-->

 <a href='../member/home.php'>
            <button>Back to home </button>
            </a>


<!--  Quiz  -->

  <div class = "data">
    <div class = "heading"> <h2>test your musical knowledge:</h2> </div>
  </div>

    <form action="piano.php" method="post" id="quiz" name="quizform" method="post" onsubmit = "return(CheckAnswers());">
    
            <ol>
            
                <li>
                
                    <h3>Which picture matches C major?</h3>
                    <br><small id = "Error1"></small>
                    <div>
                        <input type="radio" name="question-1-answers" id="question-1-answers-A" value="A" />
                        <label for="question-1-answers-A"><img src = "../image/quiz/piano/bminor.png"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-1-answers" id="question-1-answers-B" value="B" />
                        <label for="question-1-answers-B"><img src = "../image/quiz/piano/cmajor.png"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-1-answers" id="question-1-answers-C" value="C" />
                        <label for="question-1-answers-C"><img src = "../image/quiz/piano/aminor.png"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-1-answers" id="question-1-answers-D" value="D" />
                        <label for="question-1-answers-D"><img src = "../image/quiz/piano/bbmajor.png"></label>
                    </div>
                
                </li>
                
                <li>
                
                    <h3>Which picture matches E minor?</h3>
                    <br><small id = "Error2"></small>
                    <div>
                        <input type="radio" name="question-2-answers" id="question-2-answers-A" value="A" />
                        <label for="question-2-answers-A"><img src = "../image/quiz/piano/aminor.png"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-2-answers" id="question-2-answers-B" value="B" />
                        <label for="question-2-answers-B"><img src = "../image/quiz/piano/fsharpminor.png"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-2-answers" id="question-2-answers-C" value="C" />
                        <label for="question-2-answers-C"><img src = "../image/quiz/piano/eminor.png"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-2-answers" id="question-2-answers-D" value="D" />
                        <label for="question-2-answers-D"><img src = "../image/quiz/piano/bmajor.png"></label>
                    </div>
                
                </li>
                
                <li>
                
                    <h3>Which picture matches A major?</h3>
                    <br><small id = "Error3"></small>
                    <div>
                        <input type="radio" name="question-3-answers" id="question-3-answers-A" value="A" />
                        <label for="question-3-answers-A"><img src = "../image/quiz/piano/gmajor.png"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-3-answers" id="question-3-answers-B" value="B" />
                        <label for="question-3-answers-B"><img src = "../image/quiz/piano/amajor.png"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-3-answers" id="question-3-answers-C" value="C" />
                        <label for="question-3-answers-C"><img src = "../image/quiz/piano/gmajor.png"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-3-answers" id="question-3-answers-D" value="D" />
                        <label for="question-3-answers-D"><img src = "../image/quiz/piano/fmajor.png"></label>
                    </div>
                
                </li>
                
                <li>
                
                    <h3>Which picture matches Bb major?</h3>
                    <br><small id = "Error4"></small>
                    <div>
                        <input type="radio" name="question-4-answers" id="question-4-answers-A" value="A" />
                        <label for="question-4-answers-A"><img src = "../image/quiz/piano/fsharpminor.png"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-4-answers" id="question-4-answers-B" value="B" />
                        <label for="question-4-answers-B"><img src = "../image/quiz/piano/gmajor.png"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-4-answers" id="question-4-answers-C" value="C" />
                        <label for="question-4-answers-C"><img src = "../image/quiz/piano/emajor.png"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-4-answers" id="question-4-answers-D" value="D" />
                        <label for="question-4-answers-D"><img src = "../image/quiz/piano/bbmajor.png"></label>
                    </div>
                
                </li>
                
                <li>
                
                    <h3>Which picture matches F sharp minor?</h3>
                    <br><small id = "Error5"></small>
                    <div>
                        <input type="radio" name="question-5-answers" id="question-5-answers-A" value="A" />
                        <label for="question-5-answers-A"><img src = "../image/quiz/piano/dmajor.png"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-5-answers" id="question-5-answers-B" value="B" />
                        <label for="question-5-answers-B"><img src = "../image/quiz/piano/fsharpminor.png"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-5-answers" id="question-5-answers-C" value="C" />
                        <label for="question-5-answers-C"><img src = "../image/quiz/piano/cmajor.png"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-5-answers" id="question-5-answers-D" value="D" />
                        <label for="question-5-answers-D"><img src = "../image/quiz/piano/dminor.png"></label>
                    </div>
                
                </li>
            
            </ol>
            
           
            <!--<input type="submit" value="Submit Quiz" />-->


            <!--<a href='../grade/piano.php'>-->
            <button type="submit" name="submit_btn" class="btn btn-primary" id="butsubmit">Submit Quiz</button>
            <!--</a>-->
    
    </form>

  
</body>

</html>
