function CheckInputs() {
      
         if( document.loginform.username.value == "" ) {
            
            document.getElementById("usernameError").innerHTML = "Username cannot be blank";
                       return false;
         }
         else
         {
             document.getElementById("usernameError").innerHTML = "";
         }
         if( document.loginform.password.value == "" ) {
            document.getElementById("passwordError").innerHTML = "Password cannot be blank"; 
          
            return false;
         }
         else
         {
            document.getElementById("passwordError").innerHTML = "";
         }
        
         document.getElementById("usernameError").innerHTML = "";
         document.getElementById("passwordError").innerHTML = "";
         return( true );
      }