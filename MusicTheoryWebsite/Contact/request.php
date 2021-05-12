<?php
 require_once('CommentManager.php');


 $cmnt = new CommentManager();


   $username = trim($_POST['username']);
   $id = trim($_POST['id']);
   $cmnt->Delete($username, $id); 

?>


