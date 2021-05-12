<?php

require_once('Grade.php');
require_once('../DATABASE/connection.php');
require_once('Score.php');



class ProgressManager
{
	private $data;
	private $guitarProgress;
    private $pianoProgress;
    private $drumsProgress;
    private $fluteProgress;
 
    function __construct()
    {
       $this->data = new Grade();
       
    }

    public function GetGuitarProgress()
    {
        $g = $this->data->getSingleValueG([$_SESSION['user_session']['id']]);
        $this->guitarProgress = new Score($g);
        $percentage = $this->guitarProgress->CalculatePercentage();
        return $percentage;
    }

    public function GetPianoProgress()
    {
        $p = $this->data->getSingleValueP([$_SESSION['user_session']['id']]);
        $this->pianoProgress = new Score($p);
        $percentage = $this->pianoProgress->CalculatePercentage();
        return $percentage;
    }

    public function GetDrumsProgress()
    {
        $d = $this->data->getSingleValueD([$_SESSION['user_session']['id']]);
        $this->drumsProgress = new Score($d);
        $percentage = $this->drumsProgress->CalculatePercentage();
        return $percentage;
    }

    public function GetFluteProgress()
    {
        $f = $this->data->getSingleValueF([$_SESSION['user_session']['id']]);
        $this->fluteProgress = new Score($f);
        $percentage = $this->fluteProgress->CalculatePercentage();
        return $percentage;
    }

    public function UpdateGuitarGrade($grade)
    {
        $this->data->updateggrade ($grade);
    }

     public function UpdatePianoGrade($grade)
    {
        $this->data->updatepgrade ($grade);
    }

     public function UpdateDrumsGrade($grade)
    {
        $this->data->updatedgrade ($grade);
    }

     public function UpdateFluteGrade($grade)
    {
        $this->data->updatefgrade ($grade);
    }


}
?>