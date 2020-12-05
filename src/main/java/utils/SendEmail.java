package utils;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.UserModel;
 

public class SendEmail {
	
    public static final String HOST_NAME = "smtp.gmail.com";
    
    public static final int SSL_PORT = 465; // Port for SSL
 
    public static final int TSL_PORT = 587; // Port for TLS/STARTTLS
 
    public static final String APP_EMAIL = "duy2410tvt@gmail.com"; // your email
 
    public static final String APP_PASSWORD = "duythu24101408"; // your password
	
	public static void sendEmailConfirmOrder(int code, String yourEmail) {
		String contentHtml = "<div style=\"background: black; width: 350px;text-align: center; padding: 20px; margin: 20px\">"
                + "<h5 style=\"color: white\">Mã xác nhận là </h5>" + "<h3 style=\"color: white\">"+ code +"</h3>" 
         		+ "<a href=\"https://jspweb.demo.jelastic.com/xacnhandon\"><img src=\"https://jspweb.demo.jelastic.com/template/web/img/logo.png\"></a></div>";
		 configEmail("XÁC NHẬN ĐẶT HÀNG", contentHtml, yourEmail);
	}
	
	public static void sendEmailConfirmRegister(UserModel model, String yourEmail) {
		String url = "https://jspweb.demo.jelastic.com/xacnhandangky?fullname=" + model.getFullName() + "&email=" + model.getEmail() + "&password=" + model.getPassword();
		String contentHtml = "<h2><span style=\"font-size: 20px\">Chào mừng đến với</span> Electro.</h2>"
				+ "<div style=\"background: black; width: 350px;text-align: center; padding: 20px; margin: 20px\">"
				
				+ "<img style=\"margin-bottom: 20px\" src=\"https://jspweb.demo.jelastic.com/template/web/img/logo.png\"><br>"
				+ "<a href=\""+ url +"\" style=\"background-color: #d10024; color: #FFF; border: none; font-weight: 700; border-radius: 10px; padding: 10px;\">XÁC NHẬN ĐĂNG KÝ</a></div>";
		 configEmail("XÁC THỰC ĐĂNG KÝ", contentHtml, yourEmail);
	}
	
	public static void sendEmailConfirmForgotPass(String yourEmail) {
		String url = "https://jspweb.demo.jelastic.com/doimatkhau?email=" + yourEmail;
		String contentHtml = "<h2><span style=\"font-size: 20px\">Đổi mật khẩu mới nếu bạn quên mật khẩu</span></h2>"
				+ "<div style=\"background: black; width: 350px;text-align: center; padding: 20px; margin: 20px\">"
				
				+ "<img style=\"margin-bottom: 20px\" src=\"https://jspweb.demo.jelastic.com/template/web/img/logo.png\"><br>"
				+ "<a href=\""+ url +"\" style=\"background-color: #d10024; color: #FFF; border: none; font-weight: 700; border-radius: 10px; padding: 10px;\">ĐỔI MẬT KHẨU MỚI</a></div>";
		 configEmail("XÁC NHẬN QUÊN MẬT KHẨU", contentHtml, yourEmail);
	}
	
	private static void configEmail(String title, String contentHtml, String yourEmail) {
//		try {
//            // Tạo đối tượng Email
//            HtmlEmail email = new HtmlEmail();
//
//            // Cấu hình
//            email.setHostName(HOST_NAME);
//            email.setSmtpPort(465);
//            email.setAuthenticator(new DefaultAuthenticator(APP_EMAIL, APP_PASSWORD));
//            email.setSSLOnConnect(true);
//            email.setFrom(APP_EMAIL, "Electro.");
//            email.setCharset("utf-8");
//            email.setSSL(true);
//
//            // Người nhận
//            email.addTo(yourEmail);
//
//            // Tiêu đề
//            email.setSubject(title);
//
//            // Sét nội dung email định dạng HTML.
//            email.setContent(contentHtml, "text/html");
//
//            // Thiết lập các thông báo thay thế
//            // (Trong trường hợp chương trình đọc mail của người nhận ko hỗ trợ đọc HTML
//            // Email)
//            email.setTextMsg("Your email client does not support HTML messages");
//
//            // Gửi email
//            email.send();
//            
//            return "Gui tanhh cong";
//        } catch (Exception e) {
//           return e.toString();
//        }
		
		// 1) get the session object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", HOST_NAME);
        props.put("mail.smtp.socketFactory.port", SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", SSL_PORT);
        props.setProperty("mail.smtp.allow8bitmime", "true");
        props.setProperty("mail.smtps.allow8bitmime", "true");
 
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
            }
        });
 
        // 2) compose message
        try {
            MimeMessage message = new MimeMessage(session);
            try {
				message.setFrom(new InternetAddress(APP_EMAIL, "Electro."));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(yourEmail));
 
            // 3) create HTML content
            message.setSubject(title);
            message.setContent(contentHtml, "text/html");
            // 4) send message
            Transport.send(message);
        } catch (MessagingException ex) {
           ex.printStackTrace();
        }
	}
}

