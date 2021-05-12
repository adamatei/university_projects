<?php

require_once("Administrator.php");


$admin = new Administrator(); 

if(isset($_POST['update_btn']))
{
    $title = $_POST['title'];
    $text = $_POST['text'];

    $admin->AboutUsUpdate($title,$text);
}


if((isset($_POST['add_btn'])) && (isset($_POST['instrument'])))
{
	
    $instrumentid = $_POST['instrument'];
	$url = $_POST['url'];
    $text = $_POST['text'];

    //Code for updating the Additional materials page
    $admin->AdditionalMaterialsUpdate($instrumentid, $text, $url);
}

?>