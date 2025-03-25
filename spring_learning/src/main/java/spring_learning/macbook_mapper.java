package spring_learning;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

// @Mapper : mapper.xml�� �����ϴ� interface �Դϴ�.
// ** mapper.xml���� ����ϴ� id �������� �޼ҵ� �̸��� �����ϰ� �˴ϴ�.

@Mapper
public interface macbook_mapper {

	public int macbook_update(macbook_DTO dto);	// ������ ����(DAO)
	public int macbook_insert(macbook_DTO dto);	//	�ű� ������ �Է�
	List<macbook_DTO> macbook_select();	// ��ü ������
	macbook_DTO macbook_one(String midx);	// �ϳ��� �����͸� ������
	public int macbook_delete(int midx); //����ó�� (���ڰ�)	
}