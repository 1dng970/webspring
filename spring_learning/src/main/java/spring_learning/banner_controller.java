package spring_learning;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class banner_controller {

   List<String> listdata = null; //DAO�迭
   Map<String, String> mapdata = null; //DTO
   PrintWriter pw = null; //���
   String result = null; //�����
   int callback = 0;
   ModelAndView mv = null; //��
   
   @Resource(name="banner_DTO")
   banner_DTO dto; //new�� ��������
   
   @Resource(name="banner_DAO")
   banner_DAO dao;
   
   @Resource(name="file_rename")
   file_rename fname; 
   
   //field�� �ִ� dto�� �Ű������� �ִ� dto�� �ٸ� ���¸� ������ �ֽ��ϴ�. 
   //this.dto -> field�� �ִ� dto ��Ī, dto -> �Ű������� �ִ� dto
   //ModelAttribute: 1:1��Ī -> name�� DTO �ڷ��� ������ ������ ������ ������ ���� Setter�ߵ�
   
   @PostMapping("/banner/bannerok")
//   public String bannerok(@RequestParam(name="dto", required=false) banner_DTO dto){
   public String bannerok(@ModelAttribute(name="dto") banner_DTO dto, 
         MultipartFile bfile, HttpServletRequest req) throws Exception {   
//   System.out.println("b.name");
   System.out.println(dto.getBname());
//   System.out.println(bfile.getOriginalFilename());
      
      String file_new = null;
      
      if(bfile.getSize() > 0) { //���� �뷮���� üũ
//      if(bfile.getOriginalFilename()!="") { //���ϸ����� üũ
      
         file_new = this.fname.rename(bfile.getOriginalFilename());
         //�� ���丮 �����ڰ� ������ ���ϸ����� �����ϴ� �ڵ�
         String url = req.getServletContext().getRealPath("/upload/");
         FileCopyUtils.copy(bfile.getBytes(), new File(url+file_new));
         System.out.println(url);
         
         dto.setFile_url("/upload/" + file_new); //�� ���丮 ��� �� ���ϸ�
         dto.setFile_new(file_new); //�����ڰ� ���ϴ� ���·� ���ϸ��� ����
         dto.setFile_ori(bfile.getOriginalFilename()); //����ڰ� ������ ���ϸ�
     System.out.println(dto.getFile_url());
      }
      this.callback = this.dao.new_banner(dto);
      System.out.println(this.callback);
   return null;
   }
   
   @GetMapping("/banner/bannerlist")
   public String bannerlist(Model m) {
      List<banner_DTO> all = this.dao.all_banner();
      m.addAttribute("all", all);
      return null;
   }
}
