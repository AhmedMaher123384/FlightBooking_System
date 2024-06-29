package com.api.Booking.Proxy;

 import com.api.Booking.Dto.UserResponse;
 import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserServiceFeignClient {
    @GetMapping("/users/{id}")
    UserResponse getUserById(@PathVariable Long id);
}