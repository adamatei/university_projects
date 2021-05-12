<?php
require_once('Administrator.php');

$admin = new Administrator();

$error = "";



if(isset($_POST['delete_btn'])) 
{ 
   $username = $_POST['username'];
  
   if($admin->CheckUsername($username))
  {
 	$admin->AccountDelete($username);  
  } 
  else
  {
    $error = "Username doesn't exist !";
  } 
}

?>