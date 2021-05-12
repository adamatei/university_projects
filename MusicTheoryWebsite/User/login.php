
<?php
require_once('UserManagement.php');

$user = new UserManagement();

$error = "";



if(isset($_POST['login_btn'])) 
{ 
 $username = $_POST['username'];
 
 $password = $_POST['password']; 
  
 if($user->Login($username,$password))
 {
 	if($user->IsAdmin())
 	{
       $user->Redirect('../admin/home.php');
 	}
 	else
 	{
 		$user->Redirect('../member/home.php');
 	}
  
 } 
 else
 {
  $error = "Wrong Details !";
 } 
}

?>

