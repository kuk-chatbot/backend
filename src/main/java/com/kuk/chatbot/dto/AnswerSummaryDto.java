package com.kuk.chatbot.dto;

import lombok.*;

@NoArgsConstructor
@Data
public class AnswerSummaryDto {
    private String name;
    private String cause;
    private String modelName;
    private int cpuFanNoScrews;
    private int cpuFanPortDetached;
    private int cpuFanScrewsLoose;
    private int incorrectScrews;
    private int looseScrews;
    private int noScrews;
    private int scratch;
    private int id;

    public AnswerSummaryDto(String name, String cause, String modelName, int cpuFanNoScrews, int cpuFanPortDetached, int cpuFanScrewsLoose, int incorrectScrews, int looseScrews, int noScrews, int scratch, int id){
        this.name = name;
        this.cause = cause;
        this.modelName = modelName;
        this.cpuFanNoScrews = cpuFanNoScrews;
        this.cpuFanPortDetached = cpuFanPortDetached;
        this.cpuFanScrewsLoose = cpuFanScrewsLoose;
        this.incorrectScrews = incorrectScrews;
        this.looseScrews = looseScrews;
        this.noScrews = noScrews;
        this.scratch = scratch;
        this.id = id;
    }
}
