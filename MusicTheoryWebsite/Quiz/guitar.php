<?php
// Start the session
//session_start();
require_once('correct.php');
?>

<!DOCTYPE html>
<html>

<head>
	<link rel = "stylesheet" href = "../css/layout.css">
  <link rel = "stylesheet" href = "../css/gallery.css">
  <link rel = "stylesheet" href = "../css/mediaqueries.css">
  <link rel="stylesheet" type="text/css" href="../css/style.css" />
 <!-- <link rel="stylesheet" type="text/css" href="../css/styleHomePageUser.css" >-->
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

    <form action="guitar.php" method="post" id="quiz" name="quizform" method="post" onsubmit = "return(CheckAnswers());">
    
            <ol>
            
                <li>
                
                    <h3>Which picture matches C9?</h3>
                    <br><small id = "Error1"></small>
                    <div>
                        <input type="radio" name="question-1-answers" id="question-1-answers-A" value="A" />
                        <label for="question-1-answers-A"><img src = "../image/quiz/guitar/b9.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-1-answers" id="question-1-answers-B" value="B" />
                        <label for="question-1-answers-B"><img src = "../image/quiz/guitar/c9.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-1-answers" id="question-1-answers-C" value="C" />
                        <label for="question-1-answers-C"><img src = "../image/quiz/guitar/e9.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-1-answers" id="question-1-answers-D" value="D" />
                        <label for="question-1-answers-D"><img src = "../image/quiz/guitar/f7.jpg"></label>
                    </div>
                
                </li>
                
                <li>
                
                    <h3>Which picture matches E?</h3>
                    <br><small id = "Error2"></small>
                    <div>
                        <input type="radio" name="question-2-answers" id="question-2-answers-A" value="A" />
                        <label for="question-2-answers-A"><img src = "../image/quiz/guitar/c.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-2-answers" id="question-2-answers-B" value="B" />
                        <label for="question-2-answers-B"><img src = "../image/quiz/guitar/d.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-2-answers" id="question-2-answers-C" value="C" />
                        <label for="question-2-answers-C"><img src = "../image/quiz/guitar/e.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-2-answers" id="question-2-answers-D" value="D" />
                        <label for="question-2-answers-D"><img src = "../image/quiz/guitar/am.jpg"></label>
                    </div>
                
                </li>
                
                <li>
                
                    <h3>Which picture matches Fm?</h3>
                    <br><small id = "Error3"></small>
                    <div>
                        <input type="radio" name="question-3-answers" id="question-3-answers-A" value="A" />
                        <label for="question-3-answers-A"><img src = "../image/quiz/guitar/fm.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-3-answers" id="question-3-answers-B" value="B" />
                        <label for="question-3-answers-B"><img src = "../image/quiz/guitar/fm2.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-3-answers" id="question-3-answers-C" value="C" />
                        <label for="question-3-answers-C"><img src = "../image/quiz/guitar/gm.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-3-answers" id="question-3-answers-D" value="D" />
                        <label for="question-3-answers-D"><img src = "../image/quiz/guitar/gm2.jpg"></label>
                    </div>
                
                </li>
                
                <li>
                
                    <h3>Which picture matches Am7?</h3>
                    <br><small id = "Error4"></small>
                    <div>
                        <input type="radio" name="question-4-answers" id="question-4-answers-A" value="A" />
                        <label for="question-4-answers-A"><img src = "../image/quiz/guitar/dm.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-4-answers" id="question-4-answers-B" value="B" />
                        <label for="question-4-answers-B"><img src = "../image/quiz/guitar/e7.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-4-answers" id="question-4-answers-C" value="C" />
                        <label for="question-4-answers-C"><img src = "../image/quiz/guitar/g.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-4-answers" id="question-4-answers-D" value="D" />
                        <label for="question-4-answers-D"><img src = "../image/quiz/guitar/am7.jpg"></label>
                    </div>
                
                </li>
                
                <li>
                
                    <h3>Which picture matches B7?</h3>
                    <br><small id = "Error5"></small>
                    <div>
                        <input type="radio" name="question-5-answers" id="question-5-answers-A" value="A" />
                        <label for="question-5-answers-A"><img src = "../image/quiz/guitar/a.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-5-answers" id="question-5-answers-B" value="B" />
                        <label for="question-5-answers-B"><img src = "../image/quiz/guitar/b7.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-5-answers" id="question-5-answers-C" value="C" />
                        <label for="question-5-answers-C"><img src = "../image/quiz/guitar/d7.jpg"></label>
                    </div>
                    
                    <div>
                        <input type="radio" name="question-5-answers" id="question-5-answers-D" value="D" />
                        <label for="question-5-answers-D"><img src = "../image/quiz/guitar/e9.jpg"></label>
                    </div>
                
                </li>
            
            </ol>
            
           
            <!--<input type="submit" value="Submit Quiz" />-->


            <!--<a href='../grade/guitar.php'>-->
            <button type="submit" name="submit_btn" class="btn btn-primary" id="butsubmit">Submit Quiz</button>
            <!--</a>-->
    
    </form>

  
</body>

</html>
