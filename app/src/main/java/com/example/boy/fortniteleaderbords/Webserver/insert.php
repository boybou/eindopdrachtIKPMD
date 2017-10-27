<?php

if($_SERVER["REQUEST_METHOD"] == "POST")
{
    require 'connection.php';
    createUser();
}

function createUser()
{
    global $connect;

    $userName        = $_POST["userName"];
    $soloKills       = $_POST["soloKills"];
    $soloGames       = $_POST["soloGames"];
    $soloWins        = $_POST["soloWins"];
    $duoKills        = $_POST["duoKills"];
    $duoGames        = $_POST["duoGames"];
    $duoWins         = $_POST["duoWins"];
    $squadKills      = $_POST["squadKills"];
    $squadGames      = $_POST["squadGames"];
    $squadWins       = $_POST["squadWins"];
    $date            = $_POST["date"];

    $query = "INSERT INTO users(userName,soloKills,soloGames,soloWins,duoKills,duoGames,duoWins,squadKills,squadGames,squadWins,date) VALUES('$userName','$soloKills','$soloGames','$soloWins','$duoKills','$duoGames','$duoWins','$squadKills','$squadGames','$squadWins','$date')";
    mysqli_query($connect,$query) or die(mysqli_error($connect));
    mysqli_close($connect);

}


?>