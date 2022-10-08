package com.hong.pilates.vo;

import java.util.Date;

import lombok.Data;

@Data
public class PilatesBoardVO {

	private long no;
	private String title;
	private String content;
	private Date regDate;
	private int hit;
	private String memberId;
	
}
