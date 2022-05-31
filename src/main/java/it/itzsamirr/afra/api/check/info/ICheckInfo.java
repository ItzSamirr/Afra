package it.itzsamirr.afra.api.check.info;

import it.itzsamirr.afra.api.check.CheckCategory;
import it.itzsamirr.afra.api.check.ICheck;

public interface ICheckInfo {
    String getName();
    char getType();
    CheckCategory getCategory();
    String getDescription();
    ICheck getParent();
}
