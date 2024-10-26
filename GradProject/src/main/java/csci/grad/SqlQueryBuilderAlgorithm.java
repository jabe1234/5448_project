package csci.grad;

import csci.grad.query.builder.QueryBuilder;

import java.util.Map;
import java.util.Set;

public class SqlQueryBuilderAlgorithm extends QueryBuilderAlgorithm {
    @Override
    public String constructQuery(QueryBuilder queryBuilder) {
        switch (queryBuilder.getOperation()) {
            case INSERT -> {
                return constructInsertQuery(queryBuilder);
            }
            case UPDATE -> {
                return constructUpdateQuery(queryBuilder);
            }
            case DELETE -> {
                return constructDeleteQuery(queryBuilder);
            }
            case SELECT -> {
                return constructSelectQuery(queryBuilder);
            }
        }
        throw new IllegalArgumentException("Unknown operation: " + queryBuilder.getOperation());
    }

    public String constructInsertQuery(QueryBuilder queryBuilder) {
        StringBuilder query = new StringBuilder();
        query.append(queryBuilder.getOperation()).append(" INTO ");
        query.append(queryBuilder.getTable()).append(" (");
        if(!queryBuilder.getValues().isEmpty()) {
            for (Map.Entry<String, String> entry : queryBuilder.getValues().entrySet()) {
                query.append(entry.getKey()).append(", ");
            }
            query.deleteCharAt(query.length() - 2);
        } else {
            throw new IllegalArgumentException("No values provided");
        }
        query.append(") VALUES (");
        for (Map.Entry<String, String> entry : queryBuilder.getValues().entrySet()) {
            query.append(entry.getValue()).append(", ");
        }
        query.deleteCharAt(query.length() - 2);
        query.append(");");
        return query.toString();
    }

    public String constructUpdateQuery(QueryBuilder queryBuilder) {
        StringBuilder query = new StringBuilder();
        query.append(queryBuilder.getOperation()).append(" ");
        query.append(queryBuilder.getTable()).append(" ");
        query.append("SET ");
        if(!queryBuilder.getValues().isEmpty()) {
            for (Map.Entry<String, String> entry : queryBuilder.getValues().entrySet()) {
                query.append(entry.getKey()).append(" = ").append(entry.getValue()).append(", ");
            }
            query.deleteCharAt(query.length() - 2);
        } else {
            throw new IllegalArgumentException("No values provided");
        }
        if(!queryBuilder.getConstraints().isEmpty()) {
            for (Map.Entry<String, Set<String>> entry : queryBuilder.getConstraints().entrySet()) {
                query.append(" ").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        query.append(";");
        return query.toString();
    }

    public String constructDeleteQuery(QueryBuilder queryBuilder) {
        StringBuilder query = new StringBuilder();
        query.append(queryBuilder.getOperation()).append(" ");
        query.append("FROM ").append(queryBuilder.getTable());
        if(!queryBuilder.getConstraints().isEmpty()) {
            for (Map.Entry<String, Set<String>> entry : queryBuilder.getConstraints().entrySet()) {
                query.append(" ").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        query.append(";");
        return query.toString();
    }

    public String constructSelectQuery(QueryBuilder queryBuilder) {
        StringBuilder query = new StringBuilder();
        query.append(queryBuilder.getOperation()).append(" ");
        if (queryBuilder.getColumns().isEmpty()) {
            query.append("* ");
        } else {
            for (String column : queryBuilder.getColumns()) {
                query.append(column).append(", ");
            }
            query.deleteCharAt(query.length() - 2);
        }
        query.append("FROM ").append(queryBuilder.getTable());
        if(!queryBuilder.getConstraints().isEmpty()) {
            for (Map.Entry<String, Set<String>> entry : queryBuilder.getConstraints().entrySet()) {
                query.append(" ").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        query.append(";");
        return query.toString();
    }
}
