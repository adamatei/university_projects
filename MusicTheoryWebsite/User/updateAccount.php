<?php

require_once('../DATABASE/connection.php');


class updateAccount

{
    private $user;

    private $db;
 
    function __construct()
    {
       $this->db = new connection();
       
       if($_SESSION['user_session'] != null)
       {
           $this->user = $_SESSION['user_session'];
       }

    }


    public function checkcurrent($currentPassword)
    {
    	$currentPassword1 = md5($currentPassword); 

    	if($_SESSION['user_session']['password'] == $currentPassword1)
    	{
    		return true;
    	}
    	return false;
    }


    public function checknew($newPassword, $confirmPassword)
    {
    	if($newPassword == $confirmPassword)
    	{
    		return true;
    	}
    	return false;
    }


    public function change($newPassword, $currentPassword, $confirmPassword)
    {
    	if($this->checknew($newPassword, $confirmPassword) == false)
    	{
            $_SESSION['update'] = "The two passwords are not the same !";
    	}
    	else 
    	{
    		if( ($this->checkcurrent($currentPassword) == false))
    		{
                 $_SESSION['update'] = "The current password is not correct !";
    		}
    		else
    		{
               $this->update($newPassword);

               $_SESSION['update'] = "Password successfully changed!";

               $_SESSION['user_session']['password'] =  md5($newPassword);
    		}
    	}
   
    }




     function update ($newPassword)
                {
                	$newPassword = md5($newPassword);
                
                    $id = $_SESSION['user_session']['id'];

                    $newPassword = $newPassword;

                    $query = "UPDATE users SET `password`=:newPassword WHERE `id`=:id ";

                    $pdoResult = $this->db->connect()->prepare($query);

                    $pdoExec = $pdoResult -> execute(array(":newPassword" => $newPassword,":id"=> $id));

                }




    public function changeMail($newMail)
    {
        
               $this->updateMail($newMail);

               $_SESSION['update'] = "Email successfully changed!";

               $_SESSION['user_session']['email'] = $newMail;
   
    }




     function updateMail ($newMail)
                {
                
                    $id = $_SESSION['user_session']['id'];

                    $query = "UPDATE users SET `email`=:newMail WHERE `id`=:id ";

                    $pdoResult = $this->db->connect()->prepare($query);

                    $pdoExec = $pdoResult -> execute(array(":newMail" => $newMail,":id"=> $id));

                }

}