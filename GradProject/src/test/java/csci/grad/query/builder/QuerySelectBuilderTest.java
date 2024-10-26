package csci.grad.query.builder;

import csci.grad.PersistenceType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuerySelectBuilderTest {

    @Test
    public void testSimpleQuery() {
        String query = new SelectQueryBuilder("table").createQuery();

        assertTrue(query.contains("SELECT") && query.contains("FROM table;"));
        assertEquals("SELECT * FROM table;", query);
    }

    @Test
    public void testQueryWithColumns() {
        SelectQueryBuilder queryBuilder = new SelectQueryBuilder("table");
        queryBuilder.addColumn("column1");
        queryBuilder.addColumn("column3");

        String query = queryBuilder.createQuery();

        assertTrue(query.contains("SELECT") && query.contains("FROM table;"));
        assertTrue(query.contains("column1") && query.contains("column3"));
    }

    @Test
    public void testQueryWithConstraints() {
        SelectQueryBuilder queryBuilder = new SelectQueryBuilder("table");
        queryBuilder.addColumn("column1");
        queryBuilder.addColumn("column3");
        queryBuilder.addConstraint("WHERE", "column1='value1'");
        queryBuilder.addConstraint("WHERE", "column3='value3'");

        String query = queryBuilder.createQuery();

        assertTrue(query.contains("SELECT") && query.contains("FROM table"));
        assertTrue(query.contains("column1") && query.contains("column3"));
        assertTrue(query.contains("WHERE") && query.contains("column1='value1'") && query.contains("column3='value3'"));
    }
}
