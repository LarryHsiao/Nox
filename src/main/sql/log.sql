create table log (
  id         integer primary key autoincrement,
  type       text not null,
  message    text not null,
  insertTime date
);

create trigger insert_log_with_time
  after insert
  on log
begin
  update log
  set insertTime = DATETIME('now')
  where ROWID = new.ROWID;
end;

select *
from log;

insert into log (type, message)
values ('INFO', 'MESSAGE');