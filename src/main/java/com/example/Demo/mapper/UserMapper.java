//package com.example.Demo.mapper;
//
//import com.example.Demo.dto.request.UserCreationRequest;
//import com.example.Demo.dto.request.UserUpdateRequest;
//import com.example.Demo.dto.response.UserResponse;
//import com.example.Demo.entity.User;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.MappingTarget;
//
//
//@Mapper(componentModel = "spring")
//public interface UserMapper {
//    User toUser(UserCreationRequest request);
//
//    UserResponse toUserResponse(User user);
//
//    void updateUser(@MappingTarget User user, UserUpdateRequest request);
//}
