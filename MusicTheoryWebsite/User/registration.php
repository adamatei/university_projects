<?php
 require_once('UserManagement.php');

 $user = new UserManagement();


$error = array();

if(isset($_POST['register_btn']))
{
   $username = trim($_POST['username']);
   $email = trim($_POST['email']);
   $password_1 = trim($_POST['password_1']);
   $password_2 = trim($_POST['password_2']); 
 
     if($user->Checkusername($username) != false)
     {
     	if($password_1 == $password_2)
     	{
     	   $user->Register($username,$email,$password_1); 
            
            $user->Redirect('../member/home.php');
            
      }
      }
     else
     {
     	$error[] = "Username already taken ! ";
     }
    
}

?>


