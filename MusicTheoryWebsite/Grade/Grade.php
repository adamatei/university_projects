<?php

require_once('../Database/connection.php');

                   

                   class Grade  {

                       private $dbConnection;

                       public function __construct(){
                       $this->dbConnection = new Connection();
                         }
          
                
                // Update the grade from guitar test in database
                      function updateggrade ($grade)
                {
                
                    $userid = $_SESSION['user_session']['id'];
                    $ggrade = $grade;

                    $query = "UPDATE `grades` SET `ggrade`=:ggrade WHERE `userid`=:userid ";

                     $pdoResult = $this->dbConnection->connect()->prepare($query);

                    $pdoExec = $pdoResult -> execute(array(":ggrade" => $ggrade,":userid"=> $userid));

                }


               // Update the grade from drum test in database
                      function updatedgrade ($grade)
                {
                
                    $userid = $_SESSION['user_session']['id'];
                    $dgrade = $grade;

                    $query = "UPDATE `grades` SET `dgrade`=:dgrade WHERE `userid`=:userid ";

                     $pdoResult = $this->dbConnection->connect()->prepare($query);

                    $pdoExec = $pdoResult -> execute(array(":dgrade" => $dgrade,":userid"=> $userid));

                }


               // Update the grade from piano test in database
                      function updatepgrade ($grade)
                {
                
                    $userid = $_SESSION['user_session']['id'];
                    $pgrade = $grade;

                    $query = "UPDATE `grades` SET `pgrade`=:pgrade WHERE `userid`=:userid ";

                     $pdoResult = $this->dbConnection->connect()->prepare($query);

                    $pdoExec = $pdoResult -> execute(array(":pgrade" => $pgrade,":userid"=> $userid));

                }


                // Update the grade from flute test in database
                      function updatefgrade ($grade)
                {
                
                    $userid = $_SESSION['user_session']['id'];
                    $fgrade = $grade;

                    $query = "UPDATE `grades` SET `fgrade`=:fgrade WHERE `userid`=:userid ";

                     $pdoResult = $this->dbConnection->connect()->prepare($query);

                    $pdoExec = $pdoResult -> execute(array(":fgrade" => $fgrade,":userid"=> $userid));

                }


                
 
                 // Get the grade for guitar test from database
                    function getSingleValueG($parameters)
                     {
                        $sql = "SELECT `ggrade` FROM `grades` WHERE `userid`= ?";
                        $q = $this->dbConnection->connect()->prepare($sql);
                        $q->execute($parameters);
                        return $q->fetchColumn();
                     }



                 // Get the grade for piano test from database
                    function getSingleValueP($parameters)
                     {
                        $sql = "SELECT `pgrade` FROM `grades` WHERE `userid`= ?";
                        $q = $this->dbConnection->connect()->prepare($sql);
                        $q->execute($parameters);
                        return $q->fetchColumn();
                     }



                 // Get the grade for drums test from database
                    function getSingleValueD($parameters)
                     {
                        $sql = "SELECT `dgrade` FROM `grades` WHERE `userid`= ?";
                        $q = $this->dbConnection->connect()->prepare($sql);
                        $q->execute($parameters);
                        return $q->fetchColumn();
                     }


                 // Get the grade for flute test from database
                    function getSingleValueF($parameters)
                     {
                        $sql = "SELECT `fgrade` FROM `grades` WHERE `userid`= ?";
                        $q = $this->dbConnection->connect()->prepare($sql);
                        $q->execute($parameters);
                        return $q->fetchColumn();
                     }

 }


