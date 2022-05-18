package Model.Entities;

public class Query {
    private String field;
    private Op op;
    private Object value;

    public Query() {}

    public Query(String field, Op op, Object value) {
        this.field = field;
        this.op    = op;
        this.value = value;
    }

    public String parseQuery() {
        String cmp = "=";
        switch (op) {
            case EQUAL:
                cmp = "=";
                break;
            case GREATER:
                cmp = ">";
                break;
            case LESS:
                cmp = "<";
                break;
        }

        return String.format(" WHERE %s%s%s", field, cmp, value.toString());
    }

    public String getField() {
        return field;
    }

    public Query setField(String field) {
        this.field = field;
        return this;
    }

    public Op getOp() {
        return op;
    }

    public Query setOp(Op op) {
        this.op = op;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public Query setValue(Object value) {
        this.value = value;
        return this;
    }
}
