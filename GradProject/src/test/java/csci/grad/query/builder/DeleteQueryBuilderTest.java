package csci.grad.query.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteQueryBuilderTest {

    @Test
    public void testBasicDeleteQuery() {
        DeleteQueryBuilder queryBuilder = new DeleteQueryBuilder("table");

        String query = queryBuilder.createQuery();

        assertTrue(query.contains("DELETE") && query.contains("FROM table"));
        assertEquals("DELETE FROM table;", query);
    }

    @Test
    public void testDeleteWithConstraints() {
        DeleteQueryBuilder queryBuilder = new DeleteQueryBuilder("table");
        queryBuilder.addConstraint("WHERE", "column1='value1'");
        queryBuilder.addConstraint("WHERE", "column3='value3'");

        String query = queryBuilder.createQuery();

        assertTrue(query.contains("DELETE") && query.contains("FROM table"));
        assertTrue(query.contains("WHERE") && query.contains("column1='value1'") && query.contains("column3='value3'"));
        System.out.println(query);
    }
}
