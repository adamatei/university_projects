<?php
 require_once('CommentManager.php');


 $cmnt = new CommentManager();


   $name = trim($_POST['name']);
   $comment = trim($_POST['comment']);
   $cmnt->Post($name, $comment); 

?>


