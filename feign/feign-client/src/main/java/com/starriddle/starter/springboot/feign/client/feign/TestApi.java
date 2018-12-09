package com.starriddle.starter.springboot.feign.client.feign;

import com.starriddle.starter.springboot.feign.client.entity.User;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Feign客户端使用@Header注释content-type和Accept，既可以定义在类上，也可以定义在方法上，作用范围不同
 * 默认@Headers类型为 application/x-www-form-urlencoded，只能使用@Param注释，不能使用Body参数
 *
 * "Content-Type: application/json":
 *      将POST方式的Body参数转换为json发送，拼到url的@Param参数不转换
 *
 * 1.GET方式：
 *      客户端feign所有参数都必须是@Param参数，同时必须拼到url上
 *      服务端不能使用@RequestBody参数接受，只能使用无注解参数 / @RequestParam参数
 *      适用类型：基本数据类型和封装类，以及数组，和List/Map等，
 *          注：集合类如List/Map等，服务端必须使用@RequestParam参数接收
 *
 * 2. POST方式：
 *      客户端Feign可以使用最多一个Body参数（无注解参数），
 *      服务端只能使用@RequestBody参数接收对应Body参数
 *      Body参数可以是任何类型，只要使用的json编码解码工具支持
 *          注：需要注意json编解码器的String/LocalDate的支持情况（通过自定义编解码器解决）
 *              json编码encoder要求类具有toString方法，解码decoder要求类具有构造器方法
 *      其他参数（只能是基本类型）可以使用@Param参数，规则同GET方式
 *      一旦feign接口中存在Body参数，必定会使用POST方法发送请求，即使@RequestLine定义了GET方式
 *
 * "Accept: application/json":
 *      将接受的返回值一律视作json处理
 *          注：注意特殊类型的转换问题，如String，LocalDate等
 *
 *
 * @author CYL
 * @date 2018-04-27
 */
