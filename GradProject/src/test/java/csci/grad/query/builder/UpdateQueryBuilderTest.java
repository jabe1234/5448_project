package csci.grad.query.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateQueryBuilderTest {
    @Test
    public void testBasicUpdateQuery() {
        UpdateQueryBuilder queryBuilder = new UpdateQueryBuilder("table");
        queryBuilder.addValue("column1", "value1");
        queryBuilder.addValue("column2", "value2");
        queryBuilder.addValue("column3", "value3");
        queryBuilder.addConstraint("WHERE", "column4='value4'");

        String query = queryBuilder.createQuery();

        assertTrue(query.contains("UPDATE table"));
        assertTrue(query.contains("column1 = value1"));
        assertTrue(query.contains("column2 = value2"));
        assertTrue(query.contains("column3 = value3"));
        assertTrue(query.contains("WHERE"));
        System.out.println(query);
    }
}
