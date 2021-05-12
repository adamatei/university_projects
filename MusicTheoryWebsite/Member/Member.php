<?php

require_once('../DATABASE/connection.php');
require_once('../User/User.php');
require_once('../User/updateAccount.php');
require_once('../Grade/ProgressManager.php');
require_once('../Grade/Grade.php');


class Member
{
	private $update;
	private $manager;
    private $id;
    private $guitarProgress;
    private $pianoProgress;
    private $drumsProgress;
    private $fluteProgress;
    private $username;
    private $password;
    private $type;
    private $email;
    private $grades;
 
    function __construct()
    {
      $this->manager = new ProgressManager();
      $this->update = new updateAccount();
      $this->id = [$_SESSION['user_session']['id']];
      $this->username = $_SESSION['user_session']['username'];
      $this->password = $_SESSION['user_session']['password'];
      $this->type = $_SESSION['user_session']['user_type'];
      $this->email = $_SESSION['user_session']['email'];
      $this->grades = new Grade();
    }

    public function GetGuitarProgress()
    {
    	$this->SetGuitarProgress();
    	return $this->guitarProgress;
    }

    public function GetPianoProgress()
    {
    	$this->SetPianoProgress();
    	return $this->pianoProgress;
    }

    public function GetDrumsProgress()
    {
    	$this->SetDrumsProgress();
    	return $this->drumsProgress;
    }

    public function GetFluteProgress()
    {
    	$this->SetFluteProgress();
    	return $this->fluteProgress;
    }


    public function SetGuitarProgress()
    {
         $this->guitarProgress = $this->manager->GetGuitarProgress();
    }

    public function SetPianoProgress()
    {
         $this->pianoProgress = $this->manager->GetPianoProgress();
    }

    public function SetDrumsProgress()
    {
         $this->drumsProgress = $this->manager->GetDrumsProgress();
    }

    public function SetFluteProgress()
    {
         $this->fluteProgress = $this->manager->GetFluteProgress();
    }


    public function GetId()
    {
    	return $this->id;
    }


    public function SetId($i)
    {
    	$this->id = $i;
    }

    public function GetUsername()
    {
    	return $this->username;
    }

    
    public function GetPassword()
    {
    	return $this->password;
    }

     public function SetPassword($newPassword, $currentPassword, $confirmPassword)
    {
    	 $this->password = $newPassword;
    	 $this->update->change($newPassword, $currentPassword, $confirmPassword);
    }

    public function GetEmail()
    {
    	return $this->email;
    }

    public function SetEmail($newMail)
    {
    	$this->update->changeMail($newMail);
    	$this->email = $newMail;
    }

    public function UpdateGuitarProgress($grade)
    {
    	$this->manager->UpdateGuitarGrade($grade);
    	$this->SetGuitarProgress();
    }

    public function UpdatePianoProgress($grade)
    {
    	$this->SetPianoProgress();
    	$this->manager->UpdatePianoGrade($grade);
    }

    public function UpdateDrumsProgress($grade)
    {
    	$this->SetDrumsProgress();
    	$this->manager->UpdateDrumsGrade($grade);
    }

    public function UpdateFluteProgress($grade)
    {
    	$this->SetFluteProgress();
    	$this->manager->UpdateFluteGrade($grade);
    }

    public function GetGuitarPoints()
    {
        return $this->grades->getSingleValueG($this->id);
    }

    public function GetPianoPoints()
    {
        return $this->grades->getSingleValueP($this->id);
    }

    public function GetDrumsPoints()
    {
        return $this->grades->getSingleValueD($this->id);
    }

    public function GetFlutePoints()
    {
        return $this->grades->getSingleValueF($this->id);
    }



    

}
?>