@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface TestApi {

    // ----------------- 基本数据类型 start -------------------
    // 基本数据类型随便用
    /**
     * boolean/int/long等以及封装类使用@Param参数，
     * 服务端即可使用无注解参数，也可使用@RequestParam参数接收
     *
     * @param number
     * @return
     */
    @RequestLine("GET /test/int?i={num}")
    int testInt1(@Param("num") int number); // 测试基本类型

    /**
     * 原则上，任何类型都可使用Body参数，
     *      除了String，LocalDate等特殊类型
     * @param number
     * @return
     */
    @RequestLine("GET /test/int")
    int testInt2(int number); //使用body参数，注释中GET被忽略，使用POST

    /**
     * json与String的冲突
     *
     * String使用@Param参数
     *      直接将String拼到url上，不进行json转换，空格以+代替
     * 服务端使用无注解参数/@RequestParam参数，接受正常，直接读取String
     *
     * String使用Body参数
     * 服务端接收不正常，在源字符串外多了一对双引号，
     *      如 a"b"c -> java表示："a\"b\"c" -> 服务端接收后打印："a\"b\"c", 期望为a"b"c
     *
     *      推测： 客户端将String转成json，外面套了一对双引号
     *             服务端读取时直接将json作为String
     *
     * String作为返回类型
     * 客户端作为json接收，转换成String时出错
     *
     *      推测：服务器直接返回String，未将之转换为json（没在String外围套一对双引号）
     *              客户端认为返回的是json，进行json转String操作（json以双引号开头和结尾），结果出错
     *
     * json 默认不支持String，需要设置，或自定义json转换规则，即自定义编解码器来解决不受支持的类型fangshi
     * @param string
     * @return
     */
    @RequestLine("GET /test/string?s={str}")
    String testString1(@Param("str") String string); // 测试传输/返回String

    @RequestLine("POST /test/string")
    String testString2(String string); // 测试传输/返回String

    /**
     * 数组参数使用@Param传输
     * 服务端即可以使用无注释参数接收，也可以使用@RequestParam注释参数
     *      数组的toString就是Object的toString，为内存地址
     * @param date
     * @return
     */
    @RequestLine("GET /test/array?array={date}")
    String[] testArray1(@Param("date") String[] date); // 测试传输/返回 数组

    @RequestLine("POST /test/array")
    String[] testArray2(String[] date); // 测试传输/返回 数组

    // --------------------- 基本数据类型 end -----------------------

    // --------------------Conllection start-------------------
    // feign使用@Param参数时，服务端必须使用@RequestParam

    /**
     * List参数使用@Param传输，
     * 服务端controller必须使用@RequestParam注释对应参数
     * 如使用无注释参数，服务端报错List没有(init)方法 <-- 无法获取json，但又没有init方法
     * @param l
     * @return
     */
    @RequestLine("GET /test/list?list={l}")
    List<String> testList1(@Param("l") List<String> l); // 测试传输/返回 List

    @RequestLine("POST /test/list")
    List<String> testList2(List<String> l); // 测试传输/返回 List

    /**
     * Map参数使用@Param传输
     * 服务端controller必须使用@RequestParam注释对应参数
     * 服务端使用无注释参数，无法获取json，得到(init)方法生成的空对象
     *      List没有默认构造器
     * @param m
     * @return
     */
    @RequestLine("GET /test/map?map={m}")
    Map<String,Long> testMap1(@Param("m") Map<String, Long> m); // 测试传输/返回 Map

    @RequestLine("POST /test/map")
    Map<String, Long> testMap2(Map<String,Long> m); // 测试传输/返回 Map

    // -------------- Clollection end ----------------

    // --------------非基本类型 start ----------------
    // 常用类型Date，Time，BigDecimal等也都可以随便用，同基本类型，推测jackson存在对应常用类型的转换器
    // 1.8新出现的LocalDate类型，目前还不支持，可以自定义

    @RequestLine("GET /test/date?date={d}")
    Date testDate1(@Param("d") Date da);

    @RequestLine("POST /test/date")
    Date testDate2(Date da);

    @RequestLine("GET /test/time?time={d}")
    Time testTime1(@Param("d") Time da);

    @RequestLine("POST /test/time")
    Time testTime2(Time da);

    @RequestLine("GET /test/bigdecimal?num={b}")
    BigDecimal testBigDecimal1(@Param("b") BigDecimal bigDecimal);

    @RequestLine("POST /test/bigdecimal")
    BigDecimal testBigDecimal2(BigDecimal bigDecimal);

    // -------------- LocalDate start ----------------
    // Gson/Jackson均不支持LocalDate解码，需要自定义才可以

    /**
     * LocalDate使用@Param传输
     * 服务端使用@RequestParam注释参数，接收字符串yyyy-MM-dd，但报错：无法将String转换为LocalDate
     * 服务端使用无注释参数，报错：LocalDate没有init方法
     * @param date
     * @return
     */
    @RequestLine("GET /test/localdate?date={date}")
    LocalDate testLocalDate1(@Param("date") LocalDate date); // 测试传输/返回 LocalDate

    /**
     * LocalDate使用Body参数
     * 服务端接收到inputstream内容，期望为数组/String，但收到Object，无法将其转换为LocalDate,
     *      （推测：就算接收到String，因为LocalDate没有对应构造器，也会无法转换）
     *
     * 自定义客户端编码器后，传输LocalDate.toString，即yyyy-MM-dd，服务端收到String，但无法正常转换
     *
     * @param date
     * @return
     */
    @RequestLine("POST /test/localdate")
    LocalDate testLocalDate2(LocalDate date); // 测试传输/返回 LocalDate

    /**
     * LocalDate作为返回类型
     * 客户端Gson期望接收到Object，但实际收到String，无法转换LocalDate
     * 客户端Jackson接收到String，但LocalDate没有对应构造器，无法转换
     * 客户端自定义解码器，转换成功
     * @return
     */
    @RequestLine("GET /test/localdate_")
    LocalDate testLocalDate3(); // 测试返回 LocalDate

    // ------------------LocalDate end-----------------

    // ------------------ 自定义类型 start ------------
    // 非基本类型只能使用Body参数传输

    /**
     * 使用@Param参数传输User，
     * 服务端使用无注释参数，无法获取json，得到init方法生成的新对象，即new User()
     * 服务端使用@RequestParam参数，可以获得json，但转换报错：无法将String转换为User
     *
     * 返回类型没问题，客户端可以正常获取+解析
     * @param user
     * @return
     */
    @RequestLine("GET /test/user?user={user}")
    User testUser1(@Param("user") User user); // 测试传输/返回 User

    /**
     * Body参数服务端可以正常获取+解析json
     * 返回对象客户端可以正常获取+解析json
     * @param d
     * @return
     */
    @RequestLine("POST /test/user")
    User testUser2(User d); // 测试传输/返回 User

    // ------------------ 自定义类型 end ------------

}
