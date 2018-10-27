create table log_tags (
  id     integer primary key autoincrement,
  log_id integer not null,
  tag_id integer not null,
  unique (log_id, tag_id)
);

select *
from log_tags;

insert into log_tags (log_id, tag_id)
values (?, ?);