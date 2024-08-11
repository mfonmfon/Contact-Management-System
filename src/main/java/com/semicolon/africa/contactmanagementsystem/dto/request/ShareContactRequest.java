package com.semicolon.africa.contactmanagementsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShareContactRequest {
    private String receiverId;
    private String senderId;

}
