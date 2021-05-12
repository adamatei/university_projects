function CheckAnswer1() {
      
          var ans1 = document.getElementById('question-1-answers-A').checked;
          var ans2 = document.getElementById('question-1-answers-B').checked;
          var ans3 = document.getElementById('question-1-answers-C').checked;
          var ans4 = document.getElementById('question-1-answers-D').checked;
          
         
           if(ans1 == false && ans2 == false && ans3 == false && ans4 == false)
           {
            document.getElementById("Error1").innerHTML ='You need to check one answer ! !';
            return false;
           }
           else
           {
            document.getElementById("Error1").innerHTML = "";

           }
        
         
         document.getElementById("Error1").innerHTML = "";
         
         return( true );
      }


function CheckAnswer2() {
      
          var ans1 = document.getElementById('question-2-answers-A').checked;
          var ans2 = document.getElementById('question-2-answers-B').checked;
          var ans3 = document.getElementById('question-2-answers-C').checked;
          var ans4 = document.getElementById('question-2-answers-D').checked;
          
         
           if(ans1 == false && ans2 == false && ans3 == false && ans4 == false)
           {
            document.getElementById("Error2").innerHTML ='You need to check one answer ! !';
            return false;
           }
           else
           {
            document.getElementById("Error2").innerHTML = "";

           }
        
         
         document.getElementById("Error2").innerHTML = "";
         
         return( true );
      }


function CheckAnswer3() {
      
          var ans1 = document.getElementById('question-3-answers-A').checked;
          var ans2 = document.getElementById('question-3-answers-B').checked;
          var ans3 = document.getElementById('question-3-answers-C').checked;
          var ans4 = document.getElementById('question-3-answers-D').checked;
          
         
           if(ans1 == false && ans2 == false && ans3 == false && ans4 == false)
           {
            document.getElementById("Error3").innerHTML ='You need to check one answer ! !';
            return false;
           }
           else
           {
            document.getElementById("Error3").innerHTML = "";

           }
        
         
         document.getElementById("Error3").innerHTML = "";
         
         return( true );
      }


function CheckAnswer4() {
      
          var ans1 = document.getElementById('question-4-answers-A').checked;
          var ans2 = document.getElementById('question-4-answers-B').checked;
          var ans3 = document.getElementById('question-4-answers-C').checked;
          var ans4 = document.getElementById('question-4-answers-D').checked;
          
         
           if(ans1 == false && ans2 == false && ans3 == false && ans4 == false)
           {
            document.getElementById("Error4").innerHTML ='You need to check one answer ! !';
            return false;
           }
           else
           {
            document.getElementById("Error4").innerHTML = "";

           }
        
         
         document.getElementById("Error4").innerHTML = "";
         
         return( true );
      }


function CheckAnswer5() {
      
          var ans1 = document.getElementById('question-5-answers-A').checked;
          var ans2 = document.getElementById('question-5-answers-B').checked;
          var ans3 = document.getElementById('question-5-answers-C').checked;
          var ans4 = document.getElementById('question-5-answers-D').checked;

          
         
           if(ans1 == false && ans2 == false && ans3 == false && ans4 == false)
           {
            document.getElementById("Error5").innerHTML ='You need to check one answer ! !';
            return false;
           }
           else
           {
            document.getElementById("Error5").innerHTML = "";

           }
        
         
         document.getElementById("Error5").innerHTML = "";
         
         return( true );
      }


function CheckAnswers(){
  
  if(CheckAnswer1() == true && CheckAnswer2() == true && CheckAnswer3() == true && CheckAnswer4() == true && CheckAnswer5() == true)
  {
    return true;
  }
  else
  {
     return false;
  }
}