package com.springbootblog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor @NoArgsConstructor
public class ErrorDetails {
    private Date stampTime;
    private String message;
    private String details;
}
