package com.bn.mapper;

import java.util.List;

import com.bn.model.PlanVo;

public interface PlanMapper {
	public int insert(PlanVo vo);//plan����
	
	public List<PlanVo> selectAll();//ȸ�����̵��� ��� �÷� �ҷ�����
	
	public PlanVo selectByPlanid(int p_id);//plan��ȣ�� �÷��ҷ�����
}
