<?php


class Test
{
	private $instrument;
	private $check;
    private $total = 0;

    public function __construct($t)
    {
      $this->check = new CheckTest();
      $this->instrument = $t;
    }

    public function CheckAnswer1($answer)
    {
        if($this->check->CheckEx1($answer, $this->instrument))
        {
            $this->total = $this->total + 1;
        }
       

    }

    public function CheckAnswer2($answer)
    {
        if($this->check->CheckEx2($answer, $this->instrument))
        {
            $this->total = $this->total + 1;
        }
        
    }

    public function CheckAnswer3($answer)
    {
        if($this->check->CheckEx3($answer, $this->instrument))
        {
            $this->total = $this->total + 1;
        }
      
    }

    public function CheckAnswer4($answer)
    {
        if($this->check->CheckEx4($answer, $this->instrument))
        {
           $this->total = $this->total + 1;
        }
     
     
    }

    public function CheckAnswer5($answer)
    {
        if($this->check->CheckEx5($answer, $this->instrument))
        {
           $this->total = $this->total + 1;
        }
       
    }

    public function GetTotal()
    {
    	return $this->check->GetTotalScore();

    }

    public function GetInstrument()
    {
        return $this->instrument;
    }




}
?>