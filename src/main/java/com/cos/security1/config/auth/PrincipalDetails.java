package com.cos.security1.config.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다. (Security ContextHolder)
// 오브젝트 => Authentication 타입 객체
// Authentication 안에 User 정보가 있어야 됨
// User 오브젝트 타입 => UserDetails 타입 객체
public class PrincipalDetails {
}
