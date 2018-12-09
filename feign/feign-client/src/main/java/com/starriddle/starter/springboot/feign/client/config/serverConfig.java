package com.starriddle.starter.springboot.feign.client.config;

import com.starriddle.starter.springboot.feign.client.feign.EmpApi;
import com.starriddle.starter.springboot.feign.client.feign.ServerApi;
import com.starriddle.starter.springboot.feign.client.feign.TestApi;
import com.starriddle.starter.springboot.feign.client.feign.UserApi;
import com.starriddle.starter.springboot.feign.client.util.MyJacksonDecoder;
import com.starriddle.starter.springboot.feign.client.util.MyJacksonEncoder;

import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 可以对Feign设置 与服务端通讯 所用的编解码器
 * 使用默认编解码器，只支持String和byte[]的编解码，不支持其他类型
 * 如使用Gson，不支持String，LocalDate
 *
 * @author CYL
 * @date 2018-04-25
 */
@Configuration
@EnableAutoConfiguration
public class serverConfig {

    @Bean
    public TestApi registTestApi() {
        return Feign.builder()
                .encoder(new MyJacksonEncoder()) // 使用自定义，继承自Feign封装的Jackson
                .decoder(new MyJacksonDecoder())
//                .encoder(new GsonEncoder()) // 使用Gson，但springMVC首选jackson，需要注意
//                .decoder(new GsonDecoder())
//                .encoder(new JacksonEncoder()) // 使用jackson，与springMVC保持一致
//                .decoder(new JacksonDecoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(TestApi.class, "http://localhost:9001");
    }

    @Bean
    public ServerApi registServerApi() {
        return Feign.builder()
//                .encoder(new GsonEncoder())
//                .decoder(new GsonDecoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(ServerApi.class, "http://localhost:9001");
    }

    @Bean
    public UserApi registUserApi() {
        return Feign.builder()
//                .encoder(new GsonEncoder())
//                .decoder(new GsonDecoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(UserApi.class, "http://localhost:9001");
    }

    @Bean
    public EmpApi registEmpApi() {
        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(EmpApi.class, "http://localhost:9001");
    }

}
