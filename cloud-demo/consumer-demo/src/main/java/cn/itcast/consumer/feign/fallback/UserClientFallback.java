package cn.itcast.consumer.feign.fallback;

import cn.itcast.consumer.feign.UserClient;
import cn.itcast.consumer.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public User queryById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("用户查询出现异常!");
        return user;
    }
}
