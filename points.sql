SELECT * FROM library.poi_milan;

UPDATE poi_milan 
SET g = ST_GeomFromText( 
CONCAT('POINT(', CAST(lat AS CHARACTER(20)), 
' ', CAST(lon AS CHARACTER(20)),')') 
);

SELECT idno, ASText(g) FROM poi_milan;

SELECT idno ,g , r,
        units * DEGREES( ACOS(
                   COS(RADIANS(latpoint)) 
                 * COS(RADIANS(X(g))) 
                 * COS(RADIANS(longpoint) - RADIANS(Y(g))) 
                 + SIN(RADIANS(latpoint)) 
                 * SIN(RADIANS(X(g))))) AS distance
   FROM poi_milan
   JOIN (
        SELECT 9.17  AS latpoint,  45.47 AS longpoint, 
               1 AS r, 69.0 AS units
        ) AS p ON (1=1)
  WHERE MbrContains(GeomFromText (
        CONCAT('LINESTRING(',
              latpoint-(r/units),' ',
              longpoint-(r /(units* COS(RADIANS(latpoint)))),
              ',', 
              latpoint+(r/units) ,' ',
              longpoint+(r /(units * COS(RADIANS(latpoint)))),
              ')')),  g)