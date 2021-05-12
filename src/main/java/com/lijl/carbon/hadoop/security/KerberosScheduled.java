package com.lijl.carbon.hadoop.security;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author Lijl
 * @ClassName KerberosScheduled
 * @Description TODO
 * @Date 2021/5/10 10:26
 * @Version 1.0
 */
@Component
public class KerberosScheduled {

    @Scheduled(cron = "0 0 0 * * ?")
    public void cronJob(){
        try {
            KerberosAuth.authKerberos();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
