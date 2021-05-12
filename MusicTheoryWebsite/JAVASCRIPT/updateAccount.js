function CheckNewMail()
{
	var valide = true;

	if( document.changeMailform.newMail.value == "" ) 
    {

     document.getElementById("newMailError").innerHTML = "Email cannot be blank"; 
     valide = false;

    }

    else 
    {
        if(validateEmail(document.changeMailform.newMail.value) == false)
        {

         document.getElementById("newMailError").innerHTML = "Invalid email format"; 
         valide = false;

        }
        else
        {
            document.getElementById("newMailError").innerHTML = "";
        }
    }


   return( valide );
}


function validateEmail(email)
{
  return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}



function CheckPassword()
{
	var valide = true;

// check current password
         if( document.changePasswordform.currentPassword.value == "" ) 
         {
            
            document.getElementById("currentPasswordError").innerHTML = "Password cannot be blank";
            valide = false;
         }
         else
         {
            document.getElementById("currentPasswordError").innerHTML = "";
         }


// check new password
         if( document.changePasswordform.newPassword.value == "" ) 
         {
            
            document.getElementById("newPasswordError").innerHTML = "Password cannot be blank";
            valide = false;
         }
         else
         {
            document.getElementById("newPasswordError").innerHTML = "";
         }


// check confirm password
         if( document.changePasswordform.confirmPassword.value == "" ) 
         {
            
            document.getElementById("confirmPasswordError").innerHTML = "Password cannot be blank";
            valide = false;
         }
         else
         {
          
            if(document.changePasswordform.newPassword.value != document.changePasswordform.confirmPassword.value)
            {
              document.getElementById("confirmPasswordError").innerHTML = "Passwords do not match"; 
              valide = false;
            }
            else
            {
                document.getElementById("confirmPasswordError").innerHTML = "";
            }
         
         }

return( valide );

}