<?php

if($_SERVER["REQUEST_METHOD"] == "POST")
{
    require 'connection.php';
    deleteUser();
}

function deleteUser()
{

    global $connect;

    $userName = $_POST["userName"];

    $query = "DELETE FROM users WHERE userName = '$userName'";

    mysqli_query($connect,$query) or die (mysqli_error($connect));
    mysqli_close($connect);
}
