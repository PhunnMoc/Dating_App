package DAO;

import java.util.List;

import Models.Message;
import Models.Profile;

public class Main {
    public static void main(String[] args) {
        // Thử nghiệm hàm select_other_user_message
        testSelectOtherUserMessage("user1_id");

        // Thử nghiệm hàm select_message_last
        testSelectMessageLast("user1_id", "user2_id");
    }

    public static void testSelectOtherUserMessage(String mainUserId) {
        ChatDAO chatDAO = new ChatDAO();
        List<Profile> profiles = chatDAO.select_other_user_message(mainUserId);

        // In thông tin của các người dùng khác
        for (Profile profile : profiles) {
            System.out.println("UserID: " + profile.getUserID() +
                               ", Name: " + profile.getName() +
                               ", Image URL: " );
        }
    }

    public static void testSelectMessageLast(String mainUserId, String otherUserId) {
        ChatDAO chatDAO = new ChatDAO();
        Message lastMessage = chatDAO.select_message_last(mainUserId, otherUserId);

        // In thông tin của tin nhắn cuối cùng
        if (lastMessage != null) {
            System.out.println("Time: " + lastMessage.getTime() +
                               ", Content: " + lastMessage.getContent());
        } else {
            System.out.println("Không có tin nhắn cuối cùng.");
        }
    }
}
