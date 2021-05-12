<?php

require_once('../DATABASE/connection.php');
require_once('Comment.php');
require_once('Message.php');

class CommentManager
{
   private $dao; 
   private $comments = array();
   private $requests = array();

  function __construct()
    {
       $this->dao = new Comment();
    }

public function Post($name, $comment)
{
    $this->dao->send($name, $comment);
}

public function GetAllComments()
{
   $d = $this->dao->GetComments();

   foreach($d as $data)
   {
     if($data['name'] == "")
      {
       $msg = new Message("Anonymous",$data['comment']);
       $this->comments[] = $msg;
      }
      else
      {
        
        $msg = new Message($data['name'],$data['comment']);
        $this->comments[] = $msg;
      }

   }
                        
}

public function GetMessages()
{
  $this->GetAllComments();
  return $this->comments;
}

public function Delete($username, $userid)
{
  $this->dao->delete($username,$userid);
}

public function GetAllRequests() 
{
  $d = $this->dao->GetRequests();

  foreach($d as $data)
   {    
        $name = $data['username'];
        $comment = "Delete request sent";
        $msg = new Message($name, $comment);
        $this->requests[] = $msg;
   }

}

public function GetDeleteMessages()
{
  $this->GetAllRequests();
  return $this->requests;
}

public function CountRequests()
{
  return $this->dao->RequestsCount();
}
    
}

?>