package com.springleaf_restaurant_backend.user.service.mail;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.springleaf_restaurant_backend.user.entities.MailInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailerServiceImpl implements MailerService {

	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private Integer port;
	@Value("${spring.mail.username}")
	private String username;
	@Value("${spring.mail.password}")
	private String password;

	@Override
	public void send(MailInfo mail) throws MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo()));
		message.setSubject("=?UTF-8?B?" + Base64.getEncoder().encodeToString("Mã xác nhận đăng ký".getBytes()) + "?=");

		// Thiết lập nội dung HTML
		String htmlContent = "<html><body><!DOCTYPE html>\r\n" + //
				"<html lang=\"UTF-8\">\r\n" + //
				"<head>\r\n" + //
				"    <meta charset=\"UTF-8\">\r\n" + //
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
				"    <title>Document</title>\r\n" + //
				"      <style>\r\n" + //
				"        .a-box {\r\n" + //
				"    max-width: 500px;\r\n" + //
				"    margin: 0px auto;\r\n" + //
				"    padding: 20px;\r\n" + //
				"    background-color: #fff;\r\n" + //
				"    border: 1px solid #ddd;\r\n" + //
				"    border-radius: 5px;\r\n" + //
				"    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n" + //
				"  }\r\n" + //
				"  \r\n" + //
				"  h1 {\r\n" + //
				"    font-size: 24px;\r\n" + //
				"    margin-bottom: 20px;\r\n" + //
				"  }\r\n" + //
				"  \r\n" + //
				"  .a-row {\r\n" + //
				"    display: flex;\r\n" + //
				"    align-items: center;\r\n" + //
				"  }\r\n" + //
				"  \r\n" + //
				"  .a-form-label {\r\n" + //
				"    flex: 1;\r\n" + //
				"    font-weight: 700;\r\n" + //
				"    margin-right: 20px;\r\n" + //
				"    cursor: default;\r\n" + //
				"    font-size: 13px;\r\n" + //
				"    line-height: 19px;\r\n" + //
				"    color: #111;\r\n" + //
				"    font-family: Arial, sans-serif;\r\n" + //
				"  }\r\n" + //
				"  \r\n" + //
				"  .a-input-text {\r\n" + //
				"    flex: 3;\r\n" + //
				"    padding: 10px 10px;\r\n" + //
				"    font-size: 50px;\r\n" + //
				"    font-weight: bold;\r\n" + //
				"    text-align: center;\r\n" + //
				"    font-size: 14px;\r\n" + //
				"    border: 1px solid #ddd;\r\n" + //
				"    border-radius: 3px;\r\n" + //
				"    box-sizing: border-box;\r\n" + //
				"    margin: 10px;\r\n" + //
				"    background-color: burlywood;\r\n" + //
				"  }\r\n" + //
				"  \r\n" + //
				"  .a-button {\r\n" + //
				"    flex: 2;\r\n" + //
				"    display: inline-block;\r\n" + //
				"    padding: 10px 5px;\r\n" + //
				"    font-size: 16px;\r\n" + //
				"    font-weight: 50px;\r\n" + //
				"    text-align: center;\r\n" + //
				"    text-decoration: none;\r\n" + //
				"    cursor: pointer;\r\n" + //
				"    background-color: #ff9900;\r\n" + //
				"    color: #fff;\r\n" + //
				"    border: none;\r\n" + //
				"    border-radius: 3px;\r\n" + //
				"    margin-left: 2px;\r\n" + //
				"  }\r\n" + //
				"  \r\n" + //
				"  .form-image {\r\n" + //
				"    max-width: 100%;\r\n" + //
				"    height: auto;\r\n" + //
				"  }\r\n" + //
				"  \r\n" + //
				"      </style>\r\n" + //
				"</head>\r\n" + //
				"<body>\r\n" + //
				"    <div class=\"a-box a-spacing-extra-large\">\r\n" + //
				"        <div class=\"a-box-inner\">\r\n" + //
				"            <div class=\"a-row a-spacing-base\">\r\n" + //
				"              <img src=\"https://drive.google.com/uc?id=1gMS0KzK8RacdkRWGt8K9huRSOxJsGKA1\" alt=\"#\" class=\"form-image\">\r\n"
				+ //
				"            </div>\r\n" + //
				"            <div class=\"a-section a-spacing-extra-large\">\r\n" + //
				"                <label for=\"ap_customer_name\" class=\"a-form-label\">Xin chào,</label>\r\n" + //
				"            </div>\r\n" + //
				"            <div class=\"a-section a-spacing-extra-large\">\r\n" + //
				"                <label for=\"ap_customer_name\" class=\"a-form-label\">Bạn vui lòng nhập mã trên vào ô xác nhận đăng ký ở website để hoàn thành quá trình đăng ký</label>\r\n"
				+ //
				"            </div>\r\n" + //
				"            <label for=\"ap_customer_name\" class=\"a-form-label\">Mã xác nhận của bạn là:</label>\r\n" + //
				"            <div class=\"a-row a-spacing-base\">\r\n";
		for (Integer key : mail.getKeys()) {
			htmlContent += "<label class=\"a-input-text\">" + key + "</label>";
		}
		htmlContent += "   </div>\r\n" + //
				"            <div class=\"a-section a-spacing-extra-large\">\r\n" + //
				"                <label for=\"ap_customer_name\" class=\"a-form-label\"><h3 style=\"color: red; display: inline-block;\">LƯU Ý: </h3> Vui lòng không cung cấp mã trên cho bất kỳ ai, kể cả nhân viên của cửa hàng.</label>\r\n"
				+ //
				"            </div>\r\n" + //
				"            <div class=\"a-section a-spacing-extra-large\">\r\n" + //
				"                <label for=\"ap_customer_name\" class=\"a-form-label\">Trân trọng,</label>\r\n" + //
				"            </div>\r\n" + //
				"        </div>\r\n" + //
				"      </div>\r\n" + //
				"</body>\r\n" + //
				"</html>\r\n";
		message.setContent(htmlContent, "text/html; charset=utf-8");
		Transport.send(message);
	}

	@Override
	public void send(String to, String subject, String body, String token) throws MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject("=?UTF-8?B?" + Base64.getEncoder().encodeToString("Nhắc hẹn lịch đặt bàn".getBytes()) + "?=");

		// Thiết lập nội dung HTML
		String htmlContent = "<html><body><!DOCTYPE html>\r\n" + //
				"<html lang=\"UTF-8\">\r\n" + //
				"<head>\r\n" + //
				"    <meta charset=\"UTF-8\">\r\n" + //
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
				"    <title>Document</title>\r\n" + //
				"      <style>\r\n" + //
				"        .a-box {\r\n" + //
				"    max-width: 500px;\r\n" + //
				"    margin: 0px auto;\r\n" + //
				"    padding: 20px;\r\n" + //
				"    background-color: #fff;\r\n" + //
				"    border: 1px solid #ddd;\r\n" + //
				"    border-radius: 5px;\r\n" + //
				"    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n" + //
				"  }\r\n" + //
				"  \r\n" + //
				"  h1 {\r\n" + //
				"    font-size: 24px;\r\n" + //
				"    margin-bottom: 20px;\r\n" + //
				"  }\r\n" + //
				"  \r\n" + //
				"  .a-row {\r\n" + //
				"    display: flex;\r\n" + //
				"    align-items: center;\r\n" + //
				"  }\r\n" + //
				"  \r\n" + //
				"  .a-form-label {\r\n" + //
				"    flex: 1;\r\n" + //
				"    font-weight: 700;\r\n" + //
				"    margin-right: 20px;\r\n" + //
				"    cursor: default;\r\n" + //
				"    font-size: 13px;\r\n" + //
				"    line-height: 19px;\r\n" + //
				"    color: #111;\r\n" + //
				"    font-family: Arial, sans-serif;\r\n" + //
				"  }\r\n" + //
				"  \r\n" + //
				"  .a-input-text {\r\n" + //
				"    flex: 3;\r\n" + //
				"    padding: 10px 10px;\r\n" + //
				"    font-size: 50px;\r\n" + //
				"    font-weight: bold;\r\n" + //
				"    text-align: center;\r\n" + //
				"    font-size: 14px;\r\n" + //
				"    border: 1px solid #ddd;\r\n" + //
				"    border-radius: 3px;\r\n" + //
				"    box-sizing: border-box;\r\n" + //
				"    margin: 10px;\r\n" + //
				"    background-color: burlywood;\r\n" + //
				"  }\r\n" + //
				"  \r\n" + //
				"  .a-button {\r\n" + //
				"    flex: 2;\r\n" + //
				"    display: inline-block;\r\n" + //
				"    padding: 10px 5px;\r\n" + //
				"    font-size: 16px;\r\n" + //
				"    font-weight: 50px;\r\n" + //
				"    text-align: center;\r\n" + //
				"    text-decoration: none;\r\n" + //
				"    cursor: pointer;\r\n" + //
				"    background-color: #ff9900;\r\n" + //
				"    color: #fff;\r\n" + //
				"    border: none;\r\n" + //
				"    border-radius: 3px;\r\n" + //
				"    margin-left: 2px;\r\n" + //
				"  }\r\n" + //
				"  \r\n" + //
				"  .form-image {\r\n" + //
				"    max-width: 100%;\r\n" + //
				"    height: auto;\r\n" + //
				"  }\r\n" + //
				"  \r\n" + //
				"      </style>\r\n" + //
				"</head>\r\n" + //
				"<body>\r\n" + //
				"    <div class=\"a-box a-spacing-extra-large\">\r\n" + //
				"        <div class=\"a-box-inner\">\r\n" + //
				"            <div class=\"a-row a-spacing-base\">\r\n" + //
				"              <img src=\"https://drive.google.com/uc?id=1gMS0KzK8RacdkRWGt8K9huRSOxJsGKA1\" alt=\"#\" class=\"form-image\">\r\n"
				+ //
				"            </div>\r\n" + //
				"            <div class=\"a-section a-spacing-extra-large\">\r\n" + //
				"                <label for=\"ap_customer_name\" class=\"a-form-label\">Xin chào,</label>\r\n" + //
				"            </div>\r\n" + //
				"            <div class=\"a-section a-spacing-extra-large\">\r\n" + //
				"                <label for=\"ap_customer_name\" class=\"a-form-label\">Bạn có lịch hẹn đặt bàn ở nhà hàng lúc : </label>\r\n"
				+ //
				"            </div>\r\n" + //
				// ------Nội dung giờ hoặc ngày tháng---------- //
				body +
				"            <div class=\"a-row a-spacing-base\">\r\n" + //

				"            <div class=\"a-section a-spacing-extra-large\">\r\n" + //
				"                <label for=\"ap_customer_name\" class=\"a-form-label\">Vui lòng đến cửa hàng đúng thời gian trên.</label>\r\n"
				+ //
				"            </div>\r\n" + //
				"            <div class=\"a-section a-spacing-extra-large\">\r\n" + //
				"                <label for=\"ap_customer_name\" class=\"a-form-label\">Trân trọng,</label>\r\n" + //
				"            </div>\r\n" + //
				"        </div>\r\n" + //
				"      </div>\r\n" + //
				"</body>\r\n" + //
				"</html>\r\n";
		message.setContent(htmlContent, "text/html; charset=utf-8");
		Transport.send(message);
	}

	@Override
	public void sendMissingIngredientsNotification(List<String> missingIngredients, List<String> restaurantNames,
			List<String> userNames)
			throws MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("vuluan01248@gmail.com"));
			String subject = "Thông báo: Nguyên liệu dưới ngưỡng đặt lại";
			message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));

			// Tạo nội dung HTML cho email với thông tin nguyên liệu, nhà hàng và tên người
			StringBuilder htmlContent = new StringBuilder();
			htmlContent.append("<html><head><style>");
			// CSS style code
			htmlContent.append("h1, h2 { font-family: 'Arial', sans-serif; color: #333333; }");
			htmlContent.append("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
			htmlContent.append("th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }");
			htmlContent.append("th { background-color: #f2f2f2; }");
			htmlContent.append(".footer { margin-top: 20px; font-family: 'Arial', sans-serif; color: #888888; }");
			htmlContent.append("</style></head><body>");
			htmlContent.append("<h1>Các nguyên liệu dưới ngưỡng đặt lại: ").append("</h1>");

			// Khởi tạo thông tin nhà hàng và tên người
			htmlContent.append("<h2>Nhà hàng: ").append(restaurantNames.get(0)).append("</h2>");
			htmlContent.append("<h2>Khách hàng: ").append(userNames.get(0)).append("</h2>");

			// Bảng danh sách nguyên liệu dưới ngưỡng
			htmlContent.append("<table>");
			htmlContent.append("<tr><th>STT</th><th>Nguyên liệu</th></tr>");

			int count = 1;
			Iterator<String> ingredientIterator = missingIngredients.iterator();
			while (ingredientIterator.hasNext()) {
				String ingredient = ingredientIterator.next();
				htmlContent.append("<tr><td>").append(count).append("</td><td>").append(ingredient).append("</td></tr>");
				count++;
			}

			htmlContent.append("</table>");
			htmlContent.append("<p>Vui lòng kiểm tra và thực hiện các hành động cần thiết.</p>");
			// Thêm chữ ký và thông tin liên hệ của nhà hàng
			htmlContent.append("<div class=\"footer\">");
			htmlContent.append("<b>Trân trọng,<br>Quản trị nhà hàng SPRINGLEAF RESTAURANT</b>");
			htmlContent.append(
					"<p>Địa chỉ: Số 288, Đường Nguyễn Văn Linh, Phường An Khánh, Quận Ninh Kiều, TP Cần Thơ<br>Số điện thoại: 0702807905</p>");
			htmlContent.append("</div>");
			htmlContent.append("</body></html>");

			// Thiết lập nội dung và gửi email
			message.setContent(htmlContent.toString(), "text/html; charset=utf-8");
			Transport.send(message);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
			// Xử lý exception khi gửi email không thành công
		}
	}

}