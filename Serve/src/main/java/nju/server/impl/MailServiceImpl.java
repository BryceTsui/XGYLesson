package nju.server.impl;

import nju.dataServer.CourseDataService;
import nju.dataServer.impl.CourseDataServiceImpl;
import nju.model.User;
import nju.server.MailService;
import nju.utils.VerifyCodeUtils;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
@Service
public class MailServiceImpl implements MailService {


    CourseDataService courseDataService = new CourseDataServiceImpl();
    public String send(String address){

        try {
            Properties prop = new Properties();
            prop.setProperty("mail.host", "smtp.sohu.com");
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");
            //使用JavaMail发送邮件的5个步骤
            //1、创建session
            Session session = Session.getInstance(prop);
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            //2、通过session得到transport对象
            Transport ts = session.getTransport();
            //3、连上邮件服务器，需要发件人提供邮箱的用户名和密码进行验证
            //原163邮箱，防止盗号，改为*
            ts.connect("smtp.163.com", "*****", "****");
            //4、创建邮件
            VerifyCodeUtils verifyCodeUtils = new VerifyCodeUtils();
            String code = verifyCodeUtils.generate();
            Message message = createImageMail(session,address,code);
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
            return code;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @Method: createImageMail
     * @Description: 生成一封邮件正文带图片的邮件
     * @Anthor:孤傲苍狼
     *
     * @param session
     * @return
     * @throws Exception
     */
    public static MimeMessage createImageMail(Session session,String address,String code) throws Exception {
        //创建邮件
        MimeMessage message = new MimeMessage(session);
        // 设置邮件的基本信息
        //发件人
        message.setFrom(new InternetAddress("nju_rj_xgy@163.com"));
        //收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
        //邮件标题
        message.setSubject("欢迎注册MyCourse!");

        // 准备邮件数据
        // 准备邮件正文数据
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("这是您本次注册的验证码，请返回网页填写<img src='cid:xxx.jpg'>", "text/html;charset=UTF-8");
        // 准备图片数据
        MimeBodyPart image = new MimeBodyPart();

        DataHandler dh = new DataHandler(new FileDataSource("E:\\Serve\\src\\main\\resources\\verify\\"+code+".jpg"));
        image.setDataHandler(dh);
        image.setContentID("xxx.jpg");
        // 描述数据关系
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.addBodyPart(image);
        mm.setSubType("related");

        message.setContent(mm);
        message.saveChanges();
//        //将创建好的邮件写入到E盘以文件的形式进行保存
//        message.writeTo(new FileOutputStream("E:\\ImageMail.eml"));
        //返回创建好的邮件
        return message;
    }

    public static MimeMessage createStringMail(Session session,String address,String content,String title) throws Exception {
        //创建邮件
        MimeMessage message = new MimeMessage(session);
        // 设置邮件的基本信息
        //发件人
        message.setFrom(new InternetAddress("****@163.com"));
        //收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
        //邮件标题
        message.setSubject(title);

        // 准备邮件数据
        // 准备邮件正文数据
        MimeBodyPart text = new MimeBodyPart();
        text.setContent(content, "text/html;charset=UTF-8");
        // 准备图片数据
        // 描述数据关系
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.setSubType("related");

        message.setContent(mm);
        message.saveChanges();

        return message;
    }

    public void sendString(String address,String content,String title){
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.host", "smtp.sohu.com");
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");
            //使用JavaMail发送邮件的5个步骤
            //1、创建session
            Session session = Session.getInstance(prop);
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            //2、通过session得到transport对象
            Transport ts = session.getTransport();
            //3、连上邮件服务器，需要发件人提供邮箱的用户名和密码进行验证
            ts.connect("smtp.163.com", "********", "***");
            //4、创建邮件
            Message message = createStringMail(session,address,content,title);
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void sendGroupMail(String content, String title,int courseid){
        List<User> users = courseDataService.getAllUserByCourseId(courseid);

        for(int i=0;i<=users.size()-1;i++){

            try{


                String address = users.get(i).getEmail();

                sendString(address,content,title);

            }catch (Exception e){

            }
        }



    }

    public static void main(String[] args){
        MailService mailService = new MailServiceImpl();
        mailService.sendString("1275022549@qq.com","群发邮件","群发邮件");
    }
}
