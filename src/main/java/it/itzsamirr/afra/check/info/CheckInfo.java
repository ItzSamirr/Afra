package it.itzsamirr.afra.check.info;

import it.itzsamirr.afra.api.check.CheckCategory;
import it.itzsamirr.afra.api.check.ICheck;
import it.itzsamirr.afra.api.check.info.ICheckInfo;

public final class CheckInfo implements ICheckInfo {
    private final ICheck parent;
    private String name;
    private String description;
    private char type;
    private CheckCategory category;

    public CheckInfo(ICheck parent, CheckCategory category,  String name, char type, String description) {
        this.parent = parent;
        this.category = category;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public CheckInfo(ICheck parent, CheckCategory category,  String name, char type) {
        this(parent, category, name, type, "");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public char getType() {
        return type;
    }

    @Override
    public CheckCategory getCategory() {
        return category;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ICheck getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return category.name() + " | " + name + " | " + type;
    }
}
