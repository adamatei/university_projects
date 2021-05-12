<?php

require_once('../DATABASE/connection.php');


class Material
{
    private $db;
    private $instrumentid = 0;
    private $text;
    private $url;
 
    function __construct($txt, $u)
    {
       $this->db = new Connection();
       $this->url = $u;
       $this->text =  $txt;
    }


    public function GetUrl()
    {
    	return $this->url;
    }

    public function GetText()
    {
    	return $this->text;
    }

    public function GetInstrumentId()
    {
        return $this->instrumentid;
    }

    public function SetInstrumentId($id)
    {
        $this->instrumentid = $id;
    }

}

?>