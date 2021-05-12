<?php 

require_once("../User/UserManagement.php");
require_once("../User/updateAccount.php");

$user = new UserManagement(); 

if(!$user->Isloggedin())
{

 $user->Logout();
 $user->Redirect('../home.php');

}


?>
