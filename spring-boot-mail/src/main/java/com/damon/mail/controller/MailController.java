package com.damon.mail.controller;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private Configuration freemarkerConfiguration;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 简单邮件测试
     *
     * @return success
     */
    @GetMapping("/simple")
    public String simple() {
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人邮箱
        message.setFrom(this.mailProperties.getUsername());
        //收件人邮箱
        message.setTo("1123595593@qq.com");
        //邮件主题
        message.setSubject("简单邮件测试");
        //邮件内容
        message.setText("简单邮件测试");
        this.javaMailSender.send(message);
        return "success";
    }

    /**
     * HTML内容邮件测试
     *
     * @return success
     * @throws MessagingException
     */
    @GetMapping("/html")
    public String html() throws MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        //发件人邮箱
        messageHelper.setFrom(this.mailProperties.getUsername());
        //收件人邮箱
        messageHelper.setTo("1123595593@qq.com");
        //邮件主题
        messageHelper.setSubject("HTML内容邮件测试");
        //邮件内容  第二个参数表示是否HTML
        messageHelper.setText("<h1>HTML内容..</h1>", true);
        this.javaMailSender.send(message);
        return "success";
    }

    /**
     * 带附件的邮件测试
     *
     * @return success
     * @throws MessagingException
     */
    @GetMapping("/attach")
    public String attach() throws MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        //发件人邮箱
        messageHelper.setFrom(this.mailProperties.getUsername());
        //收件人邮箱
        messageHelper.setTo("1123595593@qq.com");
        //邮件主题
        messageHelper.setSubject("带附件的邮件测试");
        //邮件内容  第二个参数表示是否HTML
        messageHelper.setText("<h1>HTML内容..</h1>", true);
        messageHelper.addAttachment("附件名称", new ClassPathResource("wx.jpg"));
        this.javaMailSender.send(message);
        return "success";
    }


    /**
     * 内联附件的邮件测试
     *
     * @return success
     * @throws MessagingException
     */
    @GetMapping("/inline-attach")
    public String inlineAttach() throws MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        //发件人邮箱
        messageHelper.setFrom(this.mailProperties.getUsername());
        //收件人邮箱
        messageHelper.setTo("1123595593@qq.com");
        //邮件主题
        messageHelper.setSubject("内联附件的邮件测试");
        //邮件内容  第二个参数表示是否HTML
        messageHelper.setText("<h1>HTML内容..<img src=\"cid:attach\"/></h1>", true);
        messageHelper.addInline("attach", new ClassPathResource("wx.jpg"));
        this.javaMailSender.send(message);
        return "success";
    }

    /**
     * freemarker模板的邮件测试
     *
     * @return success
     * @throws MessagingException
     */
    @GetMapping("/freemarker")
    public String freemarker() throws MessagingException, IOException, TemplateException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        //发件人邮箱
        messageHelper.setFrom(this.mailProperties.getUsername());
        //收件人邮箱
        messageHelper.setTo("1123595593@qq.com");
        //邮件主题
        messageHelper.setSubject("freemarker模板的邮件测试");

        Map<String, Object> model = new HashMap<>();
        model.put("username", "damon");
        model.put("event", "IT牧场大事件");
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(this.freemarkerConfiguration.getTemplate("mail.ftl"), model);
        //邮件内容  第二个参数表示是否HTML
        messageHelper.setText(content, true);

        this.javaMailSender.send(message);
        return "success";
    }

    /**
     * thymeleaf模板的邮件测试
     *
     * @return success
     * @throws MessagingException
     */
    @GetMapping("/thymeleaf")
    public String thymeleaf() throws MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        //发件人邮箱
        messageHelper.setFrom(this.mailProperties.getUsername());
        //收件人邮箱
        messageHelper.setTo("1123595593@qq.com");
        //邮件主题
        messageHelper.setSubject("thymeleaf模板的邮件测试");

        Context context = new Context();
        context.setVariable("id", "006");

        String content = templateEngine.process("emailTemplate", context);
        //邮件内容  第二个参数表示是否HTML
        messageHelper.setText(content, true);

        this.javaMailSender.send(message);
        return "success";
    }

}
