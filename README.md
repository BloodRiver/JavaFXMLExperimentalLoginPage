
# Java FXML Experimental Login Page

This project consists of 2 packages although there are not many classes and FXML documents to require 2 packages.

In `mainpkg` there are 2 scenes along with 2 controller classes:
 - `LoginScene` -> `LoginSceneController`
 - `NewAccountScene` -> `NewAccountSceneController`

In `models` package, there is only one model class `User`.

## The `LoginSceneController` Class:

### `newAccountLabelOnClick` method

As this is a mouse click event, an `event` of type `MouseEvent` is passed as a parameter to this method.

From this parameter, using the `.getSource()` method, we get the `Object` which called this method upon being clicked.

This `Object` was then converted to a `Label` class and then using its `.getScene()` method, we are able to access the currently visible scene.
The `.getScene()` method also returns an object which has to be converted to the `Scene` class before it can be used to access the current window that is open.
```java
Scene currentScene = (Scene) ((Label) event.getSource()).getScene();

Stage currentStage = (Stage) currentScene.getWindow();
```

The `.getWindow()` method returns an object which does not automatically convert to the `Stage` class so we need to explicitly type-cast it.

Finally, we change the window and shift to the New Account Scene using
```java

Scene newAccountScene = new Scene(FXMLLoader.load(getClass().getResource("NewAccountScene.fxml")));
currentStage.setScene(newAccountScene);
```

### `loginButtonOnClick` method:

At first, we `try` to load user data using the **static** class method `User.loadUser` which belongs to <a style="font-weight: bold; text-decoration: underline;" href="#the-user-model-class">User</a> class.
The parameter that this method takes is the username entered by the user which we get from the `usernameTextField`
using `usernameTextField.getText()` method;

If the text file `User.txt` does not exist, it will show an error message saying that the Text File is missing (`FileNotFoundException`).

Otherwise, if the text file exists but for any reason, the text file can not be read, the program will show and error message saying "Error reading file" (`IOException`).

If a user with the given username already exists in the text file database, then this method will return a `User` object otherwise `null`.
If the method returns `null` then it means that a `User` with the given username was not found in the text file database and therefore, we can proceed to check if the password they entered matches the corresponding user's password or not.

The password will be verified using the `.checkPassword` method defined in the <a style="font-weight: bold; text-decoration: underline;" href="#the-user-model-class">User</a> class.

## The `NewAccountSceneController` Class:

### `loginLabelOnClick` method:

Same as the <a href="#newaccountlabelonclick-method" style="font-weight: bold; text-decoration: underline;">`newAccountLabelOnClick` method</a> except this label redirects to the login page on click.

### `createAccountButtonOnClick` method

Similar to the <a href="#loginButtonOnClick-method" style="font-weight: bold; text-decoration: underline;">`loginButtonOnClick` method</a>, loading a `User` with the given username will be attempted.
However, in this method, if a user with the given name is **not** found, then it means we can create a new account with the given username.
Before using the <a style="color: black; font-weight: bold; text-decoration: underline;" href="#the-user-model-class">User</a> class's constructor to create a new user and then save it in the textfile database using the `.save()` method,
we check to see if the data in the `newPasswordPasswordField` and the `retypePasswordPasswordField` are equal or not. If yes, then the new account is created otherwise and error message is shown.

## The `User` model Class
