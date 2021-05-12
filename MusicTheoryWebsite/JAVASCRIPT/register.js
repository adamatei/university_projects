function Check() {
      var valid = true;

      // check username 
         if( document.registerform.username.value == "" ) 
         {
            
            document.getElementById("usernameError").innerHTML = "Username cannot be blank";
            valide = false;
         }
         else
         {
            document.getElementById("usernameError").innerHTML = "";
         }


     // check email
         if( document.registerform.email.value == "" ) 
         {

         document.getElementById("emailError").innerHTML = "Email cannot be blank"; 
         valide = false;

         }

         else 
      {
         if(validateEmail(document.registerform.email.value) == false)
         {

         document.getElementById("emailError").innerHTML = "Invalid email format"; 
          valide = false;

         }
         else
         {
            document.getElementById("emailError").innerHTML = "";
         }
      }
        
        // check password 1
         if( document.registerform.password_1.value == "" ) 
         {
            
            document.getElementById("passwordError1").innerHTML = "Password cannot be blank";
            valide = false;
         }
         else
         {
            document.getElementById("passwordError1").innerHTML = "";
         }


          // check password 2 & pasword matching
         if( document.registerform.password_2.value == "" ) 
         {
            
            document.getElementById("passwordError2").innerHTML = "Password cannot be blank";
            valide = false;
         }
         else
         {
            if(document.registerform.password_1.value != document.registerform.password_2.value)
            {
              document.getElementById("passwordError2").innerHTML = "Passwords do not match"; 
              valide = false;
            }
            else
            {
                document.getElementById("passwordError2").innerHTML = "";
            }
         }
 
         

         return( valide );
      }


    function validateEmail(email)
    {
     return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
    }