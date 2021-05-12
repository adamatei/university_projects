<?php

require_once('../User/User.php');
require_once('../Grade/CheckTest.php');
require_once('../Member/Member.php');
require_once('../Grade/Test.php');

$user = new User();
$pd = new Member();
$check = new Test("flute");

if(isset($_POST['submit_btn'])) 
{ 
	$answer1 = $_POST['question-1-answers'];
    $answer2 = $_POST['question-2-answers'];
    $answer3 = $_POST['question-3-answers'];
    $answer4 = $_POST['question-4-answers'];
    $answer5 = $_POST['question-5-answers'];

$instrument  = $check->GetInstrument();

$check->CheckAnswer1($answer1);
$check->CheckAnswer2($answer2);
$check->CheckAnswer3($answer3);
$check->CheckAnswer4($answer4);
$check->CheckAnswer5($answer5);
$totalCorrect = $check->GetTotal();
            
$pd->UpdateFluteProgress($totalCorrect);

$user->redirect('../Grade/flute.php');
}

?>