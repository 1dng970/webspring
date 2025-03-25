package spring_learning;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class file_DTO {
	MultipartFile[] mfile;
}
