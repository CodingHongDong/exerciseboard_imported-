package com.hong.fitness.vo;

import java.util.Date;

import lombok.Data;

@Data
public class FitnessBoardReplyVO {

	private int rno;
	private long no;
	private String writer;
	private String content;
	private Date regDate;
	
}
