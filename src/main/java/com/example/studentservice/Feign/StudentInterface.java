package com.example.studentservice.Feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("STUDENT-SERVICE")
public interface  StudentInterface {

}
