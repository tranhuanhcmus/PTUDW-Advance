package com.example.chat.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
//INFO: @AllArgsConstructor generates a constructor with 1 parameter for each field in your class.
@AllArgsConstructor
//INFO: @NoArgsConstructor generates a no arguments constructor.
@NoArgsConstructor
//INFO: @Builder lets you automatically produce the code required to have your class be instantiable with code such as:
//      MyClass myClass = MyClass.builder().name("John").age(20).build();

@Builder
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType type;
}
