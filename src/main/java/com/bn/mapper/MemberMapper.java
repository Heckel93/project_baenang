package com.bn.mapper;

import com.bn.model.MemberVo;

public interface MemberMapper {
	public void joinS(MemberVo member) throws Exception; //ȸ������
	public MemberVo loginS(String m_email); //�α���
	public String findId(MemberVo member); //���̵� ã��
	public void findPwd(MemberVo member); //��й�ȣã��
	public int checkLogin(MemberVo member) throws Exception;
}