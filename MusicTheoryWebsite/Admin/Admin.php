<?php

require_once('../DATABASE/connection.php');

class Admin
{
    private $db;
   
 
    function __construct()
    {
       $this->db = new Connection();

    }

    public function DeleteAccount($username)
    {
    	
           $this->DeleteFromUsers($username);
           $this->DeleteFromGrades($username);
           $this->DeleteRequest($username);

       
    }

    public function DeleteFromUsers($username)
    {

        $result = $this->db->connect()->prepare("DELETE FROM users WHERE username= :username");
	    $result->bindParam(':username', $username);
	    $result->execute();
    }


     public function DeleteFromGrades($username)
    {
        $result = $this->db->connect()->prepare("DELETE FROM grades WHERE username= :username");
	    $result->bindParam(':username', $username);
	    $result->execute();
    }

    public function check($username)
   {

         $stmt = $this->db->connect()->prepare("SELECT username FROM users WHERE username=:username");
         $stmt->execute(array(':username'=>$username));
         $row=$stmt->fetch(PDO::FETCH_ASSOC);
    
         if($row) {
            return true;
         }
         
         return false;      
             
    }


    public function UpdateAboutUs($title, $text)
    {
        if($title == "")
        {
        $stmt = $this->db->connect()->prepare("INSERT INTO aboutus (title, text) VALUES ('', :text)");
                
        $stmt->execute(array(":text" => $text)); 
        
        }
        else
        {
        $stmt = $this->db->connect()->prepare("INSERT INTO aboutus (title, text) VALUES (:title, :text)");
                
        $stmt->execute(array(":title" => $title,":text" => $text));
        }
    }

    public function DeleteRequest($username)
    {
        $result = $this->db->connect()->prepare("DELETE FROM deleterequests WHERE username= :username");
        $result->bindParam(':username', $username);
        $result->execute();
    }

    public function UpdateAdditionalMaterials($instrumentid, $text, $url)
    {
        if($url == "")
        {
          $stmt = $this->db->connect()->prepare("INSERT INTO additionalmaterials (instrumentid, text, image) VALUES (:instrumentid, :text, '')");
                
          $stmt->execute(array(":instrumentid" => $instrumentid,":text" => $text)); 
        }
        else
        {
            if($text == "")
            {
              $stmt = $this->db->connect()->prepare("INSERT INTO additionalmaterials (instrumentid, text, image) VALUES (:instrumentid, '', :url)");
                
              $stmt->execute(array(":instrumentid" => $instrumentid,":url" => $url)); 
            }
            else
            {
              $stmt = $this->db->connect()->prepare("INSERT INTO additionalmaterials (instrumentid, text, image) VALUES (:instrumentid, :text, :url)");
                
              $stmt->execute(array(":instrumentid" => $instrumentid,":text" => $text, ":url" => $url)); 
            }
        }
    }
    
}

?>