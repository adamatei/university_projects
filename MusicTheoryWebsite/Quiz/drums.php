<?php
// Start the session
//session_start();
require_once('correctDrums.php');
?>

<!DOCTYPE html>
<html>

<head>
	<link rel = "stylesheet" href = "../css/layout.css">
  <link rel = "stylesheet" href = "../css/gallery.css">
  <link rel = "stylesheet" href = "../css/mediaqueries.css">
  <link rel="stylesheet" type="text/css" href="../css/style.css" />
   <link rel="stylesheet"  href="../css/grade.css" >
  <script type = "text/javascript" src = "../JAVASCRIPT/checkAnswers.js" ></script>

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
  

<!-- Button that returns the home page of the user-->

 <a href='../member/home.php'>
            <button>Back to home page</button>
            </a>


<!--  Quiz  -->

  <div class = "data">
    <div class = "heading"> <h2>test your musical knowledge:</h2> </div>
  </div>

    <form action="drums.php" method="post" id="quiz" name="quizform" method="post" onsubmit = "return(CheckAnswers());">
    
            <ol>
            
                <li>
                
                    <h3>Which picture matches the notation for tom 1?</h3>
                    <br><small id = "Error1"></small>
                    <div>
                        <input type="radio" name="question-1-answers" id="question-1-answers-A" value="A" />
                        <label for="question-1-answers-A"><img src = "../image/chords/drums/ridee.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-1-answers" id="question-1-answers-B" value="B" />
                        <label for="question-1-answers-B"><img src = "../image/chords/drums/tom1e.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-1-answers" id="question-1-answers-C" value="C" />
                        <label for="question-1-answers-C"><img src = "../image/chords/drums/crashe.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-1-answers" id="question-1-answers-D" value="D" />
                        <label for="question-1-answers-D"><img src = "../image/chords/drums/tom3e.jpg"></label>
                    </div>
                
                </li>
                
                <li>
                
                    <h3>Which picture matches the notation for ride?</h3>
                    <br><small id = "Error2"></small>
                    <div>
                        <input type="radio" name="question-2-answers" id="question-2-answers-A" value="A" />
                        <label for="question-2-answers-A"><img src = "../image/chords/drums/rightbasse.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-2-answers" id="question-2-answers-B" value="B" />
                        <label for="question-2-answers-B"><img src = "../image/chords/drums/closedhihate.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-2-answers" id="question-2-answers-C" value="C" />
                        <label for="question-2-answers-C"><img src = "../image/chords/drums/ridee.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-2-answers" id="question-2-answers-D" value="D" />
                        <label for="question-2-answers-D"><img src = "../image/chords/drums/snaree.jpg"></label>
                    </div>
                
                </li>
                
                <li>
                
                    <h3>Which picture matches the notation for open hi-hat?</h3>
                    <br><small id = "Error3"></small>
                    <div>
                        <input type="radio" name="question-3-answers" id="question-3-answers-A" value="A" />
                        <label for="question-3-answers-A"><img src = "../image/chords/drums/openhihate.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-3-answers" id="question-3-answers-B" value="B" />
                        <label for="question-3-answers-B"><img src = "../image/chords/drums/tom5e.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-3-answers" id="question-3-answers-C" value="C" />
                        <label for="question-3-answers-C"><img src = "../image/chords/drums/leftbasse.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-3-answers" id="question-3-answers-D" value="D" />
                        <label for="question-3-answers-D"><img src = "../image/chords/drums/tom1e.jpg"></label>
                    </div>
                
                </li>
                
                <li>
                
                    <h3>Which picture matches the notation for crash?</h3>
                    <br><small id = "Error4"></small>
                    <div>
                        <input type="radio" name="question-4-answers" id="question-4-answers-A" value="A" />
                        <label for="question-4-answers-A"><img src = "../image/chords/drums/ridee.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-4-answers" id="question-4-answers-B" value="B" />
                        <label for="question-4-answers-B"><img src = "../image/chords/drums/tom3e.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-4-answers" id="question-4-answers-C" value="C" />
                        <label for="question-4-answers-C"><img src = "../image/chords/drums/closedhihate.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-4-answers" id="question-4-answers-D" value="D" />
                        <label for="question-4-answers-D"><img src = "../image/chords/drums/crashe.jpg"></label>
                    </div>
                
                </li>
                
                <li>
                
                    <h3>Which picture matches the notation for hi-hat with pedal?</h3>
                    <br><small id = "Error5"></small>
                    <div>
                        <input type="radio" name="question-5-answers" id="question-5-answers-A" value="A" />
                        <label for="question-5-answers-A"><img src = "../image/chords/drums/tom4e.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-5-answers" id="question-5-answers-B" value="B" />
                        <label for="question-5-answers-B"><img src = "../image/chords/drums/hihatpedale.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-5-answers" id="question-5-answers-C" value="C" />
                        <label for="question-5-answers-C"><img src = "../image/chords/drums/leftbasse.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-5-answers" id="question-5-answers-D" value="D" />
                        <label for="question-5-answers-D"><img src = "../image/chords/drums/openhihate.jpg"></label>
                    </div>
                
                </li>
            
            </ol>
            
           
            <!--<input type="submit" value="Submit Quiz" />-->


            <!--<a href='../grade/drums.php'>-->
            <button type="submit" name="submit_btn" class="btn btn-primary" id="butsubmit">Submit Quiz</button>
            <!--</a>-->
    
    </form>

  
</body>

</html>
