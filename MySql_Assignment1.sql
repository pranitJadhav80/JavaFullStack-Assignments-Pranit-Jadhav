# 1. all employee data in it department
use companydb;
select e.employeeid, e.firstname, e.lastname, d.departmentname 
from employees e join departments d 
on e.departmentid = d.departmentid 
where d.departmentname = 'it';

#2. employees hired after 2010
SELECT e.firstname, e.lastname, e.hiredate
from employees e where e.hiredate > '2010-12-31';

#3. projects budget exceeding 80000
select projectname, budget from projects
where budget > 80000;

#4. sort employees by their hire date
select employeeid, firstname, lastname, hiredate
from employees 
order by hiredate desc;

#5. projects sorted by their budget in asc order
select * from projects
order by budget asc;

#6. count no. employees in each department
select d.departmentName, count(e.employeeid) as empcount
from departments d 
left join employees e
on d.departmentid = e.departmentid
group by d.departmentname;

#7. display top 3 employees with highest base salary
select e.firstname, e.lastname, s.basesalary
from employees e
left join salaries s
on e.employeeid = s.employeeid
order by basesalary desc limit 3;

#8. retrieve employees names along with their department names
select e.firstname, e.lastname, d.departmentname
from employees e 
join departments d 
on e.departmentid = d.departmentid

# 9. list all assingments including employee and project details
select e.firstname, e.lastname, p.projectname, a.hoursworked
from employees e
left join assignments a
on e.employeeid = a.employeeid
left join projects p
on a.projectid = p.projectid

#10. employees working on project with highest budget
select e.firstname, e.lastname, p.projectname, p.budget
from employees e 
join assignments a
on e.employeeid = a.employeeid
join projects p 
on p.projectid = a.projectid
order by budget desc

#11. calculate age of each employee
select employeeid, firstname, lastname, dateofbirth,
floor(datediff(curdate(), dateofbirth)/365) as age
from employees

#12. find all employees hired in 2015
select employeeid, firstname, lastname, hiredate
from employees
where year(hiredate) = 2015;

#13. names of projects ending before decembe 2013
select projectid, projectname, enddate
from projects
where enddate <= "2023-12-31";

# 14. list employees with base salaeies greater than 70000
select e.firstname, e.lastname, s.basesalary
from employees e 
join salaries s 
on e.employeeid = s.employeeid
where s.basesalary > 70000
order by basesalary desc;

#15. count no. of projects handles by each employee
select e.employeeid, e.firstname, e.lastname, count(a.projectid) as projectcount
from employees e 
left join assignments a 
on e.employeeid = a.employeeid
group by e.employeeid, e.firstname, e.lastname

#16. list all departments located in san fransisco
select departmentid, departmentname, location
from departments
where location = 'san francisco';

#17. display projects names along with total hours worked on it
select p.projectname, sum(a.hoursworked) as totalhoursworked
from projects p
join assignments a 
on p.projectid = a.projectid
group by p.projectname
order by totalhoursworked desc

#18. find highest bonus received by an employee
select max(bonus) as highestbonus from salaries;

#19. identify projects that lasted for more than 12 months
select projectid, projectname, startdate, enddate
from projects
where datediff(enddate, startdate) > 365;

#20. retrieve all projects starting in 2023
select projectid, projectname, startdate, enddate
from projects
where year(startdate) = 2023;

#21. calculate total hours worked by each employee across all projects
select e.employeeid, e.firstname, e.lastname, sum(a.hoursworked) as totalhoursworked
from employees e 
left join assignments a
on e.EmployeeID = a.employeeid
group by e.employeeid, e.firstname, e.lastname

#22. count department with most employees
select d.departmentname, count(e.employeeid) as employeecount
from departments d 
join employees e 
on d.departmentid = e.DepartmentID
group by d.departmentname
order by employeecount desc limit 5;

#23. list employees who were born before 1985
select employeeid, firstname, lastname, dateofbirth
from employees
where year(dateofbirth) < 1985;