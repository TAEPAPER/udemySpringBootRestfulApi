package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.function.Predicate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * 
 * public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id); 
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
    UserDaoService에서의 fineOne에서 끝에 orElse(null)처리로 인해 회원정보가 없음에도 불구하고 
    서버는 200response 를 뱉는다.
    
    >> 제대로 된 응답을 해줘야한다.
    >> UserNotFoundException 생성
    >> 하지만 500에러 뱉음
    >> @ResponseStatus(code = HttpStatus.NOT_FOUND) 처리로 404 뱉게 
    

 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String message) {
		//message를 부모에게 전달(RuntimeException)
		super(message);
	}

}
