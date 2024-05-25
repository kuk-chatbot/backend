package com.kuk.chatbot.dto;

public class UserEnterpriseDto {
    private Integer userlimit;
    private Integer memory;
    private Integer cores;
    private Integer sockets;

    // Getters and setters
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
