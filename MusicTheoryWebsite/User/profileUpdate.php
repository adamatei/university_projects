<?php

require_once("../User/User.php");
require_once("../User/updateAccount.php");
require_once("../User/userCheck.php");

$user = new User(); 

$account = new updateAccount();

if(isset($_POST['changepass_btn']))
{

 $newPassword = $_POST['newPassword'];
 
 $currentPassword = $_POST['currentPassword'];

 $confirmPassword = $_POST['confirmPassword'];

 $account->change($newPassword, $currentPassword, $confirmPassword);

}

if(isset($_POST['changemail_btn']))
{

 $newMail = $_POST['newMail'];

 $account->changeMail($newMail);

}



?>

