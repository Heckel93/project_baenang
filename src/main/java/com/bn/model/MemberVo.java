package com.bn.model;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberVo {

	private long M_id;//ȸ�� ID
	private String M_email; //�̸���
	private String M_PWD; //��й�ȣ
	private String M_GEN; //����
	private String M_NNAME; //�г���
	private Date M_BIRTH; //�������
	private Date M_RDATE;//������
	private String M_IMAGE; //�������̹��� ���
	private int M_STATUS; //ȸ������
	private int M_PROFIL; //����� ������
	
	
}
