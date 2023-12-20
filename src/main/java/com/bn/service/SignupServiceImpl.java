package com.bn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.mapper.SignupMapper;
import com.bn.model.MemberVo;

@Service
public class SignupServiceImpl implements SignupService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SignupMapper signupMapper;
	
	@Override
    public void signup(MemberVo member) {
        try {
            String encryptedPassword = passwordEncoder.encrypt(member.getM_EMAIL(),member.getM_PWD()); // ��й�ȣ�� �ؽ��մϴ�.
            member.setM_PWD(encryptedPassword); // �ؽ̵� ��й�ȣ�� �����մϴ�.
            signupMapper.signup(member);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public boolean validateSignup(MemberVo member) {
		// �̸���, �г���, ��й�ȣ ��ȿ�� �˻�
        if (member.getM_EMAIL() == null || member.getM_EMAIL().isEmpty()) {
            return false; // �̸����� ��� ������ ��ȿ�� �˻� ����
        }

        if (member.getM_NNAME() == null || member.getM_NNAME().isEmpty()) {
            return false; // �г����� ��� ������ ��ȿ�� �˻� ����
        }

        if (member.getM_PWD() == null || member.getM_PWD().isEmpty()) {
            return false; // ��й�ȣ�� ��� ������ ��ȿ�� �˻� ����
        }

        if (member.getM_BIRTH() == null) {
            return false; // ��������� �ΰ��̶�� ��ȿ�� �˻� ����
        }
        
        if (member.getM_GEN() == null || member.getM_GEN().isEmpty()) {
            return false; // ������� ��� ������ ��ȿ�� �˻� ����
        }
		return true;
	}

	
}
