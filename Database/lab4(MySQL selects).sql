use labor_sql;
SELECT * FROM pc WHERE price <= 800 AND speed >= 500 ORDER BY price DESC;
SELECT * FROM pc WHERE code LIKE CONCAT('%','1','%','1','%');
SELECT Ships.* , displacement FROM Ships, Classes WHERE Ships.class = Classes.class;
SELECT model, price FROM laptop WHERE price > (SELECT MAX(price) FROM pc);
SELECT DISTINCT maker FROM product, laptop WHERE product.model = laptop.model AND laptop.speed <= 500;
SELECT CONCAT('name: ', name, '| class: ', class, '| launched: ', launched) FROM Ships;

SELECT MAX(t1.count) AS max_amount, t1.date FROM (SELECT COUNT(date) AS count, date FROM Pass_in_trip WHERE trip_no = 1100 OR trip_no = 1123 OR trip_no = 1146 OR trip_no = 1181
 OR trip_no = 1187 OR trip_no = 1195 GROUP BY date) as t1;
 
SELECT t1.maker FROM (SELECT DISTINCT maker FROM laptop, product WHERE laptop.model = product.model) AS t1 WHERE t1.maker
 NOT IN(SELECT t2.maker FROM (SELECT maker, speed FROM laptop, product WHERE laptop.model = product.model) AS t2 WHERE speed < 600);

SELECT * FROM Ships, Classes 
WHERE
(CASE numGuns WHEN 8 THEN 1 ELSE 0 END)+
(CASE bore WHEN 15 THEN 1 ELSE 0 END)+
(CASE displacement WHEN 32000 THEN 1 ELSE 0 END)+
(CASE type WHEN 'bb' THEN 1 ELSE 0 END)+
(CASE country WHEN 'USA' THEN 1 ELSE 0 END)+
(CASE launched WHEN 1915 THEN 1 ELSE 0 END)+
(CASE ships.class WHEN 'Kon' THEN 1 ELSE 0 END) >= 4;

SELECT COUNT(name) FROM (SELECT name FROM Ships WHERE class = 'Tennessee') AS t1
 WHERE (SELECT COUNT(name) FROM (SELECT name FROM Ships WHERE class = 'Tennessee') AS t2) <=1;

 

SELECT 
(SELECT DISTINCT class FROM Classes WHERE class = 'Bismarck') AS ship,
(SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Bismarck') AS amount
WHERE (SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Bismarck') BETWEEN 0 AND 2
UNION
SELECT 
(SELECT DISTINCT class FROM Classes WHERE class = 'Iowa') AS ship,
(SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Iowa') AS amount
WHERE (SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Iowa') BETWEEN 0 AND 2
UNION
SELECT 
(SELECT DISTINCT class FROM Classes WHERE class = 'Kon') AS ship,
(SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Kon') AS amount
WHERE (SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Kon') BETWEEN 0 AND 2
UNION
SELECT 
(SELECT DISTINCT class FROM Classes WHERE class = 'North Carolina') AS ship,
(SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'North Carolina') AS amount
WHERE (SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'North Carolina') BETWEEN 0 AND 2
UNION
SELECT 
(SELECT DISTINCT class FROM Classes WHERE class = 'Renown') AS ship,
(SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Renown') AS amount
WHERE (SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Renown') BETWEEN 0 AND 2
UNION
SELECT 
(SELECT DISTINCT class FROM Classes WHERE class = 'Revenge') AS ship,
(SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Revenge') AS amount
WHERE (SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Revenge') BETWEEN 0 AND 2
UNION
SELECT 
(SELECT DISTINCT class FROM Classes WHERE class = 'Tennessee') AS ship,
(SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Tennessee') AS amount
WHERE (SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Tennessee') BETWEEN 0 AND 2
UNION
SELECT 
(SELECT DISTINCT class FROM Classes WHERE class = 'Yamato') AS ship,
(SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Yamato') AS amount
WHERE (SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Yamato') BETWEEN 0 AND 2
 
 