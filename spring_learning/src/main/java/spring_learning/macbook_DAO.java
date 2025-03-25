package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//DAO(Mybatis), DTO(setter, getter), VO(getter)
//DAO : �����͸� Access�� �ϴ� ����

@Repository("macbook_DAO")
public class macbook_DAO implements macbook_mapper{//�������̵� ���
	
	// Mybatis => DB ����
	@Resource(name="template")
	public SqlSessionTemplate st;
	
	@Override //@Repository: Model�� Controller�� ȣ��
	public int macbook_delete(int midx) { //@mapper�� interpace�� �ε��Ͽ�  DAO �ۼ�
	int result = this.st.delete("macbook_delete",midx);		
    return result;
	}
	
	
	// ������ ���� �޼ҵ�
	@Override
	public int macbook_update(macbook_DTO dto) {
		int result = this.st.update("macbook_update", dto);
		return result;
	}
	
	// �ϳ��� �����͸� �������� �޼ҵ�
	@Override
	public macbook_DTO macbook_one(String midx){
		// setter ���·� DB�� �ִ� ������ �̰�
		// selectOne("mapper.xml���� ����ϴ� id��", �Ű�����)
		macbook_DTO onedata = this.st.selectOne("macbook_one", midx);
		return onedata;
	}
	@Override
	public List<macbook_DTO> macbook_select() {
		// selectOne : ������ �� ���� ������ �� (List �迭, ArrayList �迭, DTO)
		// selectList : �����͸� ���� ���� ������ �� (List �迭�� ������)
		List<macbook_DTO> classList = this.st.selectList("macbook_select");
		return classList;
	}
	@Override
	public int macbook_insert(macbook_DTO dto){
		int result = this.st.insert("macbook_insert", dto);
		return 0;
	}
}