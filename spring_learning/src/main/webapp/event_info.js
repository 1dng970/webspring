function wordck() {
    //var w = "010123-45678"; // 전화번호 문자열
    var w = "01012345678";


    // let ck = /[0-9]/; // 숫자 찾기
    //let ck = /^[a-zＡ－Ｚ]/; // 영문 소문자 찾기
    //console.log(ck.test(w)); // true ,false (숫자만 있어서 소문자 없음)  

    //let ck = /[0-9]/; //0~9 숫자 외에 단어 
    //console.log(w.match(ck)); //배열 형태의 값을 출력

    //let ck = /[0-9]/g; 
    //let ck = /^[0-9]/; //0~9 숫자 외에 단어 
      //console.log(w.match(ck));

      //$ :해당 패턴에 문자열을 대입하여 체크하는 방식
     let ck = /^\d{2,3}\d{3,4}\d{4}$/; //ture(010-1234-5678),false(01012345678)
     console.log(ck.test(w));  //배열 형태의 값을 출력
}


function eventok(){
	if(f.ename.value==""){
		alert("고객명을 입력하세요!");
	}
	else if(f.etel.value==""){
		alert("연락처를  입력하세요!");		
    }
	else if(f.email.value==""){
		alert("이메일을 입력하세요!");
	}
	else if(f.ememo.value==""){
		alert("이벤트 참여 이유를 입력하세요");	
			
	}
	else if(f.info1.checked ==false){
		alert("개인정보활용에 동의하셔야만 이벤트 참여가 됩니다.");
		
	}
	else if(f.info2.checked ==false){
		alert("제3자에 정보제공에 동의하셔야만 이벤트 참여가 됩니다");	
	}
	else{
		//정규식 코드 사툥(연락처 확인)
		let ck = /^\d{2,3}\d{3,4}\d{4}$/;//숫자 외에 다른 문자일 경우 false 
		if(ck.test(f.etel.value)==false){
			alert("전화번호를 정상적으로 입력하세요");
			
		}
		else{//정규식 코드 (true)
			f.submit();
		}
		
	}		
}
	

