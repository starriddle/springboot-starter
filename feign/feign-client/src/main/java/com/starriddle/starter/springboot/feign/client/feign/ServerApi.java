package com.starriddle.starter.springboot.feign.client.feign;

import feign.Headers;
import feign.RequestLine;

/**
 *
 * 使用application/json、application/xml等content-type，
 * 可以在POST中使用Body参数
 * 使用默认的设置，即http协议默认的application/x-www-form-urlencoded时，
 * 不可使用Body参数，一律需要@Param注释
 *
 * @author CYL
 * @date 2018-04-25
 */
 @Headers({"Content-Type: application/json", "Accept: application/json"})
public interface ServerApi {

    /**
     * 不与springcloud集成，只能使用Feign自己的@RequestLine注释
     *
     * @return
     */
    @RequestLine("GET /server/index")
    String getIndex();

    @RequestLine("POST /server/index")
    String postIndex();

    @RequestLine("GET /server/hello")
    String getHello();

    @RequestLine("POST /server/hello")
    String postHello();

}
