<?php
  require_once 'connection.php';
  header('Content-Type: application/json');

    class User{
      private $db;
      private $connection;

      function _construct(){
        $this->db = new DB_Connection();
        $this->connection = $this->db->get_connection();
      }
      public function user_exists($username, $password){
        $query = "SELECT * FROM users WHERE username = '$username' AND password = '$password'";
        $result = mysqli_query($this->connect, $query);
        if(mysqli_num_rows($result)>0){
          $json['success'] = 'Welcome' .$username;
          echo json_encode($json);
          mysqli_close($this->connect);
        }else{
          $query = "INSTERT INTO users(username, password) values ('$username', '$password')";
          $is_inserted = mysqli_query($this->conection, $query);
          if($is_inserted==1){
            $json['success'] = 'Account Created';
          }
          else{
            $json['error'] = 'Wrong Password';
          }
          echo json_encode($json);
          mysqli_close($this->connection);
          }
      }
  }
    $user = new User();
    if(isset($_POST['username'],$_POST['password'])){
      $username = $_POST['username'];
      $password = $_POST['password'];

      if(!empty($username) && !empty($password)){
        $encrypted_password = md5($password);
        $user->user_exists($username,$encrypted_password);
      }
      else{
        echo json_encode("You must fill both fields");
      }
    }







?>
