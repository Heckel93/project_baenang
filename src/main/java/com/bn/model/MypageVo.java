package com.bn.model;

import java.util.Date;
import lombok.Data;

@Data
public class MypageVo {
	
	private int p_id;//�÷�id
	private String p_name;//�÷��̸�
	private String p_birth;//�÷�������
	private Date p_moddate;//�÷�������
	
	private Date dp_start;//���������
	private Date dp_end;//����������
	
	private long m_id;//ȸ��id
	private String m_email; //�̸���
	private String m_pwd; //��й�ȣ
	private String m_gen; //����
	private String m_nname; //�г���
	private Date m_birth; //�������
	private Date m_rdate;//������
	private String m_image; //�������̹��� ���
	private int m_status; //ȸ������
	private int m_profil; //����� ������
	
	private long i_id;//�ʴ�id
	private int i_per;//����
	private int i_status;//�ʴ����
}
