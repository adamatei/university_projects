<?php

require_once('../DATABASE/connection.php');

class Comment
{
    private $db;
 
    function __construct()
    {
       $this->db = new Connection();
    }
 


    public function send($name,$comment)
    {
       try
       {
        
   
        $stmt = $this->db->connect()->prepare("INSERT INTO comments( name, comment) 
  VALUES (:name,:comment)");
                
        $stmt->execute(array(":name" => $name,":comment"=> $comment)); 
        
       
       }
       catch(PDOException $e)
       {
           echo $e->getMessage();
       }    
    }

    public function GetComments()
    {
       $query = "SELECT * FROM comments";
       $d = $this->db->connect()->query($query);
        return $d;

    }

    public function delete($username,$userid)
    {
       try
       {
        
   
        $stmt = $this->db->connect()->prepare("INSERT INTO deleterequests(username, userid) 
        VALUES (:username,:userid)");
                
        $stmt->execute(array(":username" => $username,":userid"=> $userid)); 
        
       
       }
       catch(PDOException $e)
       {
           echo $e->getMessage();
       }    
    }

    public function GetRequests()
    {
      $db = new Connection();
      $query = "SELECT * FROM deleterequests";
      $d = $db->connect()->query($query);
      return $d;
    }

    public function RequestsCount()
    {
      $db = new Connection();
      $query = "SELECT * FROM deleterequests";
      $d = $db->connect()->query($query);
      return $d->rowCount();
    }
  }

?>