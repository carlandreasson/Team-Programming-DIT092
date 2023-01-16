package Utilities;

public class Print {

    //general
    public static final String ERROR_INPUT = ("ILLEGAL VALUE");
    public static final String THE_LIST_IS_EMPTY = ("The list is currently empty");
    public static final String HAS_BEEN_DELETED = (" has been deleted");
    public static final String DEAD_LINE =("Dead Line ");
    public static final String REPLACE_WITH_ID = ("REPLACE_WITH_ID");
    public static final String FILE_PATH_NOT_FOUND = ("File path not found ");
    public static final String ERROR_LOADING_ID = ("Error while loading ID");

    //login related
    public static final String ACCESS_GRANTED = ("ACCESS GRANTED");
    public static final String WRONG_PASSWORD = ("Wrong Password");
    public static final String ATTEMPTS_LEFT = (" attempts left");
    public static final String THE_ACCOUNT_HAS_BEEN_LOCKED = ("The user account has been locked until");
    public static final String SIGN_IN_FAILED = ("Sign In Failed: ");

    //message related
    public static final String SELECT_A_MESSAGE = ("Select a message");

    //user related
    public static final String NOT_EMPTY_OR_BLANK = (" cannot be empty or blank");
    public static final String PASSWORD_NOT_MATCH = ("Passwords does not match");
    public static final String USER_NOT_EXIST = ("User does not exist");
    public static final String CANNOT_DELETE_LOGGED_IN_USER = ("Cannot delete, user is currently logged in");
    public static final String A_USERTYPE_MUST_BE_CHOSEN = ("A user type must be chosen");
    public static final String SELECT_A_USER = ("Select a user");
    public static final String USER_DOES_NOT_EXIST = ("User does not exist");
    public static final String INVALID_PASSWORD = ("Passwords must be between 8-20 characters and contain at least \na uppercase character, a lowercase character and a digit");

    //project related
    public static final String CONNECT_TO_MILESTONE_START_DATE = (" Connect to milestone to get start date");

    //team related
    public static final String SELECT_A_TEAM = ("Select a team");
    public static final String TEAM_CREATED = ("Team created");


    public static void print(String stringToPrint) {
        System.out.print(stringToPrint);
    }
}
