use labor_sql
--SELECT * FROM outcome_o WHERE out>2000 ORDER BY date DESC
--SELECT * FROM ships WHERE name LIKE'%e%e%' AND name NOT LIKE'%e%e%e%'
--SELECT maker, type, speed, hd FROM laptop, product WHERE type = 'laptop' AND hd>=10
--SELECT DISTINCT maker FROM product WHERE type IN (SELECT type FROM product WHERE type != 'laptop') AND type = 'PC'
--SELECT DISTINCT maker FROM product, laptop WHERE type = 'laptop' AND speed<=450
/*
SELECT ship, battle, CASE result WHEN 'sunk' THEN N'потоплений'
						WHEN 'damaged' THEN N'нанесено ушкодження'
						WHEN 'OK' THEN N'Окей'
						ELSE '??'
					END AS result
					FROM outcomes;
*/
--SELECT price, model FROM printer WHERE price IN (SELECT MAX(price) FROM printer)
SELECT DISTINCT maker, price FROM product, pc WHERE price IN (SELECT DISTINCT MIN(price) FROM pc)

/*
SELECT * FROM ships, classes 
WHERE
(CASE numGuns WHEN 12 THEN 1 ELSE 0 END)+
(CASE bore WHEN 16 THEN 1 ELSE 0 END)+
(CASE displacement WHEN 46000 THEN 1 ELSE 0 END)+
(CASE type WHEN 'bc' THEN 1 ELSE 0 END)+
(CASE country WHEN 'Gt.Britain' THEN 1 ELSE 0 END)+
(CASE launched WHEN 1941 THEN 1 ELSE 0 END)+
(CASE ships.class WHEN 'North Carolina' THEN 1 ELSE 0 END) >= 4
*/



/*
SELECT (SELECT DISTINCT class FROM Classes WHERE class = 'Bismarck') ship,
 (SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Bismarck') amount
UNION
SELECT (SELECT DISTINCT class FROM Classes WHERE class = 'Iowa'),
 (SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Iowa') 
UNION
SELECT (SELECT DISTINCT class FROM Classes WHERE class = 'Kon'),
 (SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Kon') 
UNION
SELECT (SELECT DISTINCT class FROM Classes WHERE class = 'North Carolina'),
 (SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'North Carolina') 
UNION
SELECT (SELECT DISTINCT class FROM Classes WHERE class = 'Renown'),
 (SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Renown') 
UNION
SELECT (SELECT DISTINCT class FROM Classes WHERE class = 'Revenge'),
 (SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Revenge') 
UNION
SELECT (SELECT DISTINCT class FROM Classes WHERE class = 'Tennessee'),
 (SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Tennessee') 
UNION
SELECT (SELECT DISTINCT class FROM Classes WHERE class = 'Yamato'),
 (SELECT COUNT(name) FROM Ships, Classes WHERE Classes.class = Ships.class AND Classes.class = 'Yamato') 
 */

--UNION 
--SELECT class FROM Outcomes

--SELECT COUNT(class) FROM Classes
--SELECT class CASE class WHEN '' THEN COUNT()


SELECT DISTINCT CASE [maker] WHEN 'A' THEN N'Андрій' end
FROM product
WHERE [type] = 'pc' and not [maker] <> ALL (SELECT [maker] FROM product WHERE [type] = 'laptop');
