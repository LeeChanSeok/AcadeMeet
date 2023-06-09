package com.ssafy.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.List;

@Getter
@Setter
@ApiModel("MeetEndPostRequest")
public class MeetEndReq {


    @ApiModelProperty(name="stt")
    @JsonProperty("stt")
    List<SttDetailReq> stt;
    @ApiModelProperty(name="video")
    @JsonProperty("video")
    String video;
    @ApiModelProperty(name="endtime")
    @JsonProperty("endtime")
    Time endtime;


}
