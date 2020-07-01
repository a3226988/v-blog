package com.gc.vblog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtil {
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    //发送简单内容邮件
    public void sendSimpleMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("邮件标题");
        message.setText("邮件内容");
        message.setTo("276673204@qq.com");
        message.setFrom("276673204@qq.com");
        javaMailSender.send(message);
    }
    //发送富文本内容邮件
    public void sendRichTextMail() throws MessagingException {
        //创建一个消息类
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //使用消息帮助类来帮助我们添加复杂的数据
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setSubject("邮件标题");
        //添加富文本邮件内容
        mimeMessageHelper.setText("<html><body><h1>邮件内容</h1><img src='cid:img1'></body></html>",true);
        //添加富文本内图片的引用，其中contentid:img1对应<img src='cid:img1'>里的img1
        mimeMessageHelper.addInline("img1", new File("c:/hello.jpg"));
        mimeMessageHelper.setTo("276673204@qq.com");
        mimeMessageHelper.setFrom("276673204@qq.com");
        //添加附件
        mimeMessageHelper.addAttachment("hello.jpg",new File("c:/hello.jpg"));
        javaMailSender.send(mimeMessage);
    }
}