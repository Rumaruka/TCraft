package com.rumaruka.tcraft.utils;

public class ModUtils {

    public enum NBTType {
        SYNC, SAVE;

        public boolean sync() {
            return this == SYNC;
        }

        public boolean save() {
            return this == SAVE;
        }
    }

}
