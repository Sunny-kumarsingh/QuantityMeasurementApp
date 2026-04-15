package com.app.quantitymeasurement.client;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.quantitymeasurement.dto.SaveHistoryRequest;

@FeignClient(name = "user-service", fallbackFactory = UserServiceClientFallbackFactory.class)
public interface UserServiceClient {

    @PostMapping("/api/history/save")
    void saveHistory(@RequestBody SaveHistoryRequest request);
}

@Component
class UserServiceClientFallbackFactory implements FallbackFactory<UserServiceClient> {
    @Override
    public UserServiceClient create(Throwable cause) {
        return new UserServiceClient() {
            @Override
            public void saveHistory(SaveHistoryRequest request) {
                System.out.println("WARN: user-service is unavailable. History not saved. " + cause.getMessage());
            }
        };
    }
}
