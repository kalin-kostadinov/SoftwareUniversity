SELECT first_name, last_name FROM employees
WHERE substring(first_name, 1, 2) = 'Sa';

SELECT first_name, last_name FROM employees
WHERE last_name LIKE '%ei%';

SELECT first_name FROM employees
where department_id IN(3, 10) AND year(hire_date) BETWEEN 1995 AND 2005;

SELECT first_name, last_name FROM employees
WHERE job_title NOT LIKE '%engineer%';

SELECT `name` FROM towns
where char_length(`name`) IN (5, 6)
ORDER BY `name`;

SELECT town_id, `name` FROM towns
where left(`name`, 1) IN ('M', 'K', 'B', 'E')
ORDER BY `name`;

SELECT town_id, `name` FROM towns
where left(`name`, 1) NOT IN ('R', 'B', 'D')
ORDER BY `name`;

CREATE VIEW `v_employees_hired_after_2000` AS
SELECT first_name, last_name FROM employees
where year(hire_date) > 2000;

SELECT * FROM v_employees_hired_after_2000;

select first_name, last_name from employees
where char_length(last_name) = 5;

SELECT country_name, iso_code FROM countries
WHERE country_name LIKE '%A%A%A%'
ORDER BY iso_code;

SELECT p.peak_name AS p, r.river_name as r, lower(concat(`peak_name`, substring(`river_name`, 2))) as `mix`
from peaks as p, rivers as r
where right(`peak_name`, 1) = left(`river_name`, 1)
ORDER BY `mix`; 

SELECT `name`, date_format(`start`, '%Y-%m-%d') AS `start` FROM `games`
WHERE YEAR(`start`) IN (2011, 2012)
LIMIT 50;

SELECT `user_name`, substring(`email`, locate('@', `email`) + 1) AS 'Email Provider' FROM `users`
ORDER BY `Email Provider`, `user_name`;

SELECT user_name, ip_address from users
WHERE ip_address LIKE '___.1%.%.___'
ORDER BY user_name;

SELECT `name`, ( 
CASE 
WHEN hour(`start`) BETWEEN 0 AND 11 THEN 'Morning'
WHEN hour(`start`) BETWEEN 12 AND 17 THEN 'Afternoon'
ELSE 'Evening' 
END)
AS 'Parts of the day'
, (
CASE
WHEN `duration` BETWEEN 0 AND 3 THEN 'Extra Short'
WHEN `duration` BETWEEN 4 AND 6 THEN'Short'
WHEN `duration` BETWEEN 7 AND 10 THEN 'Long'
WHEN `duration` BETWEEN 7 AND 10 THEN 'Long'
ELSE 'Extra Long'
END)
AS 'Duration'

FROM `games`;

SELECT product_name, order_date, 
date_add(order_date, INTERVAL 3 DAY) as 'pay_due', 
date_add(order_date, INTERVAL 1 MONTH) as 'deliver_due'
FROM orders;



