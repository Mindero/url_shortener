package org.example.schedulers;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.example.kafka.UrlDeleteProducer;
import org.example.service.url.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class UrlDeleteScheduler {
    private static final String URL_DELETE_LOCK_NAME = "url-delete-lock";

    private final UrlDeleteProducer urlDeleteProducer;
    private final UrlService urlService;

    public UrlDeleteScheduler(UrlDeleteProducer urlDeleteProducer, UrlService urlService) {
        this.urlDeleteProducer = urlDeleteProducer;
        this.urlService = urlService;
    }

    @SchedulerLock(name=URL_DELETE_LOCK_NAME, lockAtMostFor = "15m")
    @Scheduled(cron="${schedulers.url-delete}", zone ="Europe/Moscow")
    public void deleteUrl(){
        System.out.println("Deleted");
        try{
            List<String> ids = urlService.getAllNotUpdatedFor();
            System.out.println("SIZE " + ids.size());
            urlDeleteProducer.sendMessages(ids);
        }
        catch (Exception ex){
            System.out.println("ERROR!");
            System.out.println(ex.getMessage());
        }
    }
}
