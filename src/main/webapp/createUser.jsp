<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create User</title>
</head>
<body>
<h2>Create New User</h2>
<form action="createUser" method="POST">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="firstname">First Name:</label>
    <input type="text" id="firstname" name="firstname" required><br>

    <label for="lastname">Second Name:</label>
    <input type="text" id="lastname" name="lastname" required><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <input type="submit" value="Create User">
</form>
</body>
</html>
