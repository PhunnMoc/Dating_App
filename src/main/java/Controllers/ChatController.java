package Controllers;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import DAO.ChatDAO;
@ServerEndpoint("/chat/{sender}/{receiver}")
public class ChatController {
  
	   //private static final Map<String, Session> userSessions = Collections.synchronizedMap(new HashMap<>());
	   private static final Map<String, Map<String, Session>> userSessions = Collections.synchronizedMap(new HashMap<>());
	    @OnOpen
	    public void onOpen(Session session, @PathParam("sender") String sender, @PathParam("receiver") String receiver) {
	        
	    	String senderSessionId = sender; // Tạo một ID duy nhất cho người gửi
	        String receiverSessionId = receiver;
	    	// Lưu thông tin phiên của người gửi và người nhận
	        Map<String, Session> userSessionMap = new HashMap<>();
	        userSessionMap.put(sender, session);
	        userSessionMap.put(receiver, session);

	        // Lưu thông tin phiên theo từng người dùng vào map chính
	        userSessions.put(senderSessionId, userSessionMap);
	        System.out.println("userSession2  " + userSessions);
	        System.out.println("WebSocket opened: " + session.getId() + " - Sender: " + sender + ", Receiver: " + receiver);
	        
	    }

	    @OnMessage
	    public void onMessage(String message, Session session, @PathParam("sender") String sender, @PathParam("receiver") String receiver) throws SQLException {
	        // Gửi tin nhắn chỉ cho người nhận
	        sendMessageToUser(receiver, message);
	        ChatDAO messageDAO = new ChatDAO();
	        try {
	            messageDAO.insertMessage(receiver, sender, message);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    

	    @OnClose
	    public void onClose(Session session, @PathParam("sender") String sender, @PathParam("receiver") String receiver) {
	        // Xóa thông tin phiên khi kết nối bị đóng
	        userSessions.remove(sender);
	        userSessions.remove(receiver);
	        System.out.println("WebSocket closed: " + session.getId() + " - Sender: " + sender + ", Receiver: " + receiver);
	    }

	    private void sendMessageToUser(String userId, String message) {
	        Session userSession = (Session) userSessions.get(userId);
	        System.out.println("Message  " + message);
	        if (userSession != null && userSession.isOpen()) {
	            try {
	                userSession.getBasicRemote().sendText(message);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
  
}