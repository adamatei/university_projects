<?php

include('configuration.php');

class Connection {


    private $host = DBHOST;
	private $user = DBUSER;
    private $pwd = DBPWD;
    private $dbName = DBNAME;
	 
    public function connect() {
        $dsn = 'mysql:host=' . $this->host .';dbname=' . $this->dbName;
        $pdo = new PDO($dsn, $this->user, $this->pwd);
        // setting fetch mode to associative array
        $pdo-> setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, pDO::FETCH_ASSOC);

        return $pdo;
    }
}


