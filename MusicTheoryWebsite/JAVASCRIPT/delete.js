function CheckInputs() {
      
         if( document.deleteform.username.value == "" ) {
            
            document.getElementById("usernameError").innerHTML = "Username cannot be blank";
                       return false;
         }
         else
         {
             document.getElementById("usernameError").innerHTML = "";
         }
                
        
         return( true );
      }