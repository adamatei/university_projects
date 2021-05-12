<?php
	// the information this application needs to connect to the database
	// use a separate file because this information changes depending on environment (DEV, TEST, ACC, PROD) and time (password change policy)

    
    define('DBUSER','root');
    define('DBPWD','');
    define('DBHOST','localhost');
    define('DBNAME','dbi439292');

    /*define('DBUSER','dbi439292');
    define('DBPWD','12345');
    define('DBHOST','studmysql01.fhict.local');
    define('DBNAME','dbi439292');*/
    
?>