<?php

require_once('../Database/connection.php');

class CheckTest
{
    private $dbConnection;
    private $totalScore = 0;

    public function __construct()
    {
      $this->dbConnection = new Connection();
    }

//Check the first exercise from the quiz
     function CheckEx1($answer, $instrument)
    {
    	$sql = "SELECT `answer1` FROM `answers` WHERE `instrument`= ?";
        $q = $this->dbConnection->connect()->prepare($sql);
        $q->execute(array($instrument));
        

        if($q->rowCount())
        {
           while($data = $q->fetch(PDO::FETCH_NUM))
           {
              if($answer == $data[0]){ $this->totalScore = $this->totalScore + 1;
              	return true;}                  
           }

        }

      return false;

    }


//Check the second exercise from the quiz
     function  CheckEx2($answer, $instrument)
    {
    	$sql = "SELECT `answer2` FROM `answers` WHERE `instrument`= ?";
        $q = $this->dbConnection->connect()->prepare($sql);
        $q->execute(array($instrument));
       

        if($q->rowCount())
        {
           while($data = $q->fetch(PDO::FETCH_NUM))
           {
              if($answer == $data[0]){$this->totalScore = $this->totalScore + 1; return true;}                  
           }

        }

      return false;

    }



//Check the third exercise from the quiz
     function  CheckEx3($answer, $instrument)
    {
    	$sql = "SELECT `answer3` FROM `answers` WHERE `instrument`= ?";
        $q = $this->dbConnection->connect()->prepare($sql);
        $q->execute(array($instrument));
       

        if($q->rowCount())
        {
           while($data = $q->fetch(PDO::FETCH_NUM))
           {
              if($answer == $data[0]){$this->totalScore = $this->totalScore + 1; return true;}                  
           }

        }

      return false;

    }


//Check the fourth exercise from the quiz
     function  CheckEx4($answer, $instrument)
    {
    	$sql = "SELECT `answer4` FROM `answers` WHERE `instrument`= ?";
        $q = $this->dbConnection->connect()->prepare($sql);
        $q->execute(array($instrument));
       

        if($q->rowCount())
        {
           while($data = $q->fetch(PDO::FETCH_NUM))
           {
              if($answer == $data[0]){$this->totalScore = $this->totalScore + 1; return true;}                  
           }

        }

      return false;

    }

//Check the fifth exercise from the quiz
     function CheckEx5($answer, $instrument)
    {
    	$sql = "SELECT `answer5` FROM `answers` WHERE `instrument`= ?";
        $q = $this->dbConnection->connect()->prepare($sql);
        $q->execute(array($instrument));
       
        if($q->rowCount())
        {
           while($data = $q->fetch(PDO::FETCH_NUM))
           {
              if($answer == $data[0]){$this->totalScore = $this->totalScore + 1; return true;}                  
           }

        }

      return false;

    }


    function GetTotalScore()
    {
    	return $this->totalScore;
    }
}

?>