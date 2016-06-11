create user webspider identified by webspider;
grant dba to webspider;

CREATE SEQUENCE spy_sequence  
    INCREMENT BY 1  -- 每次加几个  
    START WITH 1    -- 从1开始计数  
    NOMAXVALUE      -- 不设置最大值  
    NOCYCLE         -- 一直累加，不循环  
    CACHE 10;  

drop table Spy_Info;
create table Spy_Info(
       spy_id integer,
       url nvarchar2(100),
       title nvarchar2(100),
       content nvarchar2(2000),
       profile nvarchar2(500)
);

select t.*,t.rowid from Spy_Info t;
SELECT spy_sequence.currval FROM DUAL;  
SELECT spy_sequence.nextval FROM DUAL;  

INSERT INTO Spy_Info VALUES (spy_sequence.nextval,'http://www.baidu.com','HHAA阿','速度苏打','xzc');
