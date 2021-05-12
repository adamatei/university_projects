<!DOCTYPE html>
<html>
<head>
	 
	<link rel="stylesheet" type="text/css" href="../css/styleHomePageUser.css">
	<link rel="stylesheet" type="text/css" href="../css/adminHome.css">
	<link rel="stylesheet" type="text/css" href="../css/comments.css">

</head>
<body>

<div class = "main-icon">
		    <span> 
		      <h1 class="text-center text-dark pt-2"> ANNOUNCEMENTS </h1> <br>
		    	<div class="container">

		    		<?php 

		    		  require_once("../Contact/CommentManager.php");

		    		  $manager = new CommentManager();
		    		  $d = $manager->GetMessages();

		    		?>
		    		<table class="table">
		      			<tr id = "header">
		        			<th>Published by</th>
		        			<th>Comments</th>
		      			</tr>
		      			<?php
                        foreach($d as $data)
                        {
                        	$name = $data->GetName();
                        	$comment = $data->GetComment();
                          ?> 
                          <tr>
                          <td>
                             <?php  echo $name; ?>                          		
                          </td>
                          <td>
                          	<?php echo $comment;?>                          	
                          </td>
                          </tr>
                        <?php 
                         } 
                         ?>
	      			
		    		          		
		    		</table>
		  		</div>
		    </span>
		</div>

</body>
</html>