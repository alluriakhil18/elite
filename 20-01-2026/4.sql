/*
Write a SQL query to list all departments and their employee counts, including 
departments with no employees, using RIGHT JOIN.


Tables:
------
salgrade ==> grade int(4) primary key, losal decimal(10,2),  hisal decimal(10,2) 


dept==>   deptno int(2) primary key, dname varchar(50) not null, location varchar(50) not null

emp ==>   empno int(4) primary key, ename varchar(50) not null,
          job varchar(50) not null,  mgr int(4),  hiredate date,
          sal decimal(10,2),  comm decimal(10,2),  deptno int(2)

OUTPUT:
+--------+------------+-----------+
| deptno | dname      | emp_count |
+--------+------------+-----------+
|     10 | Accounting |         3 |
|     20 | Research   |         6 |
|     50 | Finance    |         0 |
+--------+------------+-----------+

*/
use fs;
SELECT d.deptno,d.dname,COUNT(e.empno) AS emp_count FROM emp e
RIGHT JOIN dept d
ON e.deptno = d.deptno
GROUP BY d.deptno,d.dname
ORDER by d.deptno;