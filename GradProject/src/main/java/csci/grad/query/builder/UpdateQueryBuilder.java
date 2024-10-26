package csci.grad.query.builder;

import csci.grad.Operation;

public class UpdateQueryBuilder extends QueryBuilder {
    public UpdateQueryBuilder(String table) {
        super(Operation.UPDATE, table);
    }
}
