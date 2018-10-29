package com.silverhetch.nox.model.logtags

import com.silverhetch.clotho.Source
import java.sql.Connection

/**
 * Decorator to connection for table log_tag
 */
class LogTagsDb(private val conn: Source<Connection>) : Source<Connection> {
    override fun fetch(): Connection {
        return conn.fetch().apply {
            this.createStatement().execute("""
              create table if not exists log_tags (
                  id     integer primary key autoincrement,
                  log_id integer not null,
                  tag_id integer not null,
                  unique (log_id, tag_id)
            );
            """)
        }
    }
}