package com.bn.model;

import java.util.Date;

import lombok.Data;

@Data
public class DtailPlanVo {

	int dp_id;//������
	int p_id;//��Ʈ�ѷ����� ��ȸ���� �ܾ���°�(������)
	String contentid;//div��������
	int dp_day;//�����Ҷ� �θ� div�� ���̵�
	Date dp_start;//session�����ܱ�
	Date dp_end;//session���� �޾ƿ���
	int dp_num;//�θ�div�� �ε���
}
