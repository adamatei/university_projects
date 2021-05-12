<?php

require_once('updateAccount.php');
require_once('User.php');

class UserManagement
{
   private $u;
   private $a;
 
    function __construct()
    {
       $this->u = new User(); 
    }

   public function Register($username,$email,$password)
   {
   	$this->u->register($username,$email,$password);
   }

   public function Login($username,$password)
   {
   	return $this->u->login($username,$password);
   }

   public function Isloggedin()
   {
   	return $this->u->is_loggedin();
   }

   public function Redirect($url)
   {
   	$this->u->redirect($url);
   }

   public function Logout()
   {
   	return $this->u->logout();
   }

   public function Checkusername($username)
   {
   	return $this->u->checkusername($username);
   }

   public function IsAdmin()
   {
   	return $this->u->isAdmin();
   }

   public function RegisterGrade($username, $logged_in_user_id)
   {
   	$this->u->registergrade($username, $logged_in_user_id);
   }

   public function Change($newPassword, $currentPassword, $confirmPassword)
   {
    $this->a = new updateAccount();
   	$this->a->change($newPassword, $currentPassword, $confirmPassword);
   }
    

}

if (isset($_GET['logout'])) {
  session_destroy();
  unset($_SESSION['user_session']);
  header("location: ../PresentationLayer/home.php");
}

?>