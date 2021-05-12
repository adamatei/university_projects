<!DOCTYPE html>
<html>
<head>
	 
	<link rel="stylesheet" type="text/css" href="../css/styleHomePageUser.css">
	<link rel="stylesheet" type="text/css" href="../css/adminHome.css">
	<link rel="stylesheet" type="text/css" href="../css/deleteRequests.css">

</head>
<body>

<div class = "content">
		    <span> 
		      <h1 class="title"> DELETE ACCOUNT REQUESTS </h1> <br>
		    	<div class="container">

		    		<?php 

		    		  require_once("../Contact/CommentManager.php");

		    		  $manager = new CommentManager();
		    		  $d = $manager->GetDeleteMessages();

                      if($manager->CountRequests() > 0){
		    		?>
		    		<center><table class="table">
		      			<tr id = "header">
		        			<th>Sent by</th>
		        			<th>Comments</th>
		      			</tr>
		      			<?php
		      			
                        foreach($d as $data)
                        {
                        	$name = $data->GetName();
                        	$comment = $data->GetComment();
                          ?> <tr>
                          <td>
                             <?php 
                             
                                echo $name;
                            
                              ?>
                          		
                          </td>
                          <td><?php echo $comment;?></td>
                          </tr>
                        <?php 
                         } 
                     }else  { echo "No requests received";}
                         ?>
                     
	      			
		    		          		
		    		</table></center>
		  		</div>
		    </span>
		</div>

</body>
</html>