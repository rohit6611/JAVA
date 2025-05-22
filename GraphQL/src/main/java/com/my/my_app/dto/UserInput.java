package com.my.my_app.dto;

import lombok.AllArgsConstructor;
import com.my.my_app.model.Address;
import com.my.my_app.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInput {
    private String id;
    private String name;
    private int age;
    private AddressInput address;
    private List<PostInput> posts;
}
