package spring_learning;

import lombok.Getter;
import lombok.Setter;

/*
lombok => pom.xml�� ���̺귯�� @Setter, @Getter ��� ����
��, lombok.jar �̼�ġ�� �޼ҵ尡 ��������� ����
*/

@Setter
@Getter

public class user_DTO {
	String mid, mpass;
	String mname, memail;
}
