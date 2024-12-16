package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	/**
	 * Rest Api version 관리 방법
	 * URI,  Parameter 방식 -> Different URL
	 * Header, Accept 방식  -> Same URL 
	 * 
	 * Same URL일 경우 캐싱 문제 발생
	 * -> 정답은 없음
	 * -> 기업은 일관된 버저닝 관리 방법을 사용할 
	 */
	
	/**
	 * URI 방식
	 * @return
	 */
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Bob Charlie");
	}
	/**
	 * URI 방식
	 * @return
	 */
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	/**
	 * Parameter 방식
	 * @return
	 */
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter() {
		return new PersonV1("Bob Charlie");
	}
	/**
	 * Parameter 방식
	 * @return
	 */
	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getSecondVersionOfPersonRequestParameter() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	/**
	 * Header 방식
	 * @return
	 */
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeader() {
		return new PersonV1("Bob Charlie");
	}
	/**
	 * Header 방식
	 * @return
	 */
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonRequestHeader() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	/**
	 * Accept 방식
	 * @return
	 */
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader() {
		return new PersonV1("Bob Charlie");
	}
	/**
	 * Accept 방식
	 * @return
	 */
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

}
