<?php

require_once('../Database/connection.php');
require_once('Grade.php');

                   

   class Score  {

    private $dbConnection;
    private $points;

    public function __construct($points)
    {
       $this->dbConnection = new Connection();
       $this->points = $points;
    }

    public function GetPoints()
    {
    	return $this->points;
    }

    public function CalculatePercentage()
    {
    	return (($this->points * 100)/5);
    }

}
?>          
                