package Objects.Message;

import Controllers.Controller;
import Controllers.UserController;

import java.io.Serializable;

public class Message implements Serializable {

    private long messageID;
    private int senderID;
    private String senderName;
    private int receiverID;
    private String createdDate;
    private String message;
    private boolean read;

    public Message(long messageID, int senderID, String senderName, int receiverID, String message, String createdDate) {
        this.messageID = messageID;
        this.senderID = senderID;
        this.senderName = senderName;
        this.receiverID = receiverID;
        this.createdDate = createdDate;
        this.message = message;
        this.read = false;
    }


    @Override
    public String toString() {
        if(getSenderID() == (Controller.getCurrentUser().getID())){
            return "To " + UserController.getUser(receiverID).getName() + ": " + "\nSent: " + getCreatedDate();
        }else{
            return "From " + getSenderName() + ": " + getRead() + "\nSent: " + getCreatedDate();
        }

    }

    public String getRead() {
        if (!read){
            return "New Message.";
        }else{
            return "Opened.";
        }
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public long getMessageID() {
        return messageID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}
