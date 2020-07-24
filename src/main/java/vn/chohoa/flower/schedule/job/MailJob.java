package vn.chohoa.flower.schedule.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.chohoa.flower.email.MailService;
import vn.chohoa.flower.service.LogEmailService;

@Component
@Slf4j
public class MailJob implements Job {

    @Autowired
    private MailService mailService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        mailService.autoSendMail();
    }
}
