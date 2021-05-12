function Check()
{
	var valid = true;

      // check first answer 
         if( document.quizform.question-1-answers.value == "" ) 
         {            
           valide = false;
         }
         if( document.quizform.question-2-answers.value == "" ) 
         {            
           valide = false;
         }
         if( document.quizform.question-3-answers.value == "" ) 
         {            
           valide = false;
         }
         if( document.quizform.question-4-answers.value == "" ) 
         {            
           valide = false;
         }
         if( document.quizform.question-5-answers.value == "" ) 
         {            
           valide = false;
         }

         if(valide == false)
         {
         	document.getElementById("Error").innerHTML = "Check all boxes";
         }
         else
         {
         	document.getElementById("Error").innerHTML = "";
         }
         
         return (valide);
}