<?php

require_once('../DATABASE/connection.php');


class Section
{
    private $db;
    private $title;
    private $text;
 
    function __construct($titl, $txt)
    {
       $this->db = new Connection();
       $this->title = $titl;
       $this->text =  $txt;
    }


    public function GetTitle()
    {
    	return $this->title;
    }

    public function GetText()
    {
    	return $this->text;
    }

}

?>