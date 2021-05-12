<?php

require_once('../DATABASE/connection.php');


class AdditionalMaterialsData
{
    private $db;
 
    function __construct()
    {
       $this->db = new Connection();
    }

    
    public function getMaterialById($id){
            
        $stmt = $this->db->connect()->prepare("SELECT * FROM additionalmaterials WHERE id=:id");
        $stmt->execute(array(':id'=>$id));
        $materialRow=$stmt->fetch(PDO::FETCH_ASSOC);
     
        return $materialRow;
    }


    public function CountMaterials()
    {
    	$stmt = $this->db->connect()->prepare("SELECT * FROM additionalmaterials");
        $stmt->execute();
        $materialRow=$stmt->fetch(PDO::FETCH_ASSOC);
        return $stmt->rowCount();
    }



}

?>