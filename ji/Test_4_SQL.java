package kr.co.programmers;

/**
 * 100
 * 데이터 리스트에서 부서명과 이름
 * 조건: 부서별 최대 연봉자 출력
 * 
 * */

//select employees.brunch_id, employees.name
//from employees
//where (salary, name) in (
//	select max(salary), name
//	from employees
//	group by brunch_id
//)
//order by brunch_id asc, name asc
