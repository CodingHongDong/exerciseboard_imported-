package com.hong.running.vo;

import java.util.Date;

import lombok.Data;

@Data
public class RunningBoardVO {
	
	private long no;
	private String title;
	private String content;
	private Date regDate;
	private int hit;
	private String memberId;
	
}
