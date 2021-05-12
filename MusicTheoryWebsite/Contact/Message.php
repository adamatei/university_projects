<?php


class Message
{
    private $name;
    private $comment;
 
    function __construct($n, $c)
    {
      $this->name = $n;
      $this->comment = $c;
    }
  
    public function GetName()
    {
      return $this->name;
    }

    public function GetComment()
    {
      return $this->comment;
    }


    
  }

?>