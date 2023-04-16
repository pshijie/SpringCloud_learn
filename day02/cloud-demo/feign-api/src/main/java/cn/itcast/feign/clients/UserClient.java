package cn.itcast.feign.clients;

import cn.itcast.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author psj
 * @date 2023/3/25 20:08
 * @File: UserClient.java
 * @Software: IntelliJ IDEA
 */
//@FeignClient(value = "userservice", configuration = DefaultFeignConfiguration.class)  // Feign配置局部生效
@FeignClient(value = "userservice")
public interface UserClient {
    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long id);
}
