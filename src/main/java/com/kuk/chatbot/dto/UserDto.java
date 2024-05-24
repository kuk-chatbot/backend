package com.kuk.chatbot.dto;

import com.kuk.chatbot.model.RoleType;

public class UserDto {
    private Integer id;
    private String username;
    private String name;
    private RoleType role;
    private Integer userlimit;
    private Integer memory;
    private Integer cores;
    private Integer sockets;

    // 생성자
    public UserDto(Integer id, String username, String name, RoleType role, Integer userlimit, Integer memory, Integer cores, Integer sockets) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
        this.userlimit = userlimit;
        this.memory = memory;
        this.cores = cores;
        this.sockets = sockets;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public Integer getUserlimit() {
        return userlimit;
    }

    public void setUserlimit(Integer userlimit) {
        this.userlimit = userlimit;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Integer getCores() {
        return cores;
    }

    public void setCores(Integer cores) {
        this.cores = cores;
    }

    public Integer getSockets() {
        return sockets;
    }

    public void setSockets(Integer sockets) {
        this.sockets = sockets;
    }
}