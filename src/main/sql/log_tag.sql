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

-- Select logs with given tag id
select log.*
from log
       left join log_tags on log.id = log_tags.log_id
where log_tags.tag_id in (?)
group by log.id;

-- Select logs with given tag name
select log.*, tag.name
from log
       left join log_tags on log.id = log_tags.log_id
       left join tag on log_tags.tag_id = tag.id
where tag.name in (?)
group by log.id;