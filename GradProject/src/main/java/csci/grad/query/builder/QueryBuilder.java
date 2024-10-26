package csci.grad.query.builder;

import csci.grad.*;

import java.util.*;

public abstract class QueryBuilder {
    private final Operation operation;
    private final String table;
    private final Set<String> columns;
    private final Map<String, String> values;
    private final Map<String, Set<String>> constraints;
    private PersistenceType persistenceType;
    private QueryBuilderAlgorithm constructorAlgorithm;

    public QueryBuilder(Operation operation, String table) {
        this.operation = operation;
        this.table = table;
        this.columns = new HashSet<>();
        this.values = new HashMap<>();
        this.persistenceType = PersistenceType.SQL;
        this.constructorAlgorithm = new SqlQueryBuilderAlgorithm();
        this.constraints = new HashMap<>();
    }

    public Operation getOperation() {
        return this.operation;
    }

    public String getTable() {
        return this.table;
    }

    public Set<String> getColumns() {
        return this.columns;
    }

    public QueryBuilder addColumn(String column) {
        this.columns.add(column);
        return this;
    }

    public Map<String, Set<String>> getConstraints() {
        return this.constraints;
    }

    public QueryBuilder addConstraint(String clause, String constraint) {
        this.constraints.computeIfAbsent(clause, k -> new HashSet<>()).add(constraint);
        return this;
    }

    public Map<String, String> getValues() {
        return this.values;
    }

    public QueryBuilder addValue(String column, String value) {
        this.values.put(column, value);
        return this;
    }

    public QueryBuilder setPersistenceType(PersistenceType persistenceType) {
        this.persistenceType = persistenceType;
        if (Objects.requireNonNull(persistenceType) == PersistenceType.NO_SQL) {
            this.constructorAlgorithm = new NoSqlQueryBuilderAlgorithm();
        } else {
            this.constructorAlgorithm = new SqlQueryBuilderAlgorithm();
        }
        return this;
    }

    public String createQuery() {
        return this.constructorAlgorithm.constructQuery(this);
    }
}
