package db.migration;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.sql.Connection;
import java.sql.Statement;

import static org.jooq.impl.DSL.*;

public class V1__CreateUsers implements JdbcMigration {
    @Override
    public void migrate(Connection connection) throws Exception {
        try (Statement stmt = connection.createStatement()) {
            DSLContext create = DSL.using(connection);
            String ddl = create.createTable(table("users"))
                    .column(field("user_id", SQLDataType.BIGINT.identity(true)))
                    .column(field("account", SQLDataType.VARCHAR(100).nullable(false)))
                    .constraints(
                            constraint().primaryKey(field("user_id"))
                    ).getSQL();
            stmt.execute(ddl);
        }

    }
}
