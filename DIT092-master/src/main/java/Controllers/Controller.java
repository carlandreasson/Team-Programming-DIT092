package Controllers;


import Objects.User.User;
import Utilities.Print;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller {
    static User currentUser;


    public static boolean checkUserID(int userID) {
        if (!UserController.userExists(userID)) {
            Print.print(Print.USER_DOES_NOT_EXIST);
            return false;
        } else {
            return true;
        }
    }


    public static boolean checkPassword(int userID, String password) {
        User user = UserController.getUser(userID);
        if (timeOutChecker(userID)){
            if (UserController.getUserPassword(userID).equals(password)) {
                Print.print(Print.ACCESS_GRANTED);
                user.setTimeOutInc(0);
                loginSuccessful(user);
                return true;
            }else if(user.getTimeOutInc() < 3) {
                Print.print(Print.WRONG_PASSWORD);
                user.incTimeOut();
                System.out.println(4 - user.getTimeOutInc() + Print.ATTEMPTS_LEFT);
                return false;
            } else{
                LockOutUser(user);
                return false;
            }
                }else{
            getLockedOutTime(user);
            System.out.println(getLockedOutTime(user));
                 return false;
                }
            }


    public static User getCurrentUser() {
        return currentUser;
    }

    public static void loginSuccessful(User user) {
        currentUser = user;
        user.setTimeOut(LocalDateTime.now());
    }
    public static String getAttemptsLeft(User user){
        return 4 - user.getTimeOutInc() + Print.ATTEMPTS_LEFT;

    }
    public static void logOut() {
        currentUser = null;}

    public static boolean timeOutChecker(int userID) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timeOut = UserController.getUser(userID).getTimeOut();
        return now.isAfter(timeOut);
        }

    public static void LockOutUser(User user){
        user.timeOutInMinutes();
        System.out.println(getLockedOutTime(user));
    }

    public static String getLockedOutTime(User user){
        return Print.THE_ACCOUNT_HAS_BEEN_LOCKED + user.getTimeOut().format(DateTimeFormatter.ofPattern("H:mm:ss"));
    }
}





