package com.bn.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberVo {

	private long M_ID;//ȸ�� ID
	private String M_EMAIL; //�̸���
	private String M_PWD; //��й�ȣ
	private String M_GEN; //����
	private String M_NNAME; //�г���
	private Date M_BIRTH; //�������
	private Date M_RDATE;//������
	private String M_IMAGE; //�������̹��� ���
	private int M_STATUS; //ȸ������
	private int M_PROFIL; //����� ������
	
}
