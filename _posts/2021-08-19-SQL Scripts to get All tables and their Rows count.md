---
title: Query to get record count of all tables in a schema oracle
date: 2021-08-19 00:00:00 Z
categories:
- Java
tags:
- Java
layout: article
cover: /assets/logo/sql.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---

# Query to get record count of all tables in a schema oracle
```sql
select table_name, 
       to_number(extractvalue(xmltype(dbms_xmlgen.getxml('select count(*) c from '||owner||'.'||table_name)),'/ROWSET/ROW/C')) as count
from all_tables
where owner = '<SCHEMA_NAME>'
```

![](media/quertxml.png)