<?php

require_once('Admin.php');

class Administrator
{
    private $ad;
   
 
    function __construct()
    {
       $this->ad = new Admin();
    }

    public function AccountDelete($username)
    {
    	$this->ad->DeleteAccount($username);
    }

    public function CheckUsername($username)
    {
    	return $this->ad->check($username);
    }

    public function AboutUsUpdate($title, $text)
    {
    	$this->ad->UpdateAboutUs($title, $text);
    }

    public function AdditionalMaterialsUpdate($instrumentid, $text, $url)
    {
    	$this->ad->UpdateAdditionalMaterials($instrumentid, $text, $url);
    }

}
?>
