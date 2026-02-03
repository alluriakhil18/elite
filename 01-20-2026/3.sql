/*
Write a SQL query to show the total salary per department where the total salary 
exceeds 5000, ordered by department number.


Tables:
------
salgrade ==> grade int(4) primary key, losal decimal(10,2),  hisal decimal(10,2) 


dept==>   deptno int(2) primary key, dname varchar(50) not null, location varchar(50) not null

emp ==>   empno int(4) primary key, ename varchar(50) not null,
          job varchar(50) not null,  mgr int(4),  hiredate date,
          sal decimal(10,2),  comm decimal(10,2),  deptno int(2)


OUTPUT:
+--------+----------+
| deptno | SUM(sal) |
+--------+----------+
|     10 |  5350.00 |
|     20 | 11825.00 |
+--------+----------+

*/
use fs;
SELECT deptno,SUM(sal) from emp
GROUP BY deptno
HAVING SUM(sal)>5000
ORDER BY deptno