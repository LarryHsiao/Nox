create table log (
  id      integer primary key autoincrement,
  type    text not null,
  message text not null,
  logTime datetime            DEFAULT (STRFTIME('%Y-%m-%d %H:%M:%f', 'NOW'))
);

select *
from log;

insert into log (type, message)
values ('INFO', 'MESSAGE');