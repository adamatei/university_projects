<?php

require_once('../DATABASE/connection.php');


class AboutUsData
{
    private $db;
 
    function __construct()
    {
       $this->db = new Connection();
    }

    
    public function getSectionById($id){
            
        $stmt = $this->db->connect()->prepare("SELECT * FROM aboutus WHERE id=:id  LIMIT 1");
        $stmt->execute(array(':id'=>$id));
        $sectionRow=$stmt->fetch(PDO::FETCH_ASSOC);
     
        return $sectionRow;
    }


    public function CountSections()
    {
    	$stmt = $this->db->connect()->prepare("SELECT * FROM aboutus ");
        $stmt->execute();
        $sectionRow=$stmt->fetch(PDO::FETCH_ASSOC);
        return $stmt->rowCount();
    }



}

?>