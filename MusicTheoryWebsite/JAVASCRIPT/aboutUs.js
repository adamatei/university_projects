function CheckInputs() {
      
         if( document.updateform.text.value == "" ) {
            
            document.getElementById("textError").innerHTML = "Text cannot be blank";
            return false;
         }
         else
         {
             document.getElementById("textError").innerHTML = "";
         }
        
         document.getElementById("textError").innerHTML = "";
         
         return( true );
      }