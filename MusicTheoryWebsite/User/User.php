<?php

require_once('../DATABASE/connection.php');
session_start();

class User
{
    private $db;
 
    function __construct()
    {
       $this->db = new Connection();
    }
 
     private $logged_in_user_id;


    public function register($username,$email,$password)
    {
       try
       {
         
        $password1 = md5($password);

        $user_type = "user";
   
        $stmt = $this->db->connect()->prepare("INSERT INTO users (username, email, user_type, password) VALUES (:username, :email,  :user_type , :password)");
                
        $stmt->execute(array(":username" => $username,":email"=> $email, ":user_type" => $user_type, ":password" => $password1)); 
        
        $this->login($username,$password);
          

        $this->registergrade($_SESSION['user_session']['username'], $_SESSION['user_session']['id']);

   
       
       }
       catch(PDOException $e)
       {
           echo $e->getMessage();
       }    
    }



    public function getUserById($id){
            
        $stmt = $this->db->connect()->prepare("SELECT * FROM users WHERE id=:id  LIMIT 1");
        $stmt->execute(array(':id'=>$id));
        $userRow=$stmt->fetch(PDO::FETCH_ASSOC);
     
        return $userRow;
    }

   

    public function login($username,$password)
    {
       try
       {
          $password = md5($password);
          $stmt = $this->db->connect()->prepare("SELECT * FROM users WHERE username=:username  LIMIT 1");
          $stmt->execute(array(':username'=>$username));
          $userRow=$stmt->fetch(PDO::FETCH_ASSOC);
          if($stmt->rowCount() > 0)
          {
             if( $password == $userRow['password'])
             {
                $_SESSION['user_session'] = $userRow;
                $_SESSION['success']  = "You are now logged in" ;
                return true;
             }
             else
             {
                return false;
             }
          }
       }
       catch(PDOException $e)
       {
           echo $e->getMessage();
       }
   }


 
   public function is_loggedin()
   {
      if(isset($_SESSION['user_session']))
      {
         return true;
      }
   }


 
   public function redirect($url)
   {
       header("Location: $url");
   }


 
   public function logout()
   {
        session_destroy();
        unset($_SESSION['user_session']);
        return true;
   }



public function checkusername($username)
{

   $stmt = $this->db->connect()->prepare("SELECT username FROM users WHERE username=:username");
         $stmt->execute(array(':username'=>$username));
         $row=$stmt->fetch(PDO::FETCH_ASSOC);
    
         if(/*$row['username'] == $username*/ $row) {
            $error[] = "Sorry username already taken !";
            return false;
         }
     return true;

        
}

function isAdmin()
{
  if (isset($_SESSION['user_session']) && $_SESSION['user_session']['user_type'] == 'admin' ) {
    return true;
  }else{
    return false;
  }
}




public function registergrade($username, $logged_in_user_id)
    {
    try
       {
                   
         $stmt = $this->db->connect()->prepare("INSERT INTO grades(username,userid,ggrade,pgrade,dgrade,fgrade) VALUES (:username, :logged_in_user_id,0,0,0,0)");
              
                                  
         $stmt->execute(array(":username" => $username,":logged_in_user_id"=> $logged_in_user_id)); 
   
                     
       }
       catch(PDOException $e)
       {
           echo $e->getMessage();
       }    

    }


}

if (isset($_GET['logout'])) {
  session_destroy();
  unset($_SESSION['user_session']);
  header("location: ../PresentationLayer/home.php");
}

?>