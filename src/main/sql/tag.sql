create table tag (
  id   integer primary key autoincrement,
  name text not null unique
);

select *
from tag;

insert into tag (name)
values (?);