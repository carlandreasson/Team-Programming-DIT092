package Controllers;


import Objects.Message.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageController {
   private static String currentDateTime;
   private static int senderID;
   private static String senderName;


    public static void createMessage(int receiverID, String text){
        currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - H:mm:ss"));
        senderID = Controller.getCurrentUser().getID();
        senderName = Controller.getCurrentUser().getName();
        String text2 = "From: " + senderName + "\n" + currentDateTime + "\n" + text;
        Message message = new Message(messageID(currentDateTime) ,senderID, senderName, receiverID, text2, currentDateTime);
        UserController.getUser(senderID).addMessage(messageID(currentDateTime), message);
        UserController.getUser(receiverID).addMessage(messageID(currentDateTime), message);

    }
    public static void replyMessage(Message message, String replyText, int receiverID){

        currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - H:mm:ss"));
        senderID = Controller.getCurrentUser().getID();
        senderName = Controller.getCurrentUser().getName();
        String newMessage = message.getMessage() + "\n\n" + "From: " + senderName + "\n" + currentDateTime + "\n" + replyText;
        message.setMessage(newMessage);
        message.setReceiverID(receiverID);
        message.setSenderID(senderID);
        message.setSenderName(senderName);
        UserController.getUser(senderID).addMessage(message.getMessageID(), message);
        UserController.getUser(receiverID).addMessage(message.getMessageID(), message);
        UserController.getUser(receiverID).getMessage(message.getMessageID()).setRead(false);
    }
    public static void deleteMessage(long messageID){
        UserController.getUser(Controller.currentUser.getID()).removeMessage(messageID);
    }

    public static long messageID(String currentDateTime){
        //Takes the date and time that the message was created and turns it into a unique message ID
        String s = currentDateTime.replaceAll("[^\\d]", "");
        long messageID = Long.parseLong(s);
        return messageID;
    }

}